package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Knip gastlessen bot")
public class Jasper_oefening_1 extends LinearOpMode {

    private DcMotor motorleft = null;
    private DcMotor motorright = null;
    private DcMotor Shoter = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorleft = hardwareMap.get(DcMotor.class, "Motorleft");
        motorright = hardwareMap.get(DcMotor.class, "MotorRight");
        Shoter = hardwareMap.get(DcMotor.class, "shoter");

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y;
            double Powerleft_stick_x;
            double Powerright_stick_x;

            Powerleft_stick_y = gamepad1.left_stick_y;
            Powerleft_stick_x = gamepad1.left_stick_x;
            Powerright_stick_x = gamepad1.right_stick_x;

            double LeftFront = Powerleft_stick_y - Powerleft_stick_x - Powerright_stick_x;
            double LeftBack = Powerleft_stick_y + Powerleft_stick_x - Powerright_stick_x;
            double RightFront = Powerleft_stick_y + Powerleft_stick_x + Powerright_stick_x;
            double RightBack = Powerleft_stick_y - Powerleft_stick_x + Powerright_stick_x;


            motorleft.setPower(LeftFront);
            motorright.setPower(RightFront);
            Shoter.setPower(RightBack);
        }
    }

}