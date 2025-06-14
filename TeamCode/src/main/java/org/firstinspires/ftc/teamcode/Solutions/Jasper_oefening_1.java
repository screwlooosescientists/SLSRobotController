package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
public class Jasper_oefening_1 extends LinearOpMode {
    private DcMotor right_back = null;
    private DcMotor right_front = null;
    private DcMotor left_back = null;
    private DcMotor left_front = null;

@Override
public void runOpMode() {
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    left_back = hardwareMap.get(DcMotor.class, "left_back");
    left_front = hardwareMap.get(DcMotor.class, "left_frond");
    right_back = hardwareMap.get(DcMotor.class, "right_back");
    right_front = hardwareMap.get(DcMotor.class, "right_frond");

    left_back.setDirection(DcMotor.Direction.FORWARD);
    left_front.setDirection(DcMotor.Direction.FORWARD);
    right_back.setDirection(DcMotor.Direction.FORWARD);
    right_front.setDirection(DcMotor.Direction.FORWARD);

    waitForStart();
    while (opModeIsActive()) {

        double Power = 0;
        left_back.setPower(Power);
        right_back.setPower(Power);
        left_front.setPower(Power);
        right_front.setPower(Power);


    }
}
}

