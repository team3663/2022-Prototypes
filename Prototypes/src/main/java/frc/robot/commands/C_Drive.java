// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import javax.swing.plaf.synth.SynthToolBarUI;

// import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SS_TankDrive;

public class C_Drive extends CommandBase {

  private final SS_TankDrive driveBase;
  
  private Supplier<Double> leftYAxisSpeedSupplier;
  private Supplier<Double> rightZAxisRotateSupplier;

  public C_Drive(SS_TankDrive driveBase,
                Supplier<Double> leftYAxisSpeedSupplier,
                Supplier<Double> rightZAxisRotateSupplier) {
    this.driveBase = driveBase;
    this.leftYAxisSpeedSupplier = leftYAxisSpeedSupplier;
    this.rightZAxisRotateSupplier = rightZAxisRotateSupplier;
    addRequirements(driveBase);
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("C_Drive started.");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(driveController.getYButtonPressed()){
    //   ss_TankDrive.setPower(0.2, 0.2);
    // }
    // driveBase.setPower(0.2, 0.2);
    driveBase.arcadeDrive(leftYAxisSpeedSupplier.get(), rightZAxisRotateSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("C_Drive ended.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}