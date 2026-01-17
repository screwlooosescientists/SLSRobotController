package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Tilt
{
   private Servo tiltLinks = null;
   private Servo tiltRechts = null;

   public Tilt(Servo tiltLinks, Servo tiltRechts)
   {
       this.tiltLinks = tiltLinks;
       this.tiltRechts = tiltRechts;
   }

   public void TiltUp()
   {
       tiltLinks.setPosition(0);
       tiltRechts.setPosition(0);
   }

   public void TiltDown()
    {
        tiltRechts.setPosition(0.25);
        tiltLinks.setPosition(0.25);
    }
}


