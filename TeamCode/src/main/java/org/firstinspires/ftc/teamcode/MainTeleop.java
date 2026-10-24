package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Tele op", group="Decode")
public class MainTeleop extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;
    private Drive drive = null;

    private DcMotor IntakeMotor = null;
    private Intake intake = null;

    private boolean dpadUpVorigeStatus = false;
    private boolean dpadDownVorigeStatus = false;
    private double maxSnelheid = 0.5;

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
        IntakeMotor = hardwareMap.get(DcMotor.class,"IntakeMotor");
        leftfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightfront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightback.setDirection(DcMotorSimple.Direction.REVERSE);
        // leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightback.setMode()
         //
         //

        drive = new Drive(leftfront, rightfront, rightback, leftback);
        intake = new Intake(IntakeMotor);

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = -gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = gamepad1.right_stick_x;

            if(gamepad2.right_trigger > 0.5)
            {
                intake.IntakeAan();
            }
            else if(gamepad2.right_bumper)
            {
                intake.IntakeUit();
            }

            if (gamepad1.dpad_up && !dpadUpVorigeStatus) {
                maxSnelheid += 0.1;
                if (maxSnelheid > 1.0) maxSnelheid = 1.0;
            }
            dpadUpVorigeStatus = gamepad1.dpad_up;


            if (gamepad1.dpad_down && !dpadDownVorigeStatus) {
                maxSnelheid -= 0.1;
                if (maxSnelheid < 0.0) maxSnelheid = 0.0;
            }
            dpadDownVorigeStatus = gamepad1.dpad_down;


            drive.drive(Powerleft_stick_y, Powerleft_stick_x, Powerright_stick_x);
            telemetry.update();


        }


    }

}

