package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Motor Naar Graden", group = "TeleOp")
public class MotorNaarGraden extends OpMode {

    DcMotor motor;
    double targetDegrees = 0;
    final double TICKS_PER_REV = 288; // REV Core Hex motor encoder
    final double DEGREES_PER_REV = 360;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad1.triangle) {
            targetDegrees = 90;
        } else if (gamepad1.circle) {
            targetDegrees = 0;
        }

        int targetTicks = (int) (targetDegrees / DEGREES_PER_REV * TICKS_PER_REV);
        motor.setTargetPosition(targetTicks);
        motor.setPower(0.5); // Pas aan voor snelheid
    }
}