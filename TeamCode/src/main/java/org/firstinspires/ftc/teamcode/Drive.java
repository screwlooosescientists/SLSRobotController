package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive
{
    private DcMotor leftfront;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotor leftback;

    public Drive(DcMotor leftfront, DcMotor rightfront,
                 DcMotor rightback, DcMotor leftback)
    {
        this.leftfront = leftfront;
        this.leftback = leftback;
        this.rightfront = rightfront;
        this.rightback = rightback;
    }

    public void Update(double Y1, double X1, double X2)
    {
        double leftFrontPower = Y1 + X1 + X2;
        double leftBackPower = Y1 - X1 + X2;
        double rightFrontPower = Y1 - X1 - X2;
        double rightBackPower = Y1 + X1 - X2;

        // Normalize motor powers
        double maxPower = Math.max(1.0, Math.max(
                Math.max(Math.abs(leftFrontPower), Math.abs(leftBackPower)),
                Math.max(Math.abs(rightFrontPower), Math.abs(rightBackPower))
        ));

        leftfront.setPower(leftFrontPower / maxPower);
        leftback.setPower(leftBackPower / maxPower);
        rightfront.setPower(rightFrontPower / maxPower);
        rightback.setPower(rightBackPower / maxPower);
    }
}