// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SS_Shooter extends SubsystemBase {
  private static SS_Shooter instance;

  private boolean isAutoSpinning = true;
  private double correctionMultiplier = 1;

  private CANSparkMax leftMotor = new CANSparkMax(Constants.SHOOTER_CAN_LEFT, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(Constants.SHOOTER_CAN_RIGHT, MotorType.kBrushless);
  private CANPIDController flywheelPid;
  private CANEncoder flywheelEncoder;

  //private int targetRPM = 0;
  /** Creates a new SS_Shoot. */
  public SS_Shooter() {}

  public static SS_Shooter getInstance() {
    if (instance == null) {
      instance = new SS_Shooter();
    }
    return instance;
  }

  public void setSpeed(double speed){
    leftMotor.set(speed);
    //rightMotor.set(-speed);
    //flywheelPid.setReference(RPM * correctionMultiplier, ControlType.kVelocity);
  }


  // public boolean atCorrectRPM(int rpm){
  //   if(targetRPM != rpm){
  //     return false;
  //   }
  //   return true;
  // }

  @Override
  public void periodic() {
    
  }
}
