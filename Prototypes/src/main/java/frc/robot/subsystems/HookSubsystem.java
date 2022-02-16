// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.enums.hookState;

public class HookSubsystem extends SubsystemBase {
  private final int HOME = 0;
  private final int LATCH = 45;
  private final int UNLATCH = 90;
  private final int CLAMP = 55;
  private final int UNCLAMP = 35;

  private boolean homed = false;
  private boolean latched = false;
  private boolean clamped = false;

  private CANSparkMax hookMotor;

  private DigitalInput hookLimitSwitch;

  /** Creates a new HookSubsystem. */
  public HookSubsystem(int motorID, int switchId) {
    hookMotor = new CANSparkMax(motorID, MotorType.kBrushless);
    hookLimitSwitch = new DigitalInput(switchId);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setState(hookState state){
    switch(state){
      case home:
        if(homed) setHome();
        break;
      case latch:
        if(!latched && !clamped && homed) setRotation(LATCH);
        break;
      case unlatch:
        if(!latched && clamped && homed) setRotation(UNLATCH);
        break;
      case clamp:
        if(latched && !clamped && homed) setRotation(CLAMP);
        break;
      case unclamp:
        if(latched && clamped && homed)setRotation(UNCLAMP);
        break;
    }
  }

  private void setHome(){
    hookMotor.set(0.3);
    if(hookLimitSwitch.get()) setRotation(HOME);
    homed = true;
  }

  private void setRotation(final int rotation){
    hookMotor.getEncoder().setPosition(rotation);
  }
}
