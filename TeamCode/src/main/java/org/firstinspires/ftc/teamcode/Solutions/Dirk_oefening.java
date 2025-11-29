package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="DIRK" , group="Oefening")
public class Dirk_oefening extends LinearOpMode {
    private DcMotor leftdrive = null;
    private DcMotor rightdrive =null;
    private DcMotor rightback =null;
    private DcMotor leftback=null;

    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftdrive = hardwareMap.get(DcMotor.class, "left_front");
        rightdrive = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft;
            double powerright;

            Powerleft = gamepad1.left_stick_y;
            powerright = gamepad1.right_stick_x;

            leftdrive.setPower(Powerleft);
            rightdrive.setPower(Powerleft);
            rightback.setPower(Powerleft);
            leftback.setPower(Powerleft);

            leftdrive.setPower(powerright);
            rightdrive.setPower(powerright);
            rightback.setPower(powerright);
            leftback.setPower(powerright);




        }
    }

}