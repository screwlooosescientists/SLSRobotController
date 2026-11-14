package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Data.HardwareMap;

@TeleOp(name = "Teleop", group = "BioBuzz")
public class MainTeleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize robot
        Robot robot = new Robot(gamepad1, gamepad2);
        robot.Initialize(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        // Main robot loop
        while (opModeIsActive()) {

            robot.Update();
            telemetry.addData("Target RPM", robot.shooter.getTargetRPM());
            telemetry.addData("Measured RPM", robot.shooter.getCurrentRPM());
            telemetry.addData("Shooter state", robot.shooter.getState());

            telemetry.addData("Motor Power", HardwareMap.shooterMotor.getPower());
            telemetry.addData("Max Power", HardwareMap.shooterMotor.getMaxPower());
            telemetry.addData("Target Velocity", HardwareMap.shooterMotor.getTargetVelocity());


            telemetry.update();
        }
    }
}