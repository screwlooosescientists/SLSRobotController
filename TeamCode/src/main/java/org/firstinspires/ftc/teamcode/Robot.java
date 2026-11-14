package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Data.HardwareMap;
import org.firstinspires.ftc.teamcode.Data.InputController;
import org.firstinspires.ftc.teamcode.Data.InputState;

public class Robot {

    // Components
    InputController inputController;
    Drive drive;
    Shooter shooter;
    Intake intake;

    // Gamepads
    Gamepad gamepad1;
    Gamepad gamepad2;

    // Delta time
    private final ElapsedTime updateTimer = new ElapsedTime();
    private double deltaTime;

    public Robot(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;

        inputController = new InputController(gamepad1, gamepad2);
    }

    /**
     * Initializes the robot components from the hardware map.
     */
    public void Initialize(
            com.qualcomm.robotcore.hardware.HardwareMap hardwareMap) {

        HardwareMap.Init(hardwareMap);

        drive = new Drive(
                HardwareMap.leftFront,
                HardwareMap.rightFront,
                HardwareMap.rightBack,
                HardwareMap.leftBack
        );

        shooter = new Shooter(HardwareMap.shooterMotor, HardwareMap.fireServo);
        intake = new Intake(HardwareMap.intakeMotor);

        // Start timing after initialization
        updateTimer.reset();
    }

    /**
     * Updates the states of all components.
     */
    public void Update() {

        // Calculate delta time in seconds
        deltaTime = updateTimer.seconds();
        updateTimer.reset();

        // Read controller inputs
        InputState input = inputController.Update();

        // ---------------- DRIVE ----------------
        drive.Update(
                input.driveForward,
                input.driveStrafe,
                input.driveTurn
        );

        // ---------------- SHOOTER ----------------
        if (input.shooterOn) {
            shooter.setMaxSpeed(input.maxShooterSpeed);
            shooter.spinUp();
        }
        else if (input.shooterOff) {
            shooter.pause();
        }

        if(input.fire)
        {
            shooter.fire();
        }
        else
        {
            shooter.stopFire();
        }

        shooter.Update(deltaTime);

        // ---------------- INTAKE ----------------
        if (input.intakeOn) {
            intake.start();
        }
        else if (input.intakeOff) {
            intake.stop();
        }

        intake.Update();
    }

    public double getDeltaTime() {
        return deltaTime;
    }
}