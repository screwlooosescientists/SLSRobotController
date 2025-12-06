package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Katapult;

@TeleOp(name="Teleop", group="Decode")
public class MainTeleop extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;

    private Katapult Schieter = null;
    private DcMotor SchietMotor = null;
    private Servo WipMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");
        SchietMotor = hardwareMap.get(DcMotor.class, "katapult");
        WipMotor = hardwareMap.get(Servo.class, "Wipper");

        Schieter = new Katapult(SchietMotor, WipMotor);


        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = -gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = -gamepad1.right_stick_x;

            double LeftFront = Powerleft_stick_y - Powerleft_stick_x - Powerright_stick_x;
            double LeftBack = Powerleft_stick_y + Powerleft_stick_x - Powerright_stick_x;
            double RightFront = Powerleft_stick_y + Powerleft_stick_x + Powerright_stick_x;
            double RightBack = Powerleft_stick_y - Powerleft_stick_x + Powerright_stick_x;

            leftfront.setPower(LeftFront);
            rightfront.setPower(RightFront);
            rightback.setPower(RightBack);
            leftback.setPower(LeftBack);

            Schieter.ShootKatapult(gamepad2.x);
            Schieter.Laden(gamepad2.triangle);
        }
    }
}
