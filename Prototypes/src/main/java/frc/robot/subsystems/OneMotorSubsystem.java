// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OneMotorSubsystem extends SubsystemBase {
  private enum MotorState {
    STOPPED,
    RUNNING,
    STOPPING
  }
  private final CANSparkMax m_motor;
  private RelativeEncoder motorEncoder;
  private double speedIncrement = 50;
  private double targetSpeed = 0;
  private double powerIncrement = 0.05;
  private double targetPower = 0;

  private MotorState motorState = MotorState.STOPPED;
  private double encoderPosition = 0;   // 'position' means 'rotation'
  private double currentSpeed = 0;
  

  // Hood PID coefficients
  private static final double kMotorP = 1e-3; // 0.1
  private static final double kMotorI = 0;  // 1e-8
  private static final double kMotorD = 0;  // 1e-4
  private static final double kMotorIz = 1e-10;
  private static final double kMotorFF = 0;
  private static final double kMotorMaxOutput = 1;
  private static final double kMotorMinOutput = 0;

  private SparkMaxPIDController motorPidController;

  // Telemetry
  private NetworkTableEntry countsPerRevolutionEntry;
  private NetworkTableEntry invertedEntry;
  private NetworkTableEntry encoderEntry;
  private NetworkTableEntry velocityEntry;
  private NetworkTableEntry outputCurrentEntry;
  private NetworkTableEntry targetSpeedEntry;
  private NetworkTableEntry targetPowerEntry;
  
  private NetworkTableEntry shooterEncoderEntry;
  private NetworkTableEntry speedErrorEntry;
  private NetworkTableEntry speedErrorPercentEntry;
  private NetworkTableEntry currentAngleEntry;
  private NetworkTableEntry targetAngleEntry;
  private NetworkTableEntry hoodEncoderEntry;
  private NetworkTableEntry hoodLimitSwitchEntry;
  private NetworkTableEntry readyToShootEntry;
  private NetworkTableEntry currentRangeEntry;

  public OneMotorSubsystem(int MotorCANID) {
    m_motor = new CANSparkMax(MotorCANID, MotorType.kBrushless);
    m_motor.setIdleMode(IdleMode.kBrake);
    motorEncoder = m_motor.getEncoder();

    motorPidController = m_motor.getPIDController();
    motorPidController.setP(kMotorP);
    motorPidController.setI(kMotorI);
    motorPidController.setD(kMotorD);
    motorPidController.setIZone(kMotorIz);
    motorPidController.setFF(kMotorFF);
    motorPidController.setOutputRange(kMotorMinOutput, kMotorMaxOutput);

    m_motor.set(0);
    initTelemetry();
  }

  public void setSpeed(double targetSpeed) {
    this.targetSpeed = targetSpeed;
    motorPidController.setReference(targetSpeed, ControlType.kVelocity);
  }

  public void setPower(double power) {
    m_motor.set(power);
  }


  @Override
  public void periodic() {
    encoderPosition = motorEncoder.getPosition();
    currentSpeed = motorEncoder.getVelocity();
    // If we are in the process of stopping the motos then slowly ramp the target speed down 
    if (motorState == MotorState.STOPPING) {
      setSpeed(targetSpeed - 20);
    }
    motorPidController.setReference(targetSpeed, ControlType.kVelocity);
    updateTelemetry();
  }

  public void move(double speed) {
    m_motor.set(speed);
  }

  public void stop() {
    m_motor.set(0);
  }

  // public void forward() {
  //   m_speed = Math.abs(m_speed);
  // }

  // public void backward() {
  //   m_speed = -Math.abs(m_speed);
  // }

  public void speedUp() {
    targetSpeed += speedIncrement;
  }

  public void speedDown() {
    targetSpeed -= speedIncrement;
  }

  public void powerUp() {
    targetPower += powerIncrement;
    m_motor.set(targetPower);
  }

  public void powerDown() {
    targetPower -= powerIncrement;
    m_motor.set(targetPower);
  }

  public void initTelemetry() {
    ShuffleboardTab tab = Shuffleboard.getTab("Motor"); // Data is grouped with shooter and intake.

      // Motor Data
      countsPerRevolutionEntry = tab.add("Count/Revolution", 0)
              .withPosition(0, 0)
              .withSize(3, 3)
              .getEntry();
      invertedEntry = tab.add("Inverted", false)
              .withPosition(2, 0)
              .withSize(1, 1)
              .getEntry();
      encoderEntry = tab.add("Encoder Position", 0)
              .withPosition(0, 2)
              .withSize(2, 1)
              .getEntry();
      velocityEntry = tab.add("Velocity", 0)
              .withPosition(0, 3)
              .withSize(2, 1)
              .getEntry();
      targetSpeedEntry = tab.add("Target Speed", 0)
              .withPosition(0, 4)
              .withSize(2, 1)
              .getEntry();        
      outputCurrentEntry = tab.add("Output Current Output", 0)
              .withPosition(0, 5)
              .withSize(2, 1)
              .getEntry();
      targetPowerEntry  = tab.add("Target Power", 0)
              .withPosition(0, 6)
              .withSize(2, 1)
              .getEntry();
  }

  public void updateTelemetry() {
    countsPerRevolutionEntry.setNumber(motorEncoder.getCountsPerRevolution());
    invertedEntry.forceSetBoolean(m_motor.getInverted());
    encoderEntry.setNumber(motorEncoder.getPosition());
    velocityEntry.setNumber(motorEncoder.getVelocity());
    targetSpeedEntry.setNumber(targetSpeed);
    outputCurrentEntry.setNumber(m_motor.getOutputCurrent());
    targetPowerEntry.setNumber(m_motor.get());
    // speedErrorEntry.setNumber(speedError);
    // speedErrorPercentEntry.setNumber(speedErrorPercent);
    // currentRangeEntry.setNumber(currentRange);
    // readyToShootEntry.forceSetBoolean(ready());

    // currentAngleEntry.setNumber(currentAngle);
    // targetAngleEntry.setNumber(targetAngle);
    // hoodEncoderEntry.setNumber(hoodEncoder.getPosition());
    // hoodLimitSwitchEntry.forceSetBoolean(hoodLimit.get());
  }
}
