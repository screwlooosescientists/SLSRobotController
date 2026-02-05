package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import static org.firstinspires.ftc.teamcode.AutonomousDrive.*;
import static org.firstinspires.ftc.teamcode.Data.AutonomousConfiguration.MAX_AUTO_SPEED;
import static org.firstinspires.ftc.teamcode.Data.AutonomousConfiguration.TicksPerRev;

public class AutonomousDrive {

    private DcMotor frontLeftDrive = null;  //  Used to control the left front drive wheel
    private DcMotor frontRightDrive = null;  //  Used to control the right front drive wheel
    private DcMotor backLeftDrive = null;  //  Used to control the left back drive wheel
    private DcMotor backRightDrive = null;

    public AutonomousDrive(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL)
    {
        this.frontRightDrive = fR;
        this.backLeftDrive = bL;
        this.backRightDrive = bR;
        this.frontLeftDrive = fL;
    }

    //double LeftFront = -(Y1 - X1 + X2);
    // double LeftBack = -(Y1 + X1 + X2);
    //double RightFront = -(-Y1 - X1 + X2);
    //double RightBack = -(-Y1 + X1 + X2);

    /**
     * Moves the robot forward
     * @param rotations amount of wheel rotations the robot should move
     */
    public void DriveForward(double rotations)
    {
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        frontRightDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        backLeftDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        backRightDrive.setTargetPosition(-(int)(rotations * TicksPerRev));

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRightDrive.setPower(MAX_AUTO_SPEED);
        frontRightDrive.setPower(MAX_AUTO_SPEED);
        backLeftDrive.setPower(MAX_AUTO_SPEED);
        frontLeftDrive.setPower(MAX_AUTO_SPEED);

        while((frontRightDrive.isBusy() || frontLeftDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy()))
        {

        }
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);

    }

    public void DriveLeft(double rotations)
    {
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        frontRightDrive.setTargetPosition((int)(rotations * TicksPerRev));
        backLeftDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        backRightDrive.setTargetPosition((int)(rotations * TicksPerRev));

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRightDrive.setPower(MAX_AUTO_SPEED);
        frontRightDrive.setPower(MAX_AUTO_SPEED);
        backLeftDrive.setPower(MAX_AUTO_SPEED);
        frontLeftDrive.setPower(MAX_AUTO_SPEED);

        while((frontRightDrive.isBusy() || frontLeftDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy()))
        {

        }
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);

    }

    public void DriveRight(double rotations)
    {
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition((int)(rotations * TicksPerRev));
        frontRightDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        backLeftDrive.setTargetPosition((int)(rotations * TicksPerRev));
        backRightDrive.setTargetPosition(-(int)(rotations * TicksPerRev));

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRightDrive.setPower(MAX_AUTO_SPEED);
        frontRightDrive.setPower(MAX_AUTO_SPEED);
        backLeftDrive.setPower(MAX_AUTO_SPEED);
        frontLeftDrive.setPower(MAX_AUTO_SPEED);

        while((frontRightDrive.isBusy() || frontLeftDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy()))
        {

        }
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);



    }

    public void Turn(double rotations)
    {
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        frontRightDrive.setTargetPosition(-(int)(rotations * TicksPerRev));
        backLeftDrive.setTargetPosition((int)(rotations * TicksPerRev));
        backRightDrive.setTargetPosition((int)(rotations * TicksPerRev));

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRightDrive.setPower(MAX_AUTO_SPEED);
        frontRightDrive.setPower(MAX_AUTO_SPEED);
        backLeftDrive.setPower(MAX_AUTO_SPEED);
        frontLeftDrive.setPower(MAX_AUTO_SPEED);

        while((frontRightDrive.isBusy() || frontLeftDrive.isBusy() || backLeftDrive.isBusy() || backRightDrive.isBusy()))
        {

        }
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);



    }
}
