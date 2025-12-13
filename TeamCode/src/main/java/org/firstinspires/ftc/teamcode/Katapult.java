package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Katapult {
    private DcMotor Motor = null;
    private Servo Wipper = null;

    public Katapult(DcMotor motor, Servo wipper)
    {
        this.Motor = motor;
        this.Wipper = wipper;
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void ShootKatapult(boolean fireButton)
    {
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if(fireButton)
        {
            Motor.setPower(0.7);
        }
        else
        {
            Motor.setPower(0);
            Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void LowerKatapult(boolean lowerButton, int position)
    {

        if(lowerButton)
        {
            Motor.setTargetPosition(position);
            Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Motor.setPower(1);
        }
        else
        {
            Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Motor.setPower(0);
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
            Wipper.setPosition(0.2);
        }
    }
}
