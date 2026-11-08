package org.firstinspires.ftc.teamcode.Data;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class HardwareMap {

    private HardwareMap() {
        /* This utility class should not be instantiated */
    }

    // FTC hardware map reference
    private static com.qualcomm.robotcore.hardware.HardwareMap hardwareMap;

    // Drivetrain
    public static DcMotor leftFront;
    public static DcMotor rightFront;
    public static DcMotor leftBack;
    public static DcMotor rightBack;

    // Shooter
    public static DcMotor shooterMotor;

    // Intake
    public static DcMotor intakeMotor;


    /**
     * Initializes all robot hardware.
     */
    public static void Init(
            com.qualcomm.robotcore.hardware.HardwareMap map) {

        hardwareMap = map;

        // =========================
        // DRIVETRAIN
        // =========================

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // =========================
        // SHOOTER
        // =========================

        shooterMotor = hardwareMap.get(DcMotor.class, "Shooter");

        // =========================
        // INTAKE
        // =========================

        intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
    }
}