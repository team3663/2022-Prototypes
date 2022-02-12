package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimberSubsytem extends SubsystemBase {

  private CANSparkMax motor1;
  private CANSparkMax motor2;
  private CANSparkMax hookMotor1;
  private CANSparkMax hookMotor2;
  private MotorControllerGroup motorGroup;

  /** Creates a new instance of the Shooter subsystem. */
  public ClimberSubsytem(int motor1CANId, int motor2CANId, int hookMotor1CANId, int hookMotor2CANId) {

    motor1 = new CANSparkMax(motor1CANId, MotorType.kBrushless);
    motor2 = new CANSparkMax(motor2CANId, MotorType.kBrushless);
    hookMotor1 = new CANSparkMax(hookMotor1CANId, MotorType.kBrushless);
    hookMotor2 = new CANSparkMax(hookMotor2CANId, MotorType.kBrushless);

    motorGroup = new MotorControllerGroup(motor1, motor2);

    motor1.setInverted(true);
    motorGroup.set(0.0);
    hookMotor1.set(0.0);
    hookMotor2.set(0.0);
  }

  @Override
  public void periodic() {}

  public void spinWindmill() {
    motorGroup.set(0.4);
  }

  public void spinWindmillInverse() {
    motorGroup.set(-0.4);
  }

  public void spinHook1Out(){
    hookMotor1.set(0.3);
  }

  public void spinHook1In(){
    hookMotor1.set(-0.3);
  }

  public void spinHook2Out(){
    hookMotor2.set(0.3);
  }

  public void spinHook2In(){
    hookMotor2.set(0.3);
  }
}