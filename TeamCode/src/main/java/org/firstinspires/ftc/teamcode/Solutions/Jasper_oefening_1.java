package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "HD Hex Motor Control")
public class Jasper_oefening_1 extends LinearOpMode {

    private DcMotor motor;
    private boolean motorAan = false;

    @Override
    public void runOpMode() {

        motor = hardwareMap.get(DcMotor.class, "motor");

        waitForStart();

        while (opModeIsActive()) {

            // X = motor aan
            if (gamepad1.x) {
                motorAan = true;
            }

            // Y = motor uit
            if (gamepad1.y) {
                motorAan = false;
            }

            if (motorAan) {
                motor.setPower(-0.8); // 100% vermogen
            } else {
                motor .setPower(0.0); // uit
            }

            telemetry.addData("Motor", motorAan ? "AAN" : "UIT");
            telemetry.update();
        }
    }
}