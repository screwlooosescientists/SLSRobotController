//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//@TeleOp(name="Katapult Control", group="TeleOp")
//public class Katapult extends LinearOpMode {
//
//    private DcMotor katapultMotor = null;
//
//    @Override
//    public void runOpMode() {
//        // Hardware mapping
//        katapultMotor = hardwareMap.get(DcMotor.class, "katapult");
//
//        // Richting en gedrag
//        katapultMotor.setDirection(DcMotor.Direction.REVERSE);
//        katapultMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        telemetry.addData("Status", "Gereed om te starten");
//        telemetry.update();
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            // 🌀 Opwinden met X
//            if (gamepad1.x) {
//                katapultMotor.setPower(1.0);
//                telemetry.addData("Katapult", "Opwinden...");
//            } else {
//                katapultMotor.setPower(0);
//            }
//
//            telemetry.update();
//        }
//    }
//}