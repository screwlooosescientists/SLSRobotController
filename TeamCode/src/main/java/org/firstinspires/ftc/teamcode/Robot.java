package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Data.HardwareMap;
import org.firstinspires.ftc.teamcode.Data.InputController;
import org.firstinspires.ftc.teamcode.Data.InputState;

public class Robot {

    //components
    InputController inputController;
    Drive drive;
    Shooter shooter;
    Intake intake;


    //used variables
    Gamepad gamepad1;
    Gamepad gamepad2;

    public Robot(Gamepad gamepad1, Gamepad gamepad2)
    {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        inputController = new InputController(gamepad1, gamepad2);
    }

    /**
     * Initializes the robot components from the hardware map and sets initial states
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

        shooter = new Shooter(HardwareMap.shooterMotor);

        intake = new Intake(HardwareMap.intakeMotor);
    }

    /**
     * Updates the states of all components
     */
    public void Update()
    {
        // Read the current controller inputs
        InputState input = inputController.Update();

        // ---------------- DRIVE ----------------
        drive.Update(
                input.driveForward,
                input.driveStrafe,
                input.driveTurn
        );

        // ---------------- SHOOTER ----------------
        if (input.shooterOn)
        {
            shooter.setMaxSpeed(input.maxShooterSpeed);
            shooter.spinUp();
        }
        else if (input.shooterOff)
        {
            shooter.pause();
        }

        shooter.Update();

        // ---------------- INTAKE ----------------
        if (input.intakeOn)
        {
            intake.start();
        }
        else if (input.intakeOff)
        {
            intake.stop();
        }

        intake.Update();
    }
}

