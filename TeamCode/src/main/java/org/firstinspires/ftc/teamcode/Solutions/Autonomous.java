package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Simple Autonomous", group="Tutorial")
class SimpleAutonomous extends LinearOpMode {

    private DcMotor left_Front = null;
    private DcMotor Right_Front = null;
    private DcMotor Left_Back = null;
    private DcMotor Right_Back = null;

    @Override
    public void runOpMode() {
        // Hardware mapping
        left_Front = hardwareMap.get(DcMotor.class, "left_drive");
        Right_Front = hardwareMap.get(DcMotor.class, "Right_drive");
        Left_Back = hardwareMap.get(DcMotor.class,"Left_Back");
        Right_Back = hardwareMap.get(DcMotor.class, "Right_Back");

        // Zet de richting goed (afhankelijk van hoe motoren gemonteerd zijn)
        left_Front.setDirection(DcMotor.Direction.FORWARD);
        Right_Front.setDirection(DcMotor.Direction.REVERSE);
        Right_Back.setDirection(DcMotor.Direction.FORWARD);
        Left_Back.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Gereed om te starten");
        telemetry.update();

        // Wacht op start
        waitForStart();

        // Draai naar rechts
        turnRight(0.5, 200);

        // Rijdt nog een beetje vooruit
        driveForward(0.5, 700);

        telemetry.addData("Status", "Autonomous klaar");
        telemetry.update();
    }

    // --- Hulpmethoden ---
    private void driveForward(double power, int timeMs) {
        left_Front.setPower(power);
        Right_Front.setPower(power);
        Left_Back.setPower(power);
        Right_Back.setPower(power);
        sleep(timeMs);
        stopDriving();
    }

    private void turnRight(double power, int timeMs) {
        left_Front.setPower(power);
        Right_Front.setPower(-power);
        Left_Back.setPower(-power);
        Right_Back.setPower(-power);
        sleep(timeMs);
        stopDriving();
    }

    private void stopDriving() {
        left_Front.setPower(0);
        Right_Front.setPower(0);
        Left_Back.setPower(0);
        Right_Back.setPower(0);
    }
}
