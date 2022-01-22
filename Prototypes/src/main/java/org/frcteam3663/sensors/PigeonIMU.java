package org.frcteam3663.sensors;

import java.text.MessageFormat;

import com.ctre.phoenix.ErrorCode;

public class PigeonIMU implements IMU
{
    private com.ctre.phoenix.sensors.PigeonIMU imu;

    private static final int X_ANGLE = 0;
    private static final int Y_ANGLE = 1;
    private static final int Z_ANGLE = 2;
    private double angleBuffer[];

    public PigeonIMU(int canId)
    {
        imu = new com.ctre.phoenix.sensors.PigeonIMU(canId);

        angleBuffer = new double[3];
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void calibrate() {
        // TODO Auto-generated method stub
    }

    @Override
    public void reset() {
        System.out.println("Resetting PigeonIMU");
        imu.setFusedHeading(0.0);
    }

    @Override
    public double getHeading() {
        return imu.getFusedHeading();
    }

    @Override
    public double getXAngle() {
        return angleBuffer[X_ANGLE];
    }

    @Override
    public double getYAngle() {
        return angleBuffer[Y_ANGLE];
    }

    @Override
    public double getZAngle() {
        return angleBuffer[Z_ANGLE];
    }

    @Override
    public void update() {
        ErrorCode err =  imu.getAccelerometerAngles(angleBuffer);

        if ( err != ErrorCode.OK)
        {
            String msg = MessageFormat.format("Error reading Pigeon tilt angles: {0}", err.value);
            System.out.println(msg);
        }
    }
}