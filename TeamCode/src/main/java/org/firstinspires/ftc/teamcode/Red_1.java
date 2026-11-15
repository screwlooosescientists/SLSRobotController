package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="Red_1", preselectTeleOp = "MainTeleop.java")
public class Red_1 extends LinearOpMode
{



    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;

    //private DcMotor  = null;
    private Servo WipMotor = null;
    private AutonomousDrive Drive = null;


    @Override public void runOpMode() {


        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");


        //Shooter = hardwareMap.get(DcMotor.class, "katapult");
        //Intake = hardwareMap.get(Servo.class, "Wipper");

        //Schieter = new Katapult(SchietMotor, WipMotor);
        Drive = new AutonomousDrive(leftFront, leftBack, rightBack, rightFront);


        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


        // Wait for driver to press start
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();
        waitForStart();


        {
            Drive.DriveForward(1);
        }
    }
}
