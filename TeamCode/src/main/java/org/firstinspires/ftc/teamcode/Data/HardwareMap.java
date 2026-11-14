package org.firstinspires.ftc.teamcode.Data;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SLS_Classes.DcMotorSLS;

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
    public static DcMotorSLS shooterMotor;
    public static Servo fireServo;

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


        shooterMotor = new DcMotorSLS(
                hardwareMap.get(DcMotorEx.class, "Shooter")
        );

        fireServo = hardwareMap.get(Servo.class,"fire_Shooter");
        //todo add this servo to the hardware map!!

        // =========================
        // INTAKE
        // =========================

        intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
    }
}