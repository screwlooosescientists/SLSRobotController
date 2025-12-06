package org.firstinspires.ftc.teamcode.AutoExamples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.*;

import java.util.List;

@Autonomous(name = "Simple AprilTag Locator")
public class GetTagLocationOpmode extends LinearOpMode {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {

        // --- Initialize AprilTag ---
        aprilTag = new AprilTagProcessor.Builder()
                .setCameraPose(
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

        while (opModeIsActive()) {

            AprilTagDetection tag = getTagPose(aprilTag, 3); // Example: Look for tag ID = 3

            if (tag != null) {
                Position p = tag.robotPose.getPosition();
                telemetry.addData("Tag 3 Pos",
                        "X: %.1f   Y: %.1f   Z: %.1f",
                        p.x, p.y, p.z);
            } else {
                telemetry.addLine("Tag 3 not visible");
            }

            telemetry.update();
            sleep(20);
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
