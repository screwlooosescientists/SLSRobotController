package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Katapult Control", group="TeleOp")
public class KatapultControl extends LinearOpMode {

    private DcMotor katapultMotor = null;
    private DcMotor katapultMotor2 = null;
    private Servo releaseServo = null;

    // Servo posities (pas aan op jouw robot!)
    private final double SERVO_LOCK_POS = 0.8;    // Servo houdt katapult vast
    private final double SERVO_RELEASE_POS = -0.3; // Servo laat los

    @Override
    public void runOpMode() {
        // Hardware mapping
        katapultMotor = hardwareMap.get(DcMotor.class, "katapult");
        katapultMotor2 = hardwareMap.get(DcMotor.class, "katapult2");
        releaseServo = hardwareMap.get(Servo.class, "release_servo");

        // Richting en gedrag
        katapultMotor.setDirection(DcMotor.Direction.BACKWARD);
        katapultMotor2.setDirection(DcMotor.Direction.FORWARD);
        katapultMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Servo startpositie
        releaseServo.setPosition(SERVO_LOCK_POS);

        telemetry.addData("Status", "Gereed om te starten");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // 🌀 Opwinden met X
            if (gamepad1.x) {
                katapultMotor.setPower(1.0);
                katapultMotor2.setPower(1.0);
                telemetry.addData("Katapult", "Opwinden...");
            } else {
                katapultMotor.setPower(0);
            }

            // 🎯 Releasen met Δ/Y
            if (gamepad1.y) {
                releaseServo.setPosition(SERVO_RELEASE_POS);
                telemetry.addData("Katapult", "Release!");
                telemetry.update();

                sleep(500); // korte pauze om te schieten
                releaseServo.setPosition(SERVO_LOCK_POS);
                telemetry.addData("Katapult", "Terug naar lock");
            }

            telemetry.update();
        }
    }
}