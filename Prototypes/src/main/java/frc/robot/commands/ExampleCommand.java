// Example command that runs forever (doing nothing) unless interrupted.

package frc.robot.commands;


import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSubsystem;

public class ExampleCommand extends CommandBase {
  // @SuppressWarnings("unused")
  private final ExampleSubsystem m_subsystem;
  @SuppressWarnings("unused")
  private final XboxController m_controller;  
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(ExampleSubsystem subsystem, XboxController controller) {
    m_subsystem = subsystem;
    this.m_controller = controller;
  
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ExampleCommand initialized.");
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Examplecommand ended.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}