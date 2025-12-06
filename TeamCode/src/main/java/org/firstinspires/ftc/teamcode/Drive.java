package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive
{
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor leftback = null;

    public Drive(DcMotor leftfront, DcMotor rightfront, DcMotor rightback, DcMotor leftback)
    {
        this.leftfront = leftfront;
        this.leftback = leftback;
        this.rightfront = rightfront;
        this.rightback = rightback;
    }

    public void drive(double Y1, double X1, double X2)
    {
        double LeftFront = -(Y1 - X1 + X2);
        double LeftBack = -(Y1 + X1 + X2);
        double RightFront = -(-Y1 - X1 + X2);
        double RightBack = -(-Y1 + X1 + X2);

        leftfront.setPower(LeftFront);
        rightfront.setPower(RightFront);
        rightback.setPower(RightBack);
        leftback.setPower(LeftBack);
    }
}
