package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Shooter
{
    private DcMotor outtakeMotor;

    private enum ShooterState
    {
        IDLE,
        SPIN_UP
    }

    private ShooterState currentState = ShooterState.IDLE;

    private double maxSpeed = 1.0;

    public Shooter(DcMotor shooterMotor)
    {
        this.outtakeMotor = shooterMotor;
        outtakeMotor.setPower(0);
    }

    // Commands
    public void spinUp()
    {
        currentState = ShooterState.SPIN_UP;
    }

    public void pause()
    {
        currentState = ShooterState.IDLE;
    }

    public void setMaxSpeed(double speed)
    {
        maxSpeed = Math.max(0.0, Math.min(1.0, speed));
    }

    // State machine
    public void Update()
    {
        switch (currentState)
        {
            case IDLE:
                outtakeMotor.setPower(0);
                break;

            case SPIN_UP:
                outtakeMotor.setPower(maxSpeed);
                break;
        }
    }
}