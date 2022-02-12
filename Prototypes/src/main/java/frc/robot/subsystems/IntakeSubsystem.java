package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;
  private double power = 0.4;
  final DoubleSolenoid intakeSolenoid;
  private boolean isOut;

  /** Creates a new instance of the Shooter subsystem. */
  public IntakeSubsystem(int motor1CANId) {

    intakeMotor = new CANSparkMax(motor1CANId, MotorType.kBrushless);

    intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.SOLONOID_INWARD_CAN_ID, Constants.SOLONOID_OUTWARD_CAN_ID);

    //intakeMotor.setInverted(true); // ------------------- might need this find out later
  }

  @Override
  public void periodic() { }

  public void start() {
    intakeSolenoid.set(Value.kForward);
    if(isOut == true){
      intakeMotor.set(power);
    } 
  }

  public void stop() {
    pneumaticsIn();
    intakeMotor.set(0.0);
  }

  public void pneumaticsOut(){
    intakeSolenoid.set(Value.kForward);
    isOut = true;
  }

  public void pneumaticsIn(){
    intakeSolenoid.set(Value.kReverse);
    isOut = false;
  }

  public void takeBallIn(){
    intakeMotor.set(power);
  }

  public void pushBallOut(){
    intakeMotor.set(-power);
  }
}
