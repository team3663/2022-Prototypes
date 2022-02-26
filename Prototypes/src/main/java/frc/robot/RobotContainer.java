package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import frc.robot.commands.C_DoSomethingUntilSwitchPressed;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //
  private final XboxController controller = new XboxController(DRIVE_CONTROLLER_ID);

  // Subsystems
  private final ExampleSubsystem m_subsystem;

  // Commands
  private final ExampleCommand m_exampleCommand;
  private final C_DoSomethingUntilSwitchPressed m_doSomethingCommand;
  // private DigitalInput input = new DigitalInput(3);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_subsystem = new ExampleSubsystem();
    m_exampleCommand = new ExampleCommand(m_subsystem);
    m_doSomethingCommand = new C_DoSomethingUntilSwitchPressed(m_subsystem);

    m_subsystem.setDefaultCommand(m_exampleCommand);
    
    // Configure the button bindings
    configureButtonBindings();
  }

  public void createCommands() {

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Bind an InstantCommand that calls the doSomething method of our subsystem when button A is pressed.
    new JoystickButton(controller, Button.kA.value).whenPressed(new InstantCommand(() -> m_subsystem.doSomething(), m_subsystem));
    new JoystickButton(controller, Button.kB.value).whenPressed(m_doSomethingCommand);
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_exampleCommand;
  }
}