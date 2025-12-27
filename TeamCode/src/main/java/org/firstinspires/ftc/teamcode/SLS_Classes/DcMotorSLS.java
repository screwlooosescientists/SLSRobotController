package org.firstinspires.ftc.teamcode.SLS_Classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.function.BooleanSupplier;

public class DcMotorSLS {

    private DcMotor Motor = null;      // Motor being controlled
    private DcMotor Encoder = null;    // Encoder source (can be another motor)
    private final BooleanSupplier opModeActive;

    // PID gains
    private double kP = 0.005;
    private double kI = 0.0;
    private double kD = 0.0005;

    // PID state
    private double integral = 0;
    private double lastError = 0;

    // Target
    private volatile int targetPosition = 0;

    // Thread control
    private Thread pidThread;
    private volatile boolean running = false;

    // Tolerance
    private int tolerance = 20;

    // -----------------------------
    // Constructor
    // -----------------------------
    public DcMotorSLS(DcMotor motor, DcMotor encoder, BooleanSupplier opModeActive) {
        this.Motor = motor;
        this.Encoder = encoder;
        this.opModeActive = opModeActive;

        Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // -----------------------------
    // Public API
    // -----------------------------

    public void setPID(double p, double i, double d) {
        this.kP = p;
        this.kI = i;
        this.kD = d;
    }

    public void setTolerance(int ticks) {
        this.tolerance = ticks;
    }

    public void setTargetPosition(int target) {
        this.targetPosition = target;
    }

    public boolean atTarget() {
        int error = targetPosition - Encoder.getCurrentPosition();
        return Math.abs(error) < tolerance;
    }

    public void enablePID() {
        if (running) return;

        running = true;

        pidThread = new Thread(() -> {
            long lastTime = System.nanoTime();

            while (running && opModeActive.getAsBoolean()) {
                long now = System.nanoTime();
                double dt = (now - lastTime) / 1e9;
                lastTime = now;

                updatePID(dt);

                try {
                    Thread.sleep(5); // ~200 Hz
                } catch (InterruptedException e) {
                    break;
                }
            }

            Motor.setPower(0);
        });

        pidThread.start();
    }

    public void disablePID() {
        running = false;
        if (pidThread != null) {
            pidThread.interrupt();
        }
        Motor.setPower(0);
    }

    // -----------------------------
    // Internal PID logic
    // -----------------------------
    private void updatePID(double dt) {
        int current = Encoder.getCurrentPosition();
        int error = targetPosition - current;

        integral += error * dt;
        double derivative = (error - lastError) / dt;
        lastError = error;

        double output = kP * error + kI * integral + kD * derivative;

        // Clamp power
        output = Math.max(-1.0, Math.min(1.0, output));

        Motor.setPower(output);
    }

    // -----------------------------
    // Optional passthroughs
    // -----------------------------
    public void setPower(double p) { Motor.setPower(p); }
    public void setDirection(DcMotor.Direction d) { Motor.setDirection(d); }
    public DcMotor getMotor() { return Motor; }
}

