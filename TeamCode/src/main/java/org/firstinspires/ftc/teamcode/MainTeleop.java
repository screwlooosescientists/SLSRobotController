package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Teleop", group="Decode")
public class MainTeleop extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;
    private Drive drive = null;

    private DcMotor OuttakeMotor = null;
    private shooter shooter = null;

    private boolean dpadrightVorigeStatus = false;
    private boolean dpadleftVorigeStatus = false;
    private double maxShoterSnelheid = 0.5;




    //vars
    int stage = 0;
    boolean leftbumbperIsPressed;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class, "left_back");
        OuttakeMotor = hardwareMap.get(DcMotor.class, "Shooter");
        leftback.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);


        drive = new Drive(leftfront, rightfront, rightback, leftback);
        shooter = new shooter(OuttakeMotor);

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = gamepad1.right_stick_x;

            if(gamepad2.left_trigger > 0.5)
            {
                shooter.ShooterUit();
            }

            else if(gamepad2.left_bumper)
            {
                shooter.ShooterUit();
            }


            if (gamepad1.dpad_right && !dpadrightVorigeStatus) {
                maxShoterSnelheid += 0.1;
                if (maxShoterSnelheid > 1.0) maxShoterSnelheid = 1.0;
            }
            dpadrightVorigeStatus = gamepad1.dpad_right;


            if (gamepad1.dpad_left && !dpadleftVorigeStatus) {
                maxShoterSnelheid -= 0.1;
                if (maxShoterSnelheid < 0.0) maxShoterSnelheid = 0.0;
            }
            dpadleftVorigeStatus = gamepad1.dpad_left;


            drive.drive(Powerleft_stick_y, Powerleft_stick_x, Powerright_stick_x);
            telemetry.update();


        }
    }
}
