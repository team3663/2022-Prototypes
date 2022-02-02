package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import frc.robot.commands.C_AutoAlign;
import frc.robot.commands.C_Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SS_TankDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //
  private SS_TankDrive driveBase = new SS_TankDrive();
  private final XboxController controller = new XboxController(DRIVE_CONTROLLER_ID);

  // Subsystems
  private final ExampleSubsystem mySubsystem = new ExampleSubsystem();

  // Commands
  private final ExampleCommand autoCmd = new ExampleCommand(mySubsystem);
  private final C_AutoAlign align = new C_AutoAlign(driveBase);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    Command cmd = new C_Drive(driveBase, 
                          () -> controller.getRawAxis(Constants.L_Y_AXIS), 
                          () -> controller.getRawAxis(Constants.R_X_AXIS));
    driveBase.setDefaultCommand(cmd);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //new edu.wpi.first.wpilibj2.command.button.Button().whenPressed(align);

    // Bind an InstantCommand that calls the doSomething method of our subsystem when button A is pressed.
    //new JoystickButton(controller, Button.kA.value).whenPressed(new InstantCommand(() -> mySubsystem.doSomething(), mySubsystem));

    new JoystickButton(controller, Button.kA.value)
            .whileHeld(new C_AutoAlign(driveBase), false);
          // .whenHeld(new InstantCommand(() -> new C_AutoAlign(driveBase)), true);
    // .whenPressed(new InstantCommand(() -> new C_AutoAlign(driveBase)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCmd;
  }
}