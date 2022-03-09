// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RumbleCommand extends CommandBase {
  private final XboxController m_controller;
  private Timer timer;
  private double seconds;
  private double rumbleIntensity;
  
  public RumbleCommand(XboxController controller, double seconds, double intensity) {
    this.m_controller = controller;
    this.seconds = seconds;
    this.rumbleIntensity = intensity;
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public RumbleCommand(XboxController controller) {
    // default values for seconds and intensity
    this(controller, 0.3, 1.0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("RumbleCommand initialize");
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_controller.setRumble(RumbleType.kLeftRumble, rumbleIntensity);
    m_controller.setRumble(RumbleType.kRightRumble, rumbleIntensity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("RumbleCommand end");
    m_controller.setRumble(RumbleType.kLeftRumble, 0);
    m_controller.setRumble(RumbleType.kRightRumble, 0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(seconds);
  }
}
