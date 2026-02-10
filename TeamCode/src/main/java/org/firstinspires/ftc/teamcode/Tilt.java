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
       tiltLinks.setPosition(0.3);
       tiltRechts.setPosition(0.3);
   }

   public void TiltDown()
    {
        tiltRechts.setPosition(0.66);
        tiltLinks.setPosition(0.66);
    }
}


