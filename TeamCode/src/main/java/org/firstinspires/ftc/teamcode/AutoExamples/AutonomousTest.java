package org.firstinspires.ftc.teamcode.AutoExamples;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.*;

import java.util.List;

@Autonomous(name = "Autonoom test camera")
public class AutonomousTest extends LinearOpMode {
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    // motors
    private DcMotor leftfront = null;
    private DcMotor leftback = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;



    //delta x and y variables
    double dx, dy;
    Position p = new Position();

    //constants
    final double marge = 5;
    final double targetX = -20, targetY = -30;
    final double kP = 0.01;

    @Override
    public void runOpMode() {

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        rightback = hardwareMap.get(DcMotor.class, "right_back");
        leftback = hardwareMap.get(DcMotor.class,"left_back");

        // --- Initialize AprilTag ---
        aprilTag = new AprilTagProcessor.Builder()
                .setCameraPose( //todo check camera mounting
                        new Position(DistanceUnit.CM, 0, 0, 0, 0),
                        new YawPitchRollAngles(AngleUnit.DEGREES, 0, -90, 0, 0)
                )
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTag)
                .build();

        telemetry.addData("Status: ", "Ready");
        telemetry.update();

        waitForStart();

        AprilTagDetection tag = getTagPose(aprilTag, 20); // Example: Look for tag ID = 3

        if(tag != null)
        {
            p = tag.robotPose.getPosition();
        }
        dx = targetX - p.x;
        dy = targetY - p.y;
        while(Math.abs(dx) > marge && Math.abs(dy) > marge || true)
        {
           tag = getTagPose(aprilTag, 20); // Example: Look for tag ID = 3
            if(tag != null)
            {
                p = tag.robotPose.getPosition();
            }
            dx = targetX - p.x;
            dy = targetY - p.y;

            double drivex = dx * kP;
            double drivey = dy * kP;

            if(tag != null)
            {
                double [] xy  = SLS_Utilities.rotateVector(drivex, drivey, -tag.robotPose.getOrientation().getYaw(AngleUnit.DEGREES));
                drivex = xy[0];
                drivey = xy[1];
                telemetry.addData("angle rotated: ", -tag.robotPose.getOrientation().getYaw(AngleUnit.DEGREES) );
            }

            //normalization of the vector
            double max = Math.max(1.0, Math.abs(drivex) + Math.abs(drivey));
            drivex /= max;
            drivey /= max;


            double Leftfrond = -(drivey - drivex);
            double Leftback = -(drivey + drivex);
            double Rightfrond = -(-drivey - drivex);
            double Rightback = -(-drivey + drivex);

            telemetry.addData("dx: ", dx);
            telemetry.addData("dy: ", dy);
            telemetry.addData("positionx: ", p.x);
            telemetry.addData("positionY: ", p.y);
            telemetry.update();

            leftfront.setPower(Leftfrond);
            leftback.setPower(Leftback);
            rightfront.setPower(Rightfrond);
            rightback.setPower(Rightback);

        }

    }

    public AprilTagDetection getTagPose(AprilTagProcessor processor, int targetId) {
        List<AprilTagDetection> detections = processor.getDetections();

        for (AprilTagDetection d : detections) {
            if (d.id == targetId) {
                return d;
            }
        }
        return null;
    }
}
