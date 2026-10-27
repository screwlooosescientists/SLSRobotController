package org.firstinspires.ftc.teamcode.Examples;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="FTC_GastLessen", group="TeleOp")
public class FTC_Knip_GastLessen extends LinearOpMode {


    private DcMotor LinksVoor = null;
    private DcMotor RechtsVoor = null;
    private DcMotor Shooter = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        LinksVoor  = hardwareMap.get(DcMotor.class, "left_drive");
        RechtsVoor = hardwareMap.get(DcMotor.class, "right_drive");
        Shooter = hardwareMap.get(DcMotor.class, "outtake");


        LinksVoor.setDirection(DcMotor.Direction.REVERSE);
        RechtsVoor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            double Powerleft_stick_y;
            double Powerleft_stick_x;
            double Powerright_stick_x;

            Powerleft_stick_y = gamepad1.left_stick_y;
            Powerleft_stick_x = gamepad1.left_stick_x;
            Powerright_stick_x = gamepad1.right_stick_x;

            double LeftFront = Powerleft_stick_y - Powerleft_stick_x - Powerright_stick_x;
            double RightFront = Powerleft_stick_y + Powerleft_stick_x + Powerright_stick_x;


            LinksVoor.setPower(LeftFront);
            RechtsVoor.setPower(RightFront);

            if(gamepad1.right_bumper)
            {
                Shooter.setPower(1.0);
            }

            else if(gamepad1.left_bumper)
            {
                Shooter.setPower(0.0);
            }

        }
    }
}