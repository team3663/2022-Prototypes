// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

// Helper class for the Talon SRX to implement as a MotorController
// This is needed if TalonSRX is to be used by arcadeDrive() method
// in edu.wpi.first.wpilibj.drive.DifferentialDrive
public class TalonSRXController extends TalonSRX implements MotorController {
    public TalonSRXController(int deviceNumber) {
        super(deviceNumber);
    }

    @Override
    public void set(double speed) {
        super.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
        return super.getMotorOutputPercent();
    }

    @Override
    public void disable() {
        stopMotor();
    }

    @Override
    public void stopMotor() {
        super.set(ControlMode.PercentOutput, 0);
    }

    // @Override
    // public void pidWrite(double output) {
    //     set(output);
    // }

}