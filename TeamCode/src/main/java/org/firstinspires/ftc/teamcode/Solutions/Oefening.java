package org.firstinspires.ftc.teamcode.Solutions;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name ="Pepijn", group ="linear opmode")

public class Oefening extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();;

   private DcMotor left_front = null ;

    private DcMotor right_back = null ;

    private DcMotor left_back;

    private DcMotor right_front = null ; ;
    @Override
    public void runOpMode() {
telemetry . addData( "status","Initialized");
telemetry . update();

left_front  = hardwareMap.get(DcMotor.class, "left_front");
right_front = hardwareMap .get(DcMotor.class, "right_front");
left_back = hardwareMap  .get(DcMotor.class, "left_back");
right_back = hardwareMap .get(DcMotor.class, "right_back");


left_front.setDirection(DcMotor.Direction.REVERSE);
right_front.setDirection(DcMotorSimple.Direction.FORWARD);
left_back.setDirection(DcMotorSimple.Direction.FORWARD);
right_back.setDirection(DcMotorSimple.Direction.REVERSE);
waitForStart();
runtime.reset();
        while (opModeIsActive()) {
            double leftPower;
            double rightPower;

            double drive = -gamepad1.left_stick_x;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            left_back.setPower(leftPower);
            left_front.setPower(leftPower);
            right_back.setPower(rightPower);
            right_front.setPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

        }
    }
}
