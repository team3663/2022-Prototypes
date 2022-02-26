// Dummy subsystem class

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  private DigitalInput input;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    input = new DigitalInput(3);
    System.out.println("ExampleSubsystem started");
  }
  
  public boolean switchPressed() {
    return input.get();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  // Dummy method
  public void doSomething() {
    System.out.print("doSomething() ");
    System.out.println("switch pressed: " + switchPressed());
  }
}