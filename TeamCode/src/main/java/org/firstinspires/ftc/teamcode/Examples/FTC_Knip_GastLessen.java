package org.firstinspires.ftc.teamcode.Examples;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="FTC_GastLessen", group="TeleOp")
public class FTC_Knip_GastLessen extends LinearOpMode {


    private DcMotor LinksVoor = null;
    private DcMotor RechtsVoor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        LinksVoor  = hardwareMap.get(DcMotor.class, "left_drive");
        RechtsVoor = hardwareMap.get(DcMotor.class, "right_drive");


        LinksVoor.setDirection(DcMotor.Direction.REVERSE);
        RechtsVoor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;


            LinksVoor.setPower(leftPower);
            RechtsVoor.setPower(rightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}