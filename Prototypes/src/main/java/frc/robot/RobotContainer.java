package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;
import frc.robot.subsystems.FeederSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final XboxController controller;

  // Subsystems
  private final FeederSubsystem feeder;
  
  // Commands


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    controller = new XboxController(DRIVE_CONTROLLER_ID);

    feeder = new FeederSubsystem(FEEDER_MOTOR_1_CAN_ID, FEEDER_MOTOR_2_CAN_ID);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kA.value).whenPressed(new InstantCommand(() -> feeder.moveBallToPrechamberFromIntake(), feeder));
    new JoystickButton(controller, Button.kB.value).whenPressed(new InstantCommand(() -> feeder.moveBallToChamber(), feeder));
    new JoystickButton(controller, Button.kX.value).whenPressed(new InstantCommand(() -> feeder.moveBallToPrechamberFromChamber(), feeder));
    new JoystickButton(controller, Button.kY.value).whenPressed(new InstantCommand(() -> feeder.moveBallToIntake(), feeder));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}