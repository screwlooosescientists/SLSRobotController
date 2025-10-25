package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name="Simple Autonomous", group="Autonomous")
public class AutonomousTestblauw extends LinearOpMode {

    private DcMotor left_front = null;
    private DcMotor right_front = null;
    private DcMotor right_back = null;
    private DcMotor left_back = null;

    @Override
    public void runOpMode() {
        // Hardware mapping
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class,"right_back");

        // Zet de richting goed (afhankelijk van hoe motoren gemonteerd zijn)
        left_front.setDirection(DcMotor.Direction.FORWARD);
        right_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotorSimple.Direction.REVERSE);
        right_back.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Gereed om te starten");
        telemetry.update();

        // Wacht op start
        waitForStart();

        // Draai naar links
        turnLeft(0.5, 1030);

        // Rijdt nog een beetje vooruit
        package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


        @Autonomous(name="Simple Autonomous", group="Autonomous")
        public class AutonomousTestblauw extends LinearOpMode {

            private DcMotor left_front = null;
            private DcMotor right_front = null;
            private DcMotor right_back = null;
            private DcMotor left_back = null;

            @Override
            public void runOpMode() {
                // Hardware mapping
                left_front = hardwareMap.get(DcMotor.class, "left_front");
                right_front = hardwareMap.get(DcMotor.class, "right_front");
                left_back = hardwareMap.get(DcMotor.class, "left_back");
                right_back = hardwareMap.get(DcMotor.class,"right_back");

                // Zet de richting goed (afhankelijk van hoe motoren gemonteerd zijn)
                left_front.setDirection(DcMotor.Direction.FORWARD);
                right_front.setDirection(DcMotor.Direction.REVERSE);
                left_back.setDirection(DcMotorSimple.Direction.REVERSE);
                right_back.setDirection(DcMotorSimple.Direction.FORWARD);

                telemetry.addData("Status", "Gereed om te starten");
                telemetry.update();

                // Wacht op start
                waitForStart();

                driveForward(0.5, 2000);

                // Draai naar links
                turnLeft(0.5, 1030);

                // Rijdt nog een beetje vooruit
                driveForward(0.5, 2000);

                telemetry.addData("Status", "Autonomous klaar");
                telemetry.update();
            }

            private void turnLeft(double power, int timeMs) {
                left_front.setPower(power);
                right_front.setPower(-power);
                left_back.setPower(power);
                right_back.setPower(-power);
                sleep(timeMs);
                stopDriving();
            }

            // --- Hulpmethoden ---
            private void driveForward(double power, int timeMs) {
                left_front.setPower(power);
                right_front.setPower(power);
                left_back.setPower(power);
                right_back.setPower(power);
                telemetry.addData("status", "drive forward");
                telemetry.update();
                sleep(timeMs);
                stopDriving();
            }

            private void turnRight(double power, int timeMs) {
                left_front.setPower(power);
                right_front.setPower(-power);
                left_back.setPower(power);
                right_back.setPower(-power);
                sleep(timeMs);
                stopDriving();
            }

            private void stopDriving() {
                left_front.setPower(0);
                right_front.setPower(0);
                left_back.setPower(0);
                right_back.setPower(0);
            }
        }


        telemetry.addData("Status", "Autonomous klaar");
        telemetry.update();
    }

    private void turnLeft(double power, int timeMs) {
        left_front.setPower(power);
        right_front.setPower(-power);
        left_back.setPower(power);
        right_back.setPower(-power);
        sleep(timeMs);
        stopDriving();
    }

    // --- Hulpmethoden ---
    private void driveForward(double power, int timeMs) {
        left_front.setPower(power);
        right_front.setPower(power);
        left_back.setPower(power);
        right_back.setPower(power);
        telemetry.addData("status", "drive forward");
        telemetry.update();
        sleep(timeMs);
        stopDriving();
    }

    private void turnRight(double power, int timeMs) {
        left_front.setPower(power);
        right_front.setPower(-power);
        left_back.setPower(power);
        right_back.setPower(-power);
        sleep(timeMs);
        stopDriving();
    }

    private void stopDriving() {
        left_front.setPower(0);
        right_front.setPower(0);
        left_back.setPower(0);
        right_back.setPower(0);
    }
}
