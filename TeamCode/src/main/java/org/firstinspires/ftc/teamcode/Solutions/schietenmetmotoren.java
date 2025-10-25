package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="rijden en vuuren", group="Oefening")
public class schietenmetmotoren extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;

    private DcMotor AeroEngineLeft = null;
    private DcMotor AeroEngineRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");

        AeroEngineLeft = hardwareMap.get(DcMotor.class, "AeroEngineLeft");
        AeroEngineRight = hardwareMap.get(DcMotor.class, "AeroEngineRight");

        AeroEngineLeft.setDirection(DcMotor.Direction.FORWARD);
        AeroEngineRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = gamepad1.right_stick_x;

            double LeftFront = Powerleft_stick_y - Powerleft_stick_x - Powerright_stick_x;
            double LeftBack = Powerleft_stick_y + Powerleft_stick_x - Powerright_stick_x;
            double RightFront = Powerleft_stick_y + Powerleft_stick_x + Powerright_stick_x;
            double RightBack = Powerleft_stick_y - Powerleft_stick_x + Powerright_stick_x;

            leftfront.setPower(LeftFront);
            rightfront.setPower(RightFront);
            rightback.setPower(RightBack);
            leftback.setPower(LeftBack);

            // AeroEngine aansturing via △ en ✕
            if (gamepad1.triangle) {
                AeroEngineLeft.setPower(1.0);
                AeroEngineRight.setPower(1.0);
            } else if (gamepad1.cross) {
                AeroEngineLeft.setPower(0.0);
                AeroEngineRight.setPower(0.0);
            }
        }
    }
}
