package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Teleop", group="Decode")
public class MainTeleop extends LinearOpMode {

    // Motoren
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor leftBack = null;
    private DcMotor katapult = null;
    private Servo tegenhouden = null;


    // Servo posities (pas aan op jouw robot!)
    private final double SERVO_LOCK_POS = 0.8;     // Servo houdt katapult vast
    private final double SERVO_RELEASE_POS = 0.25; // Servo laat los (bijv. ~45 graden)

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Hardware mapping
        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        katapult = hardwareMap.get(DcMotor.class, "katapult");
        tegenhouden = hardwareMap.get(Servo.class, "release_servo");

        waitForStart();
        while (opModeIsActive()) {
            // Besturing met gamepad1
            double powerLeftStickY = -gamepad1.left_stick_y;
            double powerLeftStickX = gamepad1.left_stick_x;
            double powerRightStickX = -gamepad1.right_stick_x;

            telemetry.addData("Status", "Gereed om te starten");

            double leftFrontPower  = powerLeftStickY - powerLeftStickX - powerRightStickX;
            double leftBackPower   = powerLeftStickY + powerLeftStickX - powerRightStickX;
            double rightFrontPower = powerLeftStickY + powerLeftStickX + powerRightStickX;
            double rightBackPower  = powerLeftStickY - powerLeftStickX + powerRightStickX;

            leftFront.setPower(leftFrontPower);
            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
            leftBack.setPower(leftBackPower);

            // Katapult besturing met gamepad2.x
            if (gamepad2.x) {
                katapult.setPower(0.7); // volle kracht
                telemetry.addData("Katapult", "Opwinden...");
            } else {
                katapult.setPower(0);   // stoppen
            }

            // Servo besturing met gamepad2.y
            if (gamepad2.y) {
                tegenhouden.setPosition(SERVO_RELEASE_POS);
                telemetry.addData("Katapult", "Release!");
                telemetry.update();

                sleep(500); // korte pauze om te schieten

                tegenhouden.setPosition(SERVO_LOCK_POS);
                telemetry.addData("Katapult", "Terug naar lock");
            }

            telemetry.update();
        }
    }
}
