package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake
{
    private DcMotor IntakeMotor = null;

    public Intake(DcMotor IntakeMotor)
    {
        this.IntakeMotor = IntakeMotor;
    }

    public void IntakeAan()
    {
        IntakeMotor.setPower(-1.0);
    }

    public void IntakeUit()
    {
        IntakeMotor.setPower(0.0);
    }
}
