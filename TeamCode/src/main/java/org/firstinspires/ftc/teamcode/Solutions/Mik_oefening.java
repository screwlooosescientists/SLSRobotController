package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name="MOK" , group="Oefening")
public class Mik_oefening extends LinearOpMode {

    private DcMotor leftdrive = null;

    private DcMotor rightdrive = null;

    private DcMotor rightback = null;

    private DcMotor leftback = null;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftdrive = hardwareMap.get(DcMotor.class, "left_front");
        rightdrive = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");

    }
}
