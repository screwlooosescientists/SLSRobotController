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
    }

    public void ShootKatapult(double fireButton)
    {
        Motor.setPower(fireButton);
    }

    public void Laden(boolean wipen)
    {
        if(wipen)
        {
            Wipper.setPosition(1);
        }
        else
        {
            Wipper.setPosition(0);
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
