package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="Red_1", preselectTeleOp = "MainTeleop.java")
public class Red_1 extends LinearOpMode
{



    private DcMotor frontLeftDrive = null;  //  Used to control the left front drive wheel
    private DcMotor frontRightDrive = null;  //  Used to control the right front drive wheel
    private DcMotor backLeftDrive = null;  //  Used to control the left back drive wheel
    private DcMotor backRightDrive = null;

    public Red_1(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL)
    {
        this.frontRightDrive = fR;
        this.backLeftDrive = bL;
        this.backRightDrive = bR;
        this.frontLeftDrive = fL;
    }


    private AutonomousDrive Drive = null;


    @Override public void runOpMode() {


        frontLeftDrive = hardwareMap.get(DcMotor.class, "left_front");
        frontRightDrive = hardwareMap.get(DcMotor.class, "right_front");
        backLeftDrive = hardwareMap.get(DcMotor.class, "left_back");
        backRightDrive = hardwareMap.get(DcMotor.class, "right_back");

        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);


        Drive = new AutonomousDrive(frontLeftDrive,frontRightDrive,backRightDrive,backLeftDrive);




        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();
        waitForStart();


        {
            Drive.DriveForward(7);
        }
    }
}
