// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SS_Shooter extends SubsystemBase {

  private CANSparkMax shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_CAN_LEFT, MotorType.kBrushless);
  private CANSparkMax shooterMotorRight = new CANSparkMax(Constants.SHOOTER_CAN_RIGHT, MotorType.kBrushless);
  
  public SS_Shooter() {}

  public void setSpeed(double speed){
    shooterMotorLeft.set(speed);
    shooterMotorRight.set(speed);
  }

  @Override
  public void periodic() {
    
  }
}
