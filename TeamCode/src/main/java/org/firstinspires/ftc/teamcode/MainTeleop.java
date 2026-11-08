package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

            telemetry.update();
        }
    }
}