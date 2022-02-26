// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSubsystem;

public class C_DoSomethingUntilSwitchPressed extends CommandBase {
  private final ExampleSubsystem m_subsystem;
  private long startTime;

  /** Creates a new C_DoSomethingUntilSwitchPressed. */
  public C_DoSomethingUntilSwitchPressed(ExampleSubsystem subsystem) {
    m_subsystem = subsystem;
    startTime = System.currentTimeMillis() / 1000;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("C_DoSomethingUntilSwitchPressed initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    long currentTime = System.currentTimeMillis() / 1000;
    if(currentTime - startTime > 0) {
      startTime = currentTime;
      System.out.println("doing something . . . . . ");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("C_DoSomethingUntilSwitchPressed ended.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.switchPressed();
  }
}
