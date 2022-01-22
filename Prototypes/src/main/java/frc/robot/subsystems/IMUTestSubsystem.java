// Subsystem to display output from an IMU object

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.frcteam3663.sensors.IMU;

public class IMUTestSubsystem extends SubsystemBase {

  private IMU imu;
  
  private NetworkTableEntry headingEntry;
  private NetworkTableEntry xAngleEntry;
  private NetworkTableEntry yAngleEntry;
  private NetworkTableEntry zAngleEntry;

  /** Creates a new ExampleSubsystem. */
  public IMUTestSubsystem(IMU imu) {

    this.imu = imu;

    ShuffleboardTab imuTab = Shuffleboard.getTab("IMU");
    
    headingEntry = imuTab.add("Heading", 0.0)
        .withPosition(0, 0)
        .withSize(1, 1)
        .getEntry();

    xAngleEntry = imuTab.add("X Angle", 0.0)
        .withPosition(1, 0)
        .withSize(1, 1)
        .getEntry();

    yAngleEntry = imuTab.add("Y Angle", 0.0)
        .withPosition(2, 0)
        .withSize(1, 1)
        .getEntry();

    zAngleEntry = imuTab.add("Z Angle", 0.0)
        .withPosition(3, 0)
        .withSize(1, 1)
        .getEntry();
  }

  @Override
  public void periodic() {

    imu.update();

    xAngleEntry.setDouble(imu.getXAngle());
    yAngleEntry.setDouble(imu.getYAngle());
    zAngleEntry.setDouble(imu.getZAngle());
    headingEntry.setDouble(imu.getHeading());
  }
}