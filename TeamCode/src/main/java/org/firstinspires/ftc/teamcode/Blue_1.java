package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.firstinspires.ftc.teamcode.AutonomousDrive.*;
import static org.firstinspires.ftc.teamcode.Data.AutonomousConfiguration.*;

@Autonomous(name="Blue1", preselectTeleOp = "teleop")
public class Blue_1 extends LinearOpMode {
    // Adjust these numbers to suit your robot.
    final double DESIRED_DISTANCE = 60; //  this is how close the camera should get to the target (inches)

    private DcMotor leftfront = null;  //  Used to control the left front drive wheel
    private DcMotor leftback= null;  //  Used to control the right front drive wheel
    private DcMotor rightback = null;  //  Used to control the left back drive wheel
    private DcMotor rightfront = null;  //  Used to control the right back drive wheel


    private AutonomousDrive Drive = null;


    @Override
    public void runOpMode() {

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftback = hardwareMap.get(DcMotor.class, "left_back");
        rightback = hardwareMap.get(DcMotor.class, "right_back");


        Drive = new AutonomousDrive(leftback,leftfront,rightback,rightfront);


        leftfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        leftback.setDirection(DcMotorSimple.Direction.REVERSE);



        // Wait for driver to press start
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();

        waitForStart();

//TODO Here goes the autonomous program



        //Drive.DriveForward(1);

        //for (int i = 1; i < 3; i++)

        //Drive.DriveRight(1);
        Drive.DriveForward(-5);

    }


    public void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double frontLeftPower = -(x - y) - yaw;
        double frontRightPower = -(x + y) + yaw;
        double backLeftPower = -(x + y) - yaw;
        double backRightPower = -(x - y) + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        // Send powers to the wheels.
        leftfront.setPower(frontLeftPower);
       rightfront.setPower(frontRightPower);
        leftback.setPower(backLeftPower);
       rightback.setPower(backRightPower);
    }
}




