package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import com.ctre.phoenix.led.ColorFlowAnimation.Direction;

import frc.robot.imput.DPadButton;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

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
  private final IntakeSubsystem intake = new IntakeSubsystem(INTAKE_CAN_ID);
  private final ShooterSubsystem shooter = new ShooterSubsystem(SHOOTER_1_CAN_ID, SHOOTER_2_CAN_ID);
  private final FeederSubsystem feeder = new FeederSubsystem(FEEDER_1_CAN_ID, FEEDER_2_CAN_ID);
  
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
    new JoystickButton(controller, Button.kRightStick.value).whenPressed(new InstantCommand(() -> intake.start(), intake));
    new JoystickButton(controller, Button.kLeftStick.value).whenPressed(new InstantCommand(() -> intake.stop(), intake));
    new JoystickButton(controller, Button.kY.value).whenPressed(new InstantCommand(() -> intake.takeBallIn(), intake));
    new JoystickButton(controller, Button.kX.value).whenPressed(new InstantCommand(() -> intake.pushBallOut(), intake));

    new JoystickButton(controller, Button.kA.value).whenPressed(new InstantCommand(() -> shooter.start(), shooter));
    new JoystickButton(controller, Button.kX.value).whenPressed(new InstantCommand(() -> shooter.stop(), shooter));
    new JoystickButton(controller, Button.kLeftBumper.value).whenPressed(new InstantCommand(() -> shooter.decreasePower(), shooter));
    new JoystickButton(controller, Button.kRightBumper.value).whenPressed(new InstantCommand(() -> shooter.increasePower(), shooter));

    new DPadButton(controller, DPadButton.Direction.UP).whenPressed(new InstantCommand(() -> feeder.moveBallToPrechamberFromIntake(), feeder));
    new DPadButton(controller, DPadButton.Direction.RIGHT).whenPressed(new InstantCommand(() -> feeder.moveBallToChamber(), feeder));
    new DPadButton(controller, DPadButton.Direction.DOWN).whenPressed(new InstantCommand(() -> feeder.moveBallToPrechamberFromChamber(), feeder));
    new DPadButton(controller, DPadButton.Direction.LEFT).whenPressed(new InstantCommand(() -> feeder.moveBallToIntake(), feeder));
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