// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivers.Limelight;
import frc.robot.subsystems.SS_TankDrive;

public class C_AutoAlign extends CommandBase {
  private SS_TankDrive driveBase;
  private Limelight vision;
  /** Creates a new C_AutoAlign. */
  public C_AutoAlign(SS_TankDrive driveBase) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveBase = driveBase;
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    if(!vision.getValidTarget()){
      driveBase.setPower("left", 0.2);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //while not horozontal turn right if xoffset is < 27 && > 0 else turn left
    //while not vertical back up if yoffset is < 20.5 && > 0 else drive foreward
    if(vision.getValidTarget()){
      if(vision.getXOffset() < 27 && vision.getXOffset() > 2){
        driveBase.setPower("left", -0.2);
      }else if(vision.getYOffset() > -27 && vision.getYOffset() < -2){
        driveBase.setPower("right", -0.2);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
