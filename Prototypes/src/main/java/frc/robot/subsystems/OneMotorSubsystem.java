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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OneMotorSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor;
  private RelativeEncoder m_motorEncoder;
  private double speedIncrement = 0.1;
  private double targetSpeed;

  // Hood PID coefficients
  private static final double kHoodP = 0.1;
  private static final double kHoodI = 1e-4;
  private static final double kHoodD = 1;
  private static final double kHoodIz = 0;
  private static final double kHoodFF = 0;
  private static final double kHoodMaxOutput = 1;
  private static final double kHoodMinOutput = -1;

  private SparkMaxPIDController motorPidController;

  public OneMotorSubsystem(int MotorCANID) {
    m_motor = new CANSparkMax(MotorCANID, MotorType.kBrushless);
    m_motor.setIdleMode(IdleMode.kBrake);
    m_motorEncoder = m_motor.getEncoder();

    motorPidController = m_motor.getPIDController();
    motorPidController.setP(kHoodP);
    motorPidController.setI(kHoodI);
    motorPidController.setD(kHoodD);
    motorPidController.setIZone(kHoodIz);
    motorPidController.setFF(kHoodFF);
    motorPidController.setOutputRange(kHoodMinOutput, kHoodMaxOutput);
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
    // m_motor.set(m_speed);
    // This method will be called once per scheduler run
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
}
