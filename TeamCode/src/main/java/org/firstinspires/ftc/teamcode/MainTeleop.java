package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Teleop", group="Decode")
public class MainTeleop extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;
    private DcMotor katapult = null;
    private Servo tegenhouden = null;



    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");
        katapult = hardwareMap.get(DcMotor.class, "katapult");
        tegenhouden = hardwareMap.get(Servo.class, "tegenhouden");

        katapult.setDirection(DcMotor.Direction.REVERSE);
        katapult.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = -gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = -gamepad1.right_stick_x;

            telemetry.addData("Status", "Gereed om te starten");

            double LeftFront = -Powerleft_stick_y - Powerleft_stick_x - Powerright_stick_x;
            double LeftBack = -Powerleft_stick_y + Powerleft_stick_x - Powerright_stick_x;
            double RightFront = -Powerleft_stick_y + Powerleft_stick_x + Powerright_stick_x;
            double RightBack = -Powerleft_stick_y - Powerleft_stick_x + Powerright_stick_x;

            leftfront.setPower(LeftFront);
            rightfront.setPower(RightFront);
            rightback.setPower(RightBack);
            leftback.setPower(LeftBack);
            tegenhouden.setPosition(1.0);
            if (gamepad2.x) {
                katapult.setPower(1.0); // volle kracht
                telemetry.addData("Katapult", "Opwinden...");
                else
                katapult.setPower(0);   // stoppen
            }

            if (gamepad2.circle) {
                tegenhouden.setPosition(0.25); // ongeveer 45 graden
                telemetry.addData("Servo", "Tegenhouden op 45 graden (0.25)");
            } else {
                tegenhouden.setPosition(0.0); // terug naar ruststand
                telemetry.addData("Servo", "Tegenhouden rust (0.0)");
            }

            telemetry.update();
        }