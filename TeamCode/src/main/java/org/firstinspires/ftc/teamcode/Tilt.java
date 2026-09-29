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
       tiltLinks.setPosition(0); // TODO Position meten met servo tester
       tiltRechts.setPosition(0); // TODO Position meten met servo tester
   }

   public void TiltDown()
    {
        tiltRechts.setPosition(1); // TODO Position meten met servo tester
        tiltLinks.setPosition(1); // TODO Position meten met servo tester
    }
}


