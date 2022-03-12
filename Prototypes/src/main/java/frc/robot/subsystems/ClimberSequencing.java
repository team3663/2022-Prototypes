// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSequencing extends SubsystemBase {

  private enum ClimbMode {
    RUNG_2,
    RUNG_3,
    RUNG_4,
    STOP
  }

  private enum MotorState {
    STOPPED,
    RUNNING,
    STOPPING
  }
  
  private enum WindmillMode {
    STOPPED,
    ROTATING_FORWARD,
    ROTATING_BACKWARD
  }
  
  private enum ClawMode {
    STOPPED,
    ROTATING_FORWARD,
    ROTATING_BACKWARD
  }
  

  public ClimberSequencing() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
  /************************************************************************************************
   * Base class for all of our climb modes
   */
  private abstract class ClimbModeBase {
    ClimbMode id;

    private ClimbModeBase(ClimbMode id) {
        this.id = id;
    }

    protected void init(ClimberSequencing climber) {
    }

    protected boolean run(ClimberSequencing climber) {
        return false;
    }

    protected void end(ClimberSequencing climber) {
    }
  }

  /************************************************************************************************
   * Implements our RUNG_2 mode: 
   * 1. pre-condition: driver has driven the "XY claws" onto rung 2 
   * 2. post-condition: "AB claws" closed on rung 3, "XY claws" closed on rung 2
   * 3. 
   */
  private class Rung2Mode extends ClimbModeBase {
    private Rung2Mode() {
        super(ClimbMode.RUNG_2);
    }

    @Override
    protected void init(ClimberSequencing climber) {
        
    }

    @Override
    protected boolean run(ClimberSequencing climber) {
      // TODO Auto-generated method stub
      return super.run(climber);
    }
    
    @Override
    protected void end(ClimberSequencing climber) {
      // TODO Auto-generated method stub
      super.end(climber);
    }

  }

  /************************************************************************************************
   * Implements our RUNG_3 mode: 
   * 1. pre-condition: "AB claws" closed on rung 3, "XY claws" closed on rung 2
   * 2. post-condition: "AB claws" closed on rung 4, "XY claws" closed on rung 3
   * 3.
   */
  private class Rung3Mode extends ClimbModeBase {
    private Rung3Mode() {
        super(ClimbMode.RUNG_3);
    }

    @Override
    protected void init(ClimberSequencing climber) {
        
    }
  }

  /************************************************************************************************
   * Implements our RUNG_4 mode: 
   * 1. pre-condition: "AB claws" closed on rung 4, "XY claws" closed on rung 3
   * 2. post-condition: "AB claws" closed on rung 4. Robot is hanging on Traverse Bar
   * 3.
   */
  private class Rung4Mode extends ClimbModeBase {
    private Rung4Mode() {
        super(ClimbMode.RUNG_4);
    }

    @Override
    protected void init(ClimberSequencing climber) {
        
    }
  }

  /************************************************************************************************
   * Implements our STOP mode: 
   * 1. pre-condition: 
   * 2. post-condition:
   * 3. 
   */
  private class StopMode extends ClimbModeBase {
    private StopMode() {
        super(ClimbMode.STOP);
    }

    @Override
    protected void init(ClimberSequencing climber) {
        
    }
  }

}
