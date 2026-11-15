package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake
{
    private DcMotor intakeMotor;
    private final Servo intaker;

    private enum IntakeState
    {
        IDLE,
        RUNNING
    }

    public void intakerOn()
    {
        //TODO tune servo pos
        intaker.setPosition(1);
    }

    public void stopintaker()
    {
        //TODO tune servo pos
        intaker.setPosition(0);
    }

    private IntakeState currentState = IntakeState.IDLE;

    private double maxSpeed = 1.0;

    public Intake(DcMotor intakeMotor, Servo intaker)
    {
        this.intakeMotor = intakeMotor;
        intakeMotor.setPower(0);
        this.intaker = intaker;
    }

    // Commands
    public void start()
    {
        currentState = IntakeState.RUNNING;
    }

    public void stop()
    {
        currentState = IntakeState.IDLE;
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
                intakeMotor.setPower(0);
                break;

            case RUNNING:
                intakeMotor.setPower(-maxSpeed);
                break;
        }
    }
}