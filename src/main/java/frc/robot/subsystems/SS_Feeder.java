// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class SS_Feeder extends SubsystemBase {
//   /** Creates a new SS_Feeder. */

//   //private CANSparkMax feedMotor = new CANSparkMax(Constants.FEEDER_CAN, MotorType.kBrushless);
//   private CANSparkMax feedMotor = new CANSparkMax(Constants.FEEDER_CAN, MotorType.kBrushed);

//   public SS_Feeder() {  }

//   public void setSpeed(double speed){
//     feedMotor.set(speed);
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
