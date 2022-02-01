// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.swervedrivespecialties.swervelib.DriveController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
// import frc.robot.commands.C_Drive;
// import frc.robot.commands.C_Feeder;
import frc.robot.commands.C_Lob;
import frc.robot.commands.C_Shoot;
import frc.robot.drivers.Limelight;
// import frc.robot.subsystems.SS_Feeder;
import frc.robot.subsystems.SS_Shooter;
// import frc.robot.subsystems.SS_TankDrive;
import frc.robot.util.XboxGamepad;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  //public static final XboxGamepad DRIVE_CONTROLLER = new XboxGamepad(Constants.DRIVE_CONTROLLER_ID, 0.1);

  private static Limelight vision = new Limelight();

  // Subsystems
  // private SS_TankDrive driveBase = new SS_TankDrive();
  private SS_Shooter shooter = new SS_Shooter();
  // private SS_Feeder feeder = new SS_Feeder();

  // Commands
  private C_Shoot shoot;
  private C_Lob lob = new C_Lob(shooter);
  // private C_Feeder feed = new C_Feeder(feeder);
  // private C_Drive drive = new C_Drive(driveBase);

  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    shooter.setDefaultCommand(new C_Shoot(shooter));

    //driveBase.setDefaultCommand(new C_Drive(driveBase));

    //configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() { 
    // new Button(DRIVE_CONTROLLER::getAButton).whenPressed(drive, false);
    // new Button(DRIVE_CONTROLLER::getBButton).whenPressed(shoot, false);
    // new Button(DRIVE_CONTROLLER::getXButton).whenPressed(lob, false);
    // new Button(DRIVE_CONTROLLER::getYButton).whenPressed(feed, false);
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

  // public static XboxGamepad getXboxController() {
  //    return DRIVE_CONTROLLER;
  // }

  public static Limelight getVision(){
    return vision;
  }
}
