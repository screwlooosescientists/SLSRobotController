package org.firstinspires.ftc.teamcode.AutoExamples;

public class SLS_Utilities {
    /**
     * Rotates a 2D vector by a given angle (in degrees).
     *
     * @param x        original x value
     * @param y        original y value
     * @param degrees  how many degrees to rotate (positive = counter-clockwise)
     * @return a double[] containing {newX, newY}
     */
    public static double[] rotateVector(double x, double y, double degrees) {

        // Convert degrees to radians.
        // Java's math functions use radians, not degrees.
        double radians = Math.toRadians(degrees);

        // Calculate sine and cosine of the angle
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        // Apply the rotation
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;

        // Return both values
        return new double[]{ newX, newY };
    }

}
