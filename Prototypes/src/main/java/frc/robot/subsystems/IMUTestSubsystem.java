// Subsystem to display output from a Gyros object

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IMUTestSubsystem extends SubsystemBase {

  Gyro gyro;
  
  private NetworkTableEntry headingEntry;

  /** Creates a new ExampleSubsystem. */
  public IMUTestSubsystem(Gyro gyro) {

    this.gyro = gyro;

    ShuffleboardTab imuTab = Shuffleboard.getTab("IMU");
    
    headingEntry = imuTab.add("Heading", 0.0)
            .withPosition(0, 0)
            .withSize(1, 1)
            .getEntry();
  }

  @Override
  public void periodic() {
    double heading = gyro.getAngle();
    headingEntry.setDouble(heading);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  // Dummy method
  public void doSomething() {}
}