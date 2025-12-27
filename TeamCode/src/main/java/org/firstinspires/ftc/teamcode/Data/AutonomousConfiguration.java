package org.firstinspires.ftc.teamcode.Data;

public class AutonomousConfiguration {

    //encoderticks per rotation
    public static final double TicksPerRev = 1120;

    //Proportional gains, (output is proportional to the error)
    public static final double SPEED_GAIN  =  0.025  ;   //  Forward Speed Control "Gain"
    public static final double STRAFE_GAIN =  0.02 ;   //  Strafe Speed Control "Gain".
    public static final double TURN_GAIN   =  0.015  ;   //  Turn Control "Gain".

    //Max speeds
    public static final double MAX_AUTO_SPEED = 0.7  ;   //  Clip the approach speed to this max value
    public static final double MAX_AUTO_STRAFE= 0.7;   //  Clip the strafing speed to this max value
    public static final double MAX_AUTO_TURN  = 0.4;   //  Clip the turn speed to this max value

}
