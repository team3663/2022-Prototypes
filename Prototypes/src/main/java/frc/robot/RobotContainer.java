package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import frc.robot.commands.BlinkyCommand;
import frc.robot.subsystems.LEDSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Misc
  private final XboxController controller = new XboxController(DRIVE_CONTROLLER_ID);
  private SendableChooser<Integer> chooser = new SendableChooser<Integer>();

  // Subsystems
  private final LEDSubsystem ledSubsystem = new LEDSubsystem();

  // Commands

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    chooser.setDefaultOption("Blink 0", 0);
    chooser.addOption("Blink 1", 1);
    chooser.addOption("Blink 2", 2);

    Shuffleboard.getTab("Main")
        .add("Auto Scenario", chooser)
        .withPosition(0, 1)
        .withSize(2, 1)
        .withWidget(BuiltInWidgets.kComboBoxChooser);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(controller, Button.kA.value)
        .whenPressed(new InstantCommand(() -> ledSubsystem.set(0, !ledSubsystem.get(0))));
    new JoystickButton(controller, Button.kB.value)
        .whenPressed(new InstantCommand(() -> ledSubsystem.set(1, !ledSubsystem.get(1))));
    new JoystickButton(controller, Button.kX.value)
        .whenPressed(new InstantCommand(() -> ledSubsystem.set(2, !ledSubsystem.get(2))));
    new JoystickButton(controller, Button.kY.value)
        .whenPressed(new InstantCommand(() -> ledSubsystem.set(3, !ledSubsystem.get(3))));

    new JoystickButton(controller, Button.kLeftBumper.value).whenHeld(new BlinkyCommand(ledSubsystem, 3));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    int scenario = chooser.getSelected();
    Command cmd = null;

    switch (scenario) {
      case 0:
        cmd = createBlink0();
        break;
      case 1:
        cmd = createBlink1();
        break;
      case 2:
        cmd = createBlink2();
        break;
    }

    return cmd;
  }

  private Command createBlink0() {
    return new BlinkyCommand(ledSubsystem, 0);
  }

  private Command createBlink1() {
    return new BlinkyCommand(ledSubsystem, 1);
  }

  private Command createBlink2() {
    return new BlinkyCommand(ledSubsystem, 2);
  }
}