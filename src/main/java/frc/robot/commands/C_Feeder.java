// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.SS_Feeder;

// public class C_Feeder extends CommandBase {
//   /** Creates a new C_Feeder. */
//   private SS_Feeder feeder;

//   public C_Feeder(SS_Feeder feeder) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     this.feeder = feeder;
//     addRequirements(feeder);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     feeder.setSpeed(0.2);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
