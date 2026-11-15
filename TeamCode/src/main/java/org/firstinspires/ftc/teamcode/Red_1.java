package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="Red_1", preselectTeleOp = "MainTeleop.java")
public class Red_1 extends LinearOpMode
{



    private DcMotor leftfront;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotor leftback;

    private AutonomousDrive Drive ;


    @Override public void runOpMode() {


        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftback = hardwareMap.get(DcMotor.class, "left_back");
        rightback = hardwareMap.get(DcMotor.class, "right_back");

        leftfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        leftback.setDirection(DcMotorSimple.Direction.REVERSE);
        Drive = new AutonomousDrive();



        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();
        waitForStart();


        {
            Drive.Turn(2);
        }
    }
}
