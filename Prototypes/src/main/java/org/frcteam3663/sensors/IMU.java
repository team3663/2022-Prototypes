package org.frcteam3663.sensors;
import org.frcteam3663.Updatable;

public interface IMU extends Updatable {
 
    public void close();

    public void calibrate();

    public void reset();

    public double getHeading();

    public double getXAngle();

    public double getYAngle();

    public double getZAngle();
}