package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import frc.robot.commands.BlinkyCommand;
import frc.robot.commands.OneMotorCommand;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.OneMotorSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  // Misc
  private final XboxController controller = new XboxController(DRIVE_CONTROLLER_ID);

  // Subsystems
  private final LEDSubsystem ledSubsystem = new LEDSubsystem();
  private OneMotorSubsystem m_subsystem; 

  // Commands
  OneMotorCommand moveMotor;

  // Autonomous command creation
  private final HashMap<String, Supplier<Command>> commandCreators = new HashMap<String, Supplier<Command>>();
  private SendableChooser<Supplier<Command>> chooser = new SendableChooser<Supplier<Command>>();


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems(); // Create our subsystems.
    createCommands();
    configureButtonBindings();
    setupCommandChooser();  
    
  }

  private void createSubsystems() {
    m_subsystem = new OneMotorSubsystem(MOTOR_CAN_ID);
  }

  private void createCommands() {
    moveMotor = new OneMotorCommand(m_subsystem, () -> controller.getLeftX());

    // Register a creator for our autonomous commands
    registerAutoCommand("Do Nothing", this::createNullCommand);
    registerAutoCommand("blink 0", this::createBlink0);
    registerAutoCommand("blink 1", this::createBlink1);
    registerAutoCommand("blink 2", this::createBlink2);
  }

  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kA.value)
        // .whenPressed(new InstantCommand(() -> ledSubsystem.set(0, !ledSubsystem.get(0))));
        // .whenPressed(new InstantCommand(() -> m_subsystem.setPower(0.03)));
        // .whenPressed(new InstantCommand(() -> m_subsystem.powerDown()));
        .whenPressed(new InstantCommand(() -> m_subsystem.speedDown()));
    new JoystickButton(controller, Button.kB.value)
        // .whenPressed(new InstantCommand(() -> ledSubsystem.set(1, !ledSubsystem.get(1))));
        // .whenPressed(new InstantCommand(() -> m_subsystem.powerUp()));
        .whenPressed(new InstantCommand(() -> m_subsystem.speedUp()));
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
    Supplier<Command> creator = chooser.getSelected();
    return creator.get();
  }

  private void registerAutoCommand(String name, Supplier<Command> creator) {
    commandCreators.put(name, creator);
  }

  /**
   * Setup our autonomous command chooser in the Shuffleboard
   */
  private void setupCommandChooser() {
    List<String> keys = new ArrayList<String>(commandCreators.keySet());
    keys.sort((a,b) -> a.compareTo(b));
    
    for (String key : keys) {
        chooser.addOption(key, commandCreators.get(key));
    }

    Shuffleboard.getTab("Main")
        .add("Auto Command", chooser)
        .withPosition(0, 1)
        .withSize(2, 1)
        .withWidget(BuiltInWidgets.kComboBoxChooser);
  }

  //---------------------------------------------------------------------------
  // Autonomous command creators
  //---------------------------------------------------------------------------

  private Command createNullCommand() {
    return null;
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