package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp(name = "Katapult Automatisch + Handmatig (verder naar voren)", group = "Example")
public class MotorNaarGradenKatapult extends LinearOpMode {

    private DcMotor katapultMotor;
    private VoltageSensor battery;

    // REV Core Hex motor parameters
    private static final double MOTOR_TICKS_PER_REV = 28.0;
    private static final double GEARBOX_RATIO = 20.0;
    private static final double TICKS_PER_REV = MOTOR_TICKS_PER_REV * GEARBOX_RATIO;
    private static final double DEGREES_PER_REV = 360.0;

    // Hoeklimieten
    private static final double VOOR_POS = 10.0;   // verder naar voren (schieten)
    private static final double TERUG_POS = 70.0;  // achter (rustpositie)

    private boolean isMoving = false;

    // Snelheden heen en terug
    private double powerVooruit = 1.0;   // krachtiger naar voren
    private double powerAchteruit = 0.4; // zachter terug om overshoot te vermijden

    // Snelheid voor handmatig bewegen
    private double manualPower = 0.5;

    @Override
    public void runOpMode() {

        katapultMotor = hardwareMap.get(DcMotor.class, "katapult");
        battery = hardwareMap.voltageSensor.iterator().next();

        katapultMotor.setDirection(DcMotor.Direction.FORWARD);

        // Reset encoder bij startpositie (handmatig in ruststand zetten!)
        katapultMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        katapultMotor.setTargetPosition(0);
        katapultMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        katapultMotor.setPower(0);

        telemetry.addLine("✅ Klaar — gebruik:");
        telemetry.addLine("   ➡ A = automatisch heen/terug");
        telemetry.addLine("   ➡ L1 = handmatig vooruit");
        telemetry.addLine("   ➡ R1 = handmatig achteruit");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double currentDegrees = (katapultMotor.getCurrentPosition() / TICKS_PER_REV) * DEGREES_PER_REV;

            // --- AUTOMATISCH schieten met A ---
            if (gamepad1.a && !isMoving) {
                isMoving = true;

                // Heen (verder naar voren)
                moveToAngle(VOOR_POS, powerVooruit);
                sleep(200);

                // Terug (rustpositie)
                moveToAngle(TERUG_POS, powerAchteruit);

                isMoving = false;
            }

            // --- HANDMATIG vooruit met L1 ---
            else if (gamepad1.left_bumper) {
                if (currentDegrees > VOOR_POS) {
                    katapultMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    katapultMotor.setPower(-manualPower); // Negatief om vooruit te gaan
                } else {
                    katapultMotor.setPower(0);
                }
            }

            // --- HANDMATIG achteruit met R1 ---
            else if (gamepad1.right_bumper) {
                if (currentDegrees < TERUG_POS) {
                    katapultMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    katapultMotor.setPower(manualPower); // Positief om terug te gaan
                } else {
                    katapultMotor.setPower(0);
                }
            }

            // Geen knoppen → stil
            else if (!isMoving) {
                katapultMotor.setPower(0);
            }

            telemetry.addData("Encoder ticks", katapultMotor.getCurrentPosition());
            telemetry.addData("Huidige hoek (°)", "%.1f", currentDegrees);
            telemetry.addData("VOOR_POS", VOOR_POS);
            telemetry.addData("TERUG_POS", TERUG_POS);
            telemetry.addData("Battery Voltage", battery.getVoltage());
            telemetry.update();
        }
    }

    // Automatische beweging naar een bepaalde hoek
    private void moveToAngle(double targetDegrees, double power) {
        // Limieten toepassen
        if (targetDegrees < VOOR_POS) targetDegrees = VOOR_POS;
        if (targetDegrees > TERUG_POS) targetDegrees = TERUG_POS;

        int targetTicks = (int) ((targetDegrees / DEGREES_PER_REV) * TICKS_PER_REV);

        katapultMotor.setTargetPosition(targetTicks);
        katapultMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        katapultMotor.setPower(power);

        while (katapultMotor.isBusy() && opModeIsActive()) {
            telemetry.addData("Target (ticks)", targetTicks);
            telemetry.addData("Current (ticks)", katapultMotor.getCurrentPosition());
            telemetry.addData("Power", power);
            telemetry.addData("Battery Voltage", battery.getVoltage());
            telemetry.update();
        }

        katapultMotor.setPower(0);
        katapultMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
