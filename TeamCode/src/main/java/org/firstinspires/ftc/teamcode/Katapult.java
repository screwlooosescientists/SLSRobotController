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

    public void ShootKatapult(boolean fireButton)
    {
        if(fireButton)
        {
            Motor.setPower(0.7);
        }
        else
        {
            Motor.setPower(0);
        }
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
}
