package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Katapult {
    private DcMotor Motor = null;

    public Katapult(DcMotor motor)
    {
        this.Motor = motor;
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
}
