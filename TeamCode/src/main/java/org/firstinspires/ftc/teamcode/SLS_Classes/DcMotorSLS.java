
package org.firstinspires.ftc.teamcode.SLS_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

public class DcMotorSLS {
    public boolean isBusy() {
        return false;
    }

    public void setMode(DcMotor.RunMode runMode) {
    }

    // --------------------------------------------------
    // CONTROL MODES
    // --------------------------------------------------

    public enum ControlMode {
        OFF,
        MANUAL,
        POSITION,
        VELOCITY
    }

    private ControlMode controlMode = ControlMode.OFF;

    // --------------------------------------------------
    // HARDWARE
    // --------------------------------------------------

    private final DcMotorEx motor;

    // Encoder position source
    private final IntSupplier positionSource;

    // Optional position feed-forward input
    private DoubleSupplier positionFeedforward;

    // --------------------------------------------------
    // GENERAL SETTINGS
    // --------------------------------------------------

    private double maxPower = 1.0;

    // --------------------------------------------------
    // POSITION PIDF
    // --------------------------------------------------

    private double positionKP = 0.0;
    private double positionKI = 0.0;
    private double positionKD = 0.0;
    private double positionKS = 0.0;

    private double positionIntegral = 0.0;
    private double previousPosition = 0.0;

    private int targetPosition = 0;
    private int positionTolerance = 20;

    private boolean firstPositionUpdate = true;

    // --------------------------------------------------
    // VELOCITY PIDF
    // --------------------------------------------------

    private double velocityKP = 0.0;
    private double velocityKI = 0.0;
    private double velocityKD = 0.0;

    // Velocity feed-forward
    private double velocityKS = 0.0;
    private double velocityKV = 0.0;
    private double velocityKA = 0.0;

    private double velocityIntegral = 0.0;
    private double previousVelocity = 0.0;

    private double targetVelocity = 0.0;
    private double targetAcceleration = 0.0;

    private boolean firstVelocityUpdate = true;

    // --------------------------------------------------
    // CONSTRUCTORS
    // --------------------------------------------------

    /**
     * Constructor using the motor's built-in encoder
     * for both position and velocity.
     */
    public DcMotorSLS(DcMotorEx motor) {

        this.motor = motor;
        this.positionSource = motor::getCurrentPosition;

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Constructor using the motor's built-in velocity
     * measurement and a separate position encoder.
     */
    public DcMotorSLS(
            DcMotorEx motor,
            IntSupplier positionSource
    ) {

        this.motor = motor;
        this.positionSource = positionSource;

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // --------------------------------------------------
    // MAIN UPDATE
    // --------------------------------------------------

    /**
     * Call once per OpMode loop.
     *
     * @param dt Time since previous update, in seconds.
     */
    public void update(double dt) {

        if (dt <= 0.0 || dt > 0.5) {
            return;
        }

        if (controlMode == ControlMode.OFF ||
                controlMode == ControlMode.MANUAL) {
            return;
        }

        switch (controlMode) {

            case POSITION:
                updatePosition(dt);
                break;

            case VELOCITY:
                updateVelocity(dt);
                break;
        }
    }

    // --------------------------------------------------
    // POSITION CONTROL
    // --------------------------------------------------

    private void updatePosition(double dt) {

        if (positionSource == null) {
            throw new IllegalStateException(
                    "Position control requires an encoder."
            );
        }

        double currentPosition = positionSource.getAsInt();

        double error = targetPosition - currentPosition;

        // Proportional
        double p = positionKP * error;

        // Derivative on measurement
        double derivative = 0.0;

        if (!firstPositionUpdate) {
            derivative =
                    -(currentPosition - previousPosition) / dt;
        }

        double d = positionKD * derivative;

        // Static friction compensation
        double staticFF = 0.0;

        if (Math.abs(error) > positionTolerance) {
            staticFF = positionKS * Math.signum(error);
        }

        // Optional mechanism-specific feed-forward
        double customFF = 0.0;

        if (positionFeedforward != null) {
            customFF = positionFeedforward.getAsDouble();
        }

        // Integral candidate
        double integralCandidate =
                positionIntegral + error * dt;

        double i = positionKI * integralCandidate;

        // Total output
        double output = p + i + d + staticFF + customFF;

        // Anti-windup
        boolean saturatedHigh = output > maxPower;
        boolean saturatedLow = output < -maxPower;

        if ((!saturatedHigh && !saturatedLow) ||
                (saturatedHigh && error < 0) ||
                (saturatedLow && error > 0)) {

            positionIntegral = integralCandidate;
        }

        // Recalculate output
        i = positionKI * positionIntegral;

        output = p + i + d + staticFF + customFF;

        motor.setPower(clamp(output, -maxPower, maxPower));

        previousPosition = currentPosition;
        firstPositionUpdate = false;
    }

    /**
     * Set target encoder position.
     */
    public void setTargetPosition(int target) {

        targetPosition = target;

        positionIntegral = 0.0;
        firstPositionUpdate = true;

        controlMode = ControlMode.POSITION;
    }

    /**
     * Configure position PIDF.
     */
    public void setPositionPIDF(
            double kP,
            double kI,
            double kD,
            double kS
    ) {

        positionKP = kP;
        positionKI = kI;
        positionKD = kD;
        positionKS = kS;

        positionIntegral = 0.0;
    }

    /**
     * Configure custom position feed-forward.
     */
    public void setPositionFeedforward(
            DoubleSupplier feedforward
    ) {
        positionFeedforward = feedforward;
    }

    public void setPositionTolerance(int tolerance) {
        positionTolerance = Math.max(0, tolerance);
    }

    public boolean atTarget() {

        if (positionSource == null) {
            return false;
        }

        return Math.abs(
                targetPosition - positionSource.getAsInt()
        ) <= positionTolerance;
    }

    // --------------------------------------------------
    // VELOCITY CONTROL
    // --------------------------------------------------

    private void updateVelocity(double dt) {

        // REV / FTC SDK encoder velocity in ticks per second
        double currentVelocity = motor.getVelocity();

        double error = targetVelocity - currentVelocity;

        // Proportional
        double p = velocityKP * error;

        // Derivative on measurement
        double derivative = 0.0;

        if (!firstVelocityUpdate) {
            derivative =
                    -(currentVelocity - previousVelocity) / dt;
        }

        double d = velocityKD * derivative;

        // Feed-forward
        double staticFF =
                velocityKS * Math.signum(targetVelocity);

        double velocityFF =
                velocityKV * targetVelocity;

        double accelerationFF =
                velocityKA * targetAcceleration;

        double feedforward =
                staticFF + velocityFF + accelerationFF;

        // Integral candidate
        double integralCandidate =
                velocityIntegral + error * dt;

        double i = velocityKI * integralCandidate;

        double output = p + i + d + feedforward;

        // Anti-windup
        boolean saturatedHigh = output > maxPower;
        boolean saturatedLow = output < -maxPower;

        if ((!saturatedHigh && !saturatedLow) ||
                (saturatedHigh && error < 0) ||
                (saturatedLow && error > 0)) {

            velocityIntegral = integralCandidate;
        }

        // Recalculate output
        i = velocityKI * velocityIntegral;

        output = p + i + d + feedforward;

        motor.setPower(clamp(output, -maxPower, maxPower));

        previousVelocity = currentVelocity;
        firstVelocityUpdate = false;
    }

    /**
     * Set target velocity in ticks per second.
     */
    public void setTargetVelocity(double velocity) {
        setTargetVelocity(velocity, 0.0);
    }

    /**
     * Set target velocity and acceleration.
     *
     * Units:
     * velocity = ticks/second
     * acceleration = ticks/second^2
     */
    public void setTargetVelocity(
            double velocity,
            double acceleration
    ) {

        targetVelocity = velocity;
        targetAcceleration = acceleration;

        velocityIntegral = 0.0;
        firstVelocityUpdate = true;

        controlMode = ControlMode.VELOCITY;
    }

    /**
     * Configure velocity PIDF and feed-forward.
     */
    public void setVelocityPIDF(
            double kP,
            double kI,
            double kD,
            double kS,
            double kV,
            double kA
    ) {

        velocityKP = kP;
        velocityKI = kI;
        velocityKD = kD;

        velocityKS = kS;
        velocityKV = kV;
        velocityKA = kA;

        velocityIntegral = 0.0;
    }

    // --------------------------------------------------
    // MANUAL CONTROL
    // --------------------------------------------------

    /**
     * Directly control motor power.
     */
    public void setPower(double power) {

        controlMode = ControlMode.MANUAL;

        motor.setPower(
                clamp(power, -maxPower, maxPower)
        );
    }

    // --------------------------------------------------
    // GENERAL CONTROL
    // --------------------------------------------------

    public void setMaxPower(double maxPower) {

        this.maxPower = clamp(
                Math.abs(maxPower),
                0.0,
                1.0
        );
    }

    public void setDirection(DcMotor.Direction direction) {
        motor.setDirection(direction);
    }

    public void setZeroPowerBehavior(
            DcMotor.ZeroPowerBehavior behavior
    ) {
        motor.setZeroPowerBehavior(behavior);
    }

    public void stop() {

        controlMode = ControlMode.OFF;

        motor.setPower(0.0);

        positionIntegral = 0.0;
        velocityIntegral = 0.0;
    }

    // --------------------------------------------------
    // GETTERS
    // --------------------------------------------------

    public ControlMode getControlMode() {
        return controlMode;
    }

    public int getCurrentPosition() {

        if (positionSource == null) {
            throw new IllegalStateException(
                    "No position encoder configured."
            );
        }

        return positionSource.getAsInt();
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    /**
     * Returns motor velocity in ticks per second
     * using the FTC SDK / REV motor encoder reading.
     */
    public double getCurrentVelocity() {
        return motor.getVelocity();
    }

    public double getPower() {
        return motor.getPower();
    }

    public double getMaxPower() {
        return maxPower;
    }

    // --------------------------------------------------
    // UTILITY
    // --------------------------------------------------

    private double clamp(
            double value,
            double min,
            double max
    ) {
        return Math.max(min, Math.min(max, value));
    }
}
