package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class shooter
{
    private DcMotor OuttakeMotor = null;

    public shooter(DcMotor ShooterMotor)
    {
        this.OuttakeMotor = ShooterMotor;
    }

    public void ShooterAan()
    {
        OuttakeMotor.setPower(-1.0);
    }

    public void ShooterUit()
    {
        OuttakeMotor.setPower(0.0);
    }
}
