package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "basic: Linear OpMode", group = "Linear OpMode")
public class Oefeningpowahrobotsem extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftback= null;
    private DcMotor rightback = null;
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    leftback = hardwareMap.get(DcMotor.class,"left_back");
    rightback = hardwareMap.get(DcMotor.class, "right_back");
    leftfront = hardwareMap.get(DcMotor.class, "left_front");
    rightfront = hardwareMap.get(DcMotor.class, "right_front");

    leftback.setDirection(DcMotor.Direction.REVERSE);
    leftfront.setDirection(DcMotor.Direction.REVERSE);
    rightfront.setDirection(DcMotor.Direction.FORWARD);
    rightback.setDirection(DcMotor.Direction.REVERSE);


    waitForStart();
    resetRuntime();

    while (opModeIsActive()) {
        double powah = 0;

        double drive = -gamepad1.left_stick_y;
        leftback.setPower(drive);
        leftfront.setPower(drive);
        rightback.setPower(drive);
        rightfront.setPower(drive);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", powah);
        telemetry.update();


    }


    }


}
