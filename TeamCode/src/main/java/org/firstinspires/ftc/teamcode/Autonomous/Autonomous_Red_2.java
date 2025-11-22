package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Autonomous Red 1 (Encoders)", group="Autonomous")
public class Autonomous_Red_2 extends LinearOpMode {

    private DcMotor left_front = null;
    private DcMotor right_front= null;
    private DcMotor right_back = null;
    private DcMotor left_back = null;

    private DcMotor katapult = null;

    // ---- Encoder instellingen ----
    static final double COUNTS_PER_MOTOR_REV = 1120;  // Pas aan aan jouw motor type
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_CM = 7.5;     // Jouw wiel diameter


    static final double COUNTS_PER_CM = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_CM * Math.PI);

    @Override
    public void runOpMode() {
        // Hardware mapping
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class, "right_back");
        katapult = hardwareMap.get(DcMotor.class, "katapult");

        // Richtingen (pas aan indien nodig)
        left_front.setDirection(DcMotor.Direction.FORWARD);
        right_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotorSimple.Direction.REVERSE);
        right_back.setDirection(DcMotorSimple.Direction.FORWARD);
        katapult.setDirection(DcMotorSimple.Direction.REVERSE);

        // Stop met rollen bij 0 power
        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        katapult.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoders
        resetEncoders();

        telemetry.addData("Status", "Gereed om te starten");
        telemetry.update();

        waitForStart();


        // Rijd vooruit (2 rotaties)
        driveForward(0.7, 2.0);

        turnLeft(0.7,2.0);

        Shoot(0.5, 1);



        telemetry.addData("Status", "Autonomous klaar");
        telemetry.update();
    }

    // --- Hulpfuncties ---

  // vooruit rijden
    private void driveForward(double power, double rotations) {
        int moveCounts = (int)(rotations * COUNTS_PER_MOTOR_REV);

        // Links achteruit, rechts vooruit
        setTargetPosition(-moveCounts, moveCounts, -moveCounts, moveCounts);
        runToPosition();

        left_front.setPower(power);
        right_front.setPower(power);
        left_back.setPower(power);
        right_back.setPower(power);
        stopDriving();
        runUsingEncoders();

        setPowerAll(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Beweging", "Vooruit");
            telemetry.addData("Positie", "LF: %d RF: %d", left_front.getCurrentPosition(), right_front.getCurrentPosition());
            telemetry.update();
        }

        stopDriving();
        runUsingEncoders();
    }
    private void turnLeft(double power, double rotations) {
        int moveCounts = (int)(rotations * COUNTS_PER_MOTOR_REV);
        // Links achteruit, rechts vooruit
        setTargetPosition (-moveCounts, moveCounts, -moveCounts, moveCounts);
        runToPosition();

        left_front.setPower(power);
        right_front.setPower(power);
        left_back.setPower(power);
        right_back.setPower(power);
        stopDriving();
        runUsingEncoders();

        setPowerAll(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Beweging", "Vooruit");
            telemetry.addData("Positie", "LF: %d RF: %d", left_front.getCurrentPosition(), right_front.getCurrentPosition());
            telemetry.update();
        }

        stopDriving();
        runUsingEncoders();
    }
    //schieten
    private void Shoot(double power, int timeMs) {
        katapult.setPower(power);
        sleep(timeMs);
    }

    private void stopDriving() {
        setPowerAll(0);
    }

    private void resetEncoders() {
        left_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        runUsingEncoders();
    }

    private void runToPosition() {
        left_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void runUsingEncoders() {
        left_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setTargetPosition(int lf, int rf, int lb, int rb) {
        left_front.setTargetPosition(left_front.getCurrentPosition() + lf);
        right_front.setTargetPosition(right_front.getCurrentPosition() + rf);
        left_back.setTargetPosition(left_back.getCurrentPosition() + lb);
        right_back.setTargetPosition(right_back.getCurrentPosition() + rb);
    }

    private void setPowerAll(double power) {
        left_front.setPower(Math.abs(power));
        right_front.setPower(Math.abs(power));
        left_back.setPower(Math.abs(power));
        right_back.setPower(Math.abs(power));
    }

    private boolean allMotorsBusy() {
        return left_front.isBusy() && right_front.isBusy() && left_back.isBusy() && right_back.isBusy();
    }
}
