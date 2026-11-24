package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Data.HardwareMap;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "Teleop", group = "BioBuzz")
public class MainTeleop extends LinearOpMode {
    private Follower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize robot
        follower = Constants.create(hardwareMap);
        //follower.setPose();
        assert follower != null;
        follower.update();


        Robot robot = new Robot(gamepad1, gamepad2);
        robot.Initialize(hardwareMap);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        // Main robot loop
        while (opModeIsActive()) {

            follower.manual(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );

            follower.update();

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