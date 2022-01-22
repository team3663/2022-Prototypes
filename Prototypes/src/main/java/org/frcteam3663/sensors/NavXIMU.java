package org.frcteam3663.sensors;

import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

public class NavXIMU implements IMU
{
    private final AHRS navx = new AHRS(SPI.Port.kMXP, (byte) 200);

    public NavXIMU()
    {
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
        navx.reset();
    }

    @Override
    public double getHeading() {
        return navx.getFusedHeading();
    }

    @Override
    public double getXAngle() {
        return navx.getPitch();
    }

    @Override
    public double getYAngle() {
        return navx.getRoll();
    }

    @Override
    public double getZAngle() {
        return navx.getYaw();
    }

    @Override
    public void update() {
    }
}