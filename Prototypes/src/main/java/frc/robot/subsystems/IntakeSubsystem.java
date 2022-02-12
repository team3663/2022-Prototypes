package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax motor;
  private DoubleSolenoid solenoid;

  private double power = 0.4;
  private boolean isOut;

  /** Creates a new instance of the Shooter subsystem. */
  public IntakeSubsystem(int motorCanId, int inSolenoidCanId, int outSolenoidCanId) {

    motor = new CANSparkMax(motorCanId, MotorType.kBrushless);

    solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, inSolenoidCanId, outSolenoidCanId);

  }

  @Override
  public void periodic() { }

  public void start() {
    solenoid.set(Value.kForward);
    if(isOut == true){
      motor.set(power);
    } 
  }

  public void stop() {
    pneumaticsIn();
    motor.set(0.0);
  }

  public void pneumaticsOut(){
    solenoid.set(Value.kForward);
    isOut = true;
  }

  public void pneumaticsIn(){
    solenoid.set(Value.kReverse);
    isOut = false;
  }

  public void takeBallIn(){
    motor.set(power);
  }

  public void pushBallOut(){
    motor.set(-power);
  }
}
