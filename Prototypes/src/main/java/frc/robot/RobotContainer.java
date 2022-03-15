package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ClimberSubsytem;

import static frc.robot.Constants.*;


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
  private final ClimberSubsytem climber = new ClimberSubsytem(CLIMBER_1_CAN_ID, CLIMBER_2_CAN_ID, CLIMBER_3_CAN_ID, CLIMBER_4_CAN_ID, CLIMBER_5_CAN_ID, ELEVATOR_LIMITSWITCH_DIO);

  // private final Climber climbersub = new Climber(ELEVATOR_MOTOR_CAN_ID, WINDMILL_MOTOR_1_CAN_ID, WINDMILL_MOTOR_2_CAN_ID, HOOK_MOTOR_1_CAN_ID, HOOK_MOTOR_2_CAN_ID,
  //               ELEVATOR_LIMITSWITCH_DIO, HOOK_ROTATION_LIMITSWITCH_1_DIO, HOOK_ROTATION_LIMITSWITCH_2_DIO, HOOK_PRESSED_LIMITSWITCH_1_DIO, 
  //               HOOK_PRESSED_LIMITSWITCH_2_DIO, HOOK_PRESSED_LIMITSWITCH_3_DIO, HOOK_PRESSED_LIMITSWITCH_4_DIO);
  // Commands


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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
    // new JoystickButton(controller, Button.kLeftBumper.value).whenPressed(new InstantCommand(() -> climber.spinWindmill(), climber));
    // new JoystickButton(controller, Button.kRightBumper.value).whenPressed(new InstantCommand(() -> climber.spinWindmillInverse(), climber));
    new JoystickButton(controller, Button.kBack.value).whenPressed(new InstantCommand(() -> climber.elevatorUp(), climber));
    new JoystickButton(controller, Button.kX.value).whenHeld(new InstantCommand(() -> climber.elevatorDown(), climber));

    new JoystickButton(controller, Button.kStart.value).whenPressed(new InstantCommand(() -> climber.stop(), climber));
    // // new JoystickButton(controller, Button.kBack.value).whenPressed(new InstantCommand(() -> climber.stopHook(), climber));

    // new JoystickButton(controller, Button.kA.value).whenPressed(new InstantCommand(() -> climber.spinHook1In(), climber));
    // new JoystickButton(controller, Button.kB.value).whenPressed(new InstantCommand(() -> climber.spinHook1Out(), climber));
    // new JoystickButton(controller, Button.kY.value).whenPressed(new InstantCommand(() -> climber.spinHook2In(), climber));
    // new JoystickButton(controller, Button.kX.value).whenPressed(new InstantCommand(() -> climber.spinHook2Out(), climber));

            // FOR TESTING
          //   new JoystickButton(controller, Button.kB.value).whenPressed(
          //     new InstantCommand(() -> climbersub.elevatorUp()));
  
          // new JoystickButton(controller, Button.kA.value).whenPressed(
          //     new InstantCommand(() -> climbersub.elevatorHome()));
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