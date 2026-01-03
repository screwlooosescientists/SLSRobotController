package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Katapult;

@TeleOp(name="Teleop", group="Decode")
public class MainTeleop extends LinearOpMode {

    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;
    private Drive drive = null;
    private Katapult Schieter = null;
    private DcMotor SchietMotor = null;
    private DcMotor SchietMotor2 = null;
    private Servo WipMotor = null;
    private Servo WipMotorOg = null;

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
        SchietMotor = hardwareMap.get(DcMotor.class, "katapult");
        SchietMotor2 = hardwareMap.get(DcMotor.class, "KatapultOG" );
        WipMotor = hardwareMap.get(Servo.class, "Wipper");
        WipMotorOg = hardwareMap.get(Servo.class, "wipperOG");

        Schieter = new Katapult(SchietMotor,SchietMotor2 , WipMotor, WipMotorOg);
        drive = new Drive(leftfront, rightfront, rightback, leftback);

        waitForStart();
        while (opModeIsActive()) {
            double Powerleft_stick_y = gamepad1.left_stick_y;
            double Powerleft_stick_x = gamepad1.left_stick_x;
            double Powerright_stick_x = gamepad1.right_stick_x;

            drive.drive(Powerleft_stick_y, Powerleft_stick_x, Powerright_stick_x);


            if (gamepad2.right_trigger != 0){
                Schieter.ShootKatapult(gamepad2.right_trigger);

            }
            else {
                SchietMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                SchietMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }


            if(gamepad2.left_bumper && stage == 0 && !leftbumbperIsPressed)
            {
                stage = 1;
                leftbumbperIsPressed = true;
            }
            else if(gamepad2.left_bumper && stage == 1  && !leftbumbperIsPressed)
            {
                stage = 2;
                leftbumbperIsPressed = true;
            }
            else if(gamepad2.left_bumper && stage == 2  && !leftbumbperIsPressed)
            {
                stage = 0;
                leftbumbperIsPressed = true;
            }
            else if(leftbumbperIsPressed && !gamepad2.left_bumper)
            {
                leftbumbperIsPressed = false;
            }

            Schieter.OpenWipper(stage);

            telemetry.addData("Motor pos: ", SchietMotor.getCurrentPosition());
            telemetry.addData("Motor pos2: ", SchietMotor2.getCurrentPosition());
            telemetry.update();
        }
    }
}
