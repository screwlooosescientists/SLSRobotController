package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SLS_Classes.DcMotorSLS;

public class Katapult {
    private DcMotor Motor = null;
    private DcMotor Motor2 = null;

    private DcMotorSLS Motorsls1 = null;
    private DcMotorSLS Motorsls2 = null;

    private Servo Wipper = null;
    private Servo WipperOG = null;

    public Katapult(DcMotor motor, DcMotor motor2, Servo wipper, Servo wipperOG)
    {
        this.Motor = motor;
        this.Motor2 = motor2;
        this.Wipper = wipper;
        this.WipperOG = wipperOG;
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }

    public void ShootKatapult(double fireButton)
    {
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor.setPower(fireButton);
        Motor2.setPower(-fireButton);
    }

    public void LowerKatapult(boolean lowerButton, int position)
    {

        if(lowerButton)
        {
            Motor.setTargetPosition(position);
            Motor2.setTargetPosition(-position);
            Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Motor.setPower(0.5);
            Motor2.setPower(0.5);
        }
        else
        {
            Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Motor.setPower(0);
            Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Motor2.setPower(0);
        }
    }

    public void Laden(boolean wipen)
    {
        if(wipen)
        {
            Wipper.setPosition(0.6);
        }
        else
        {
            Wipper.setPosition(0);
        }
    }

    public void OpenWipper(int stage)
    {
        switch (stage)
        {
            case 0:

                Wipper.setPosition(1);
                WipperOG.setPosition(1);

                break;
            case 1:

                Wipper.setPosition(0.7);
                WipperOG.setPosition(1);

                break;

            case 2:

                Wipper.setPosition(0.7);
                WipperOG.setPosition(0.7);

                break;
        }
    }

    /**
     * Shoots the catapult once
     */
    public void Tak()
    {
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor.setTargetPosition(288);
        Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Motor.setPower(1);

        while(Motor.isBusy())
        {

        }
    }
}
