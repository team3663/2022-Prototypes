// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
  private CANSparkMax preChamberMotor;
  private CANSparkMax chamberMotor;
  private boolean ballInChamber = false;
  private boolean ballInPreChamber = false;
  private double power = 0.4;

   static final double powerIncrement = 0.05; 

  /** Creates a new instance of the Shooter subsystem. */
  public FeederSubsystem(int motor1CANId, int motor2CANId) {
    preChamberMotor = new CANSparkMax(motor1CANId, MotorType.kBrushless);
    chamberMotor = new CANSparkMax(motor2CANId, MotorType.kBrushless);
  }

  @Override
  public void periodic() { }

  public void moveBallToPrechamberFromIntake() {
    if(getBallInPreChamber(true)){
      preChamberMotor.set(0);
    }else{
      preChamberMotor.set(power);
      setBallInPreChamber(true);
    }
  }

  public void moveBallToIntake() {
    if(getBallInPreChamber(true)){
      preChamberMotor.set(0);
    }else{
      preChamberMotor.set(-power);
      setBallInPreChamber(false);
    }
  }

  public void moveBallToChamber() {
    if(getBallInChamber(true)){
      chamberMotor.set(0);
    }else{
      chamberMotor.set(power);
      setBallInPreChamber(false);
      setBallInChamber(true);
    }
  }

  public void moveBallToPrechamberFromChamber() {
    if(getBallInPreChamber(true)){
      chamberMotor.set(0);
    }else{
      chamberMotor.set(-power);
      setBallInPreChamber(true);
      setBallInChamber(false);
    }
  }

  public Boolean getBallInPreChamber(boolean state){
    if(ballInPreChamber) return true;
    return false;
  }

  public Boolean getBallInChamber(boolean state){
    if(ballInChamber) return true;
    return false;
  }

  public void setBallInPreChamber(boolean state){
    ballInPreChamber = state;
  }

  public void setBallInChamber(boolean state){
    ballInChamber = state;
  }
}

