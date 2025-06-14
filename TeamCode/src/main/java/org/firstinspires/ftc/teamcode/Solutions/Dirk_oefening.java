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
            double Power;

            Power = gamepad1.left_stick_y;

            leftdrive.setPower(Power);
            rightdrive.setPower(Power);
            rightback.setPower(Power);
            leftback.setPower(Power);

            telemetry.addData("Motors", "left (%.2f), right (%.2f)", Power);
            telemetry.update();

        }
    }

}