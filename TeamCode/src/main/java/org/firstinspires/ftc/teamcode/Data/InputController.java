package org.firstinspires.ftc.teamcode.Data;

import com.qualcomm.robotcore.hardware.Gamepad;

public class InputController {

    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    // Previous button states (edge detection)
    private boolean previousDpadRight;
    private boolean previousDpadLeft;


    // Adjustable speed limits
    private double maxShooterSpeed = 0.5;

    public InputController(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public InputState Update() {

        InputState inputs = new InputState();

        // =========================
        // DRIVETRAIN INPUTS
        // =========================

        inputs.driveForward = -gamepad1.left_stick_y;
        inputs.driveStrafe = gamepad1.left_stick_x;
        inputs.driveTurn = gamepad1.right_stick_x;

        // =========================
        // SHOOTER INPUTS
        // =========================

        inputs.shooterOn = gamepad2.left_trigger > 0.5;
        inputs.shooterOff = !inputs.shooterOn && gamepad2.left_bumper;

        // Adjust shooter speed
        if (gamepad1.dpad_right && !previousDpadRight) {
            maxShooterSpeed = Math.min(1.0, maxShooterSpeed + 0.1);
        }

        if (gamepad1.dpad_left && !previousDpadLeft) {
            maxShooterSpeed = Math.max(0.0, maxShooterSpeed - 0.1);
        }

        // =========================
        // INTAKE INPUTS
        // =========================

        inputs.intakeOn = gamepad2.right_trigger > 0.5;
        inputs.intakeOff = !inputs.intakeOn && gamepad2.right_bumper;

        // =========================
        // UPDATE PREVIOUS BUTTONS
        // =========================

        previousDpadRight = gamepad1.dpad_right;
        previousDpadLeft = gamepad1.dpad_left;

        // =========================
        // OUTPUT VALUES
        // =========================
        inputs.maxShooterSpeed = maxShooterSpeed;

        return inputs;
    }
}