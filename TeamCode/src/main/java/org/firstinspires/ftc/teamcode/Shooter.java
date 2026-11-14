package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SLS_Classes.DcMotorSLS;

public class Shooter {

    private final DcMotorSLS outtakeMotor;
    private final Servo fireServo;

    // Encoder configuration
    private static final double TICKS_PER_REV = 28;

    // Shooter configuration
    private static double targetRPM = 2600;
    private double maxSpeed = 1.0;

    private static final double READY_TOLERANCE_RPM = 50;
    private static final double READY_STABLE_TIME = 0.2;

    private double readyTimer = 0;

    private enum ShooterState {
        IDLE,
        SPIN_UP,
        READY
    }

    private ShooterState currentState = ShooterState.IDLE;

    // Velocity PIDF constants
    private static final double kP = 0.00007;
    private static final double kI = 0.000007;
    private static final double kD = 0.0;

    private static final double kS = 0.08;
    private static final double kV = 0.0003;
    private static final double kA = 0.0;


    public Shooter(DcMotorSLS shooterMotor, Servo fireServo) {

        this.outtakeMotor = shooterMotor;
        this.fireServo = fireServo;

        outtakeMotor.setVelocityPIDF(
                kP, kI, kD,
                kS, kV, kA
        );

        outtakeMotor.setMaxPower(maxSpeed);
        outtakeMotor.stop();
    }


    // --------------------------------------------------
    // UNIT CONVERSION
    // --------------------------------------------------

    private double ticksPerSecondToRPM(double ticksPerSecond) {

        return (ticksPerSecond * 60.0) / TICKS_PER_REV;
    }

    private double rpmToTicksPerSecond(double rpm) {

        return (rpm * TICKS_PER_REV) / 60.0;
    }


    // --------------------------------------------------
    // COMMANDS
    // --------------------------------------------------

    public void fire()
    {
        //TODO tune servo pos
        fireServo.setPosition(1);
    }

    public void stopFire()
    {
        //TODO tune servo pos
        fireServo.setPosition(0);
    }

    public void spinUp() {

        if (currentState == ShooterState.IDLE) {

            outtakeMotor.setTargetVelocity(
                    rpmToTicksPerSecond(targetRPM)
            );

            readyTimer = 0;
            currentState = ShooterState.SPIN_UP;
        }
    }


    public void pause() {

        currentState = ShooterState.IDLE;
        readyTimer = 0;

        outtakeMotor.stop();
    }


    public void setTargetRPM(double rpm) {

        targetRPM = Math.max(0, rpm);

        if (currentState != ShooterState.IDLE) {

            outtakeMotor.setTargetVelocity(
                    rpmToTicksPerSecond(targetRPM)
            );

            readyTimer = 0;
            currentState = ShooterState.SPIN_UP;
        }
    }


    public void setMaxSpeed(double speed) {

        maxSpeed = Math.max(0.0, Math.min(1.0, speed));

        outtakeMotor.setMaxPower(maxSpeed);
    }


    // --------------------------------------------------
    // STATE MACHINE
    // --------------------------------------------------

    public void Update(double dt) {

        // Ignore invalid time steps
        if (dt <= 0 || dt > 0.5) {
            return;
        }

        switch (currentState) {

            case IDLE:

                outtakeMotor.stop();

                break;


            case SPIN_UP:

                outtakeMotor.update(dt);

                checkReady(dt);

                break;


            case READY:

                // Continue PIDF control to maintain velocity
                outtakeMotor.update(dt);

                checkVelocity();

                break;
        }
    }


    // --------------------------------------------------
    // STATE TRANSITIONS
    // --------------------------------------------------

    private void checkReady(double dt) {

        double currentRPM = getCurrentRPM();

        double velocityError = Math.abs(targetRPM - currentRPM);

        if (velocityError <= READY_TOLERANCE_RPM) {

            readyTimer += dt;

            if (readyTimer >= READY_STABLE_TIME) {

                currentState = ShooterState.READY;
            }

        } else {

            readyTimer = 0;
        }
    }


    private void checkVelocity() {

        double currentRPM = getCurrentRPM();

        double velocityError = Math.abs(targetRPM - currentRPM);

        if (velocityError > READY_TOLERANCE_RPM) {

            currentState = ShooterState.SPIN_UP;
            readyTimer = 0;
        }
    }


    // --------------------------------------------------
    // GETTERS
    // --------------------------------------------------

    public double getCurrentRPM() {

        return ticksPerSecondToRPM(
                outtakeMotor.getCurrentVelocity()
        );
    }

    public double getTargetRPM() {
        return targetRPM;
    }

    public String getState() {
        return currentState.name();
    }
}