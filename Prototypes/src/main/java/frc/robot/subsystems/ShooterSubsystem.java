package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax shooterMotor1;
  private CANSparkMax shooterMotor2;
  private CANSparkMax hoodMotor;
  private MotorControllerGroup shooterMotorGroup ;
  private RelativeEncoder hoodEncoder;
  private boolean running = false;
  private double power = 0.0;

  //Network tables for Shuffleboard telemetry
  private NetworkTableEntry runningEntry;
  private NetworkTableEntry powerEntry;
  private NetworkTableEntry rpmEntry;

  static final double powerIncrement = 0.05; 

  //THESE NUMBERS ARE ALL COMPLETELY OFF
  private final int[][] KNOWN_POINTS = new int[][] { 
    { 3490, 64, 5 }, 
    { 3400, 47, 10 }, 
    { 3730, 35, 15 },
    { 3900, 30, 18 }, 
    { 4090, 27, 20 }, 
    { 4100, 23, 23 },
    { 4390, 21, 25 } 
  };

  private final int RPM_COLUMN = 0; // column index for RPM values
  private final int ANGLE_COLUMN = 1; // column index for angle values
  private final int DISTANCE_COLUMN = 2; // column index for distance values

  /** Creates a new instance of the Shooter subsystem. */
  public ShooterSubsystem(int motor1CANId, int motor2CANId) {

    shooterMotor1 = new CANSparkMax(motor1CANId, MotorType.kBrushless);
    shooterMotor2 = new CANSparkMax(motor2CANId, MotorType.kBrushless);
    motorGroup = new MotorControllerGroup(motor1, motor2);
    encoder1 = motor1.getEncoder();

    // The motors in the shooter run in opposition to each other by default
    // invert one of them to fix this and initialize power to zero.
    motor1.setInverted(true);
    motorGroup.set(power);
  }

  @Override
  public void periodic() {

    // Update network tables for Shuffleboard
    runningEntry.setBoolean(running);
    powerEntry.setDouble(power);
    rpmEntry.setDouble(encoder1.getVelocity());
  }

  public void start() {
    running = true;
    motorGroup.set(power);
  }

  public void stop() {
    running = false;
    motorGroup.set(0.0);
  }

  public void increasePower() {
      power += powerIncrement;
      if (power > 1.0) {
        power = 1.0;
      }

      if ( running ) {
        motorGroup.set(power);
      }
  }

  public void decreasePower() {
    power -= powerIncrement;
    if (power < 0.0) {
      power = 0.0;
    }

    if (running) {
      motorGroup.set(power);
    }
  }
}
