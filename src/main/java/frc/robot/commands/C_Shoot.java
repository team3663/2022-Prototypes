// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SS_Shooter;


public class C_Shoot extends CommandBase {
  /** Creates a new C_Shoot. */
  private static final XboxController driveController = new XboxController(Constants.DRIVE_CONTROLLER_ID);

  private SS_Shooter shooter;

  public double speed = 0;

  public C_Shoot(SS_Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(driveController.getLeftTriggerAxis() > 0.8){
      shooter.setSpeed(speed);
    } 
    if(driveController.getLeftBumperPressed()){
      speed -= 0.1;
    }
    if(driveController.getRightBumperPressed()){
      speed += 0.1;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
