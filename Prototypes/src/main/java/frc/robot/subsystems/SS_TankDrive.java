// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.TalonSRXController;

public class SS_TankDrive extends SubsystemBase {

  private final TalonSRXController leftMotor = new TalonSRXController(Constants.LEFT_DRIVE);
  private final TalonSRXController rightMotor = new TalonSRXController(Constants.RIGHT_DRIVE);

  private final DifferentialDrive m_diffDrive = new DifferentialDrive(leftMotor, rightMotor);

  /** Creates a new SS_FTC. */
   /** Creates a new SS_FTC. */

   public SS_TankDrive() {
    leftMotor.setInverted(false);
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
    m_diffDrive.arcadeDrive(xAxisSpeed, -zAxisRotate);
  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(String motor, double power){
    if(motor == "left"){
      leftMotor.set(ControlMode.PercentOutput, power);
      rightMotor.set(ControlMode.PercentOutput, -power);
    } else if(motor == "right"){
      rightMotor.set(ControlMode.PercentOutput, power);
      leftMotor.set(ControlMode.PercentOutput, -power);
    }
  }

  public void setPower(double leftPower, double rightPower) {
    leftMotor.set(ControlMode.PercentOutput, leftPower);
    rightMotor.set(ControlMode.PercentOutput, rightPower);
  }
}
