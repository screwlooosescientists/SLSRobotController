package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Data.HardwareMap;

@Autonomous(name = "Red1", group = "BioBuzz")
public class Red_1 extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException
    {
      DriveForward (1);
    }
}