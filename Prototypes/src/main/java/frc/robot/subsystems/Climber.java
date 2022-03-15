// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  public enum HookState {
    OpenFront,
    OpenBack,
    LockedFront,
    LockedBack
  };

  public enum WindmillState {
    Home,
    Start,
    Climb,
    End
  };

  public enum ElevatorState {
    ElevatorUp,
    ElevatorDown
  };

  private HookState currentHookState;
  private WindmillState currentWindmillState;
  private ElevatorState currentElevatorState;

  // Hook Helper class.
  private class Hook {

    private RelativeEncoder hookPose;
    private CANSparkMax hookMotor;
    private SparkMaxPIDController hookPosePID;

    private double openToFront = 0;
    private double openToBack = 0;
    private double lockedToFront = 0;
    private double lockedToBack = 0;

    // PID values
    private double hookP;
    private double hookI;
    private double hookD;

    // LimitSwitches
    public DigitalInput hookLimitSwitch1;
    public DigitalInput hookLimitSwitch2;
    public DigitalInput homeLimitSwitch;

    // Gear Ratio
    public int gearRatio = 0;

    // All of these args are in Degreas
    private Hook(int HookCanId, int HookLimitSwitch1Id, int HookLimitSwitch2Id, int HookRotationLimitSwitchId) {

      hookMotor = new CANSparkMax(HookCanId, MotorType.kBrushless);

      hookPose = hookMotor.getEncoder();
      hookPosePID = hookMotor.getPIDController();
      
      hookPose.setPositionConversionFactor(gearRatio);

      // Setting the PID Values
      hookPosePID.setP(hookP);
      hookPosePID.setI(hookI);
      hookPosePID.setD(hookD);

      
    //   hookLimitSwitch1 = new DigitalInput(HookLimitSwitch1Id);
    //   hookLimitSwitch2 = new DigitalInput(HookLimitSwitch2Id);
      homeLimitSwitch = new DigitalInput(HookRotationLimitSwitchId);

      hookMotor.setIdleMode(IdleMode.kBrake);
    }

    public void home() {
      hookMotor.set(-0.05);
      if (homeLimitSwitch.get()) {
        hookMotor.set(0);
        hookPose.setPosition(0);
      }
    }

    // returns true if the hook is at the position.
    public void setHookPosition(HookState position) {
      switch (position) {
        case OpenFront:
          hookPosePID.setReference(openToFront, ControlType.kPosition);
          currentHookState = HookState.OpenFront;
          break;
        case OpenBack:
          hookPose.setPosition(openToBack);
          currentHookState = HookState.OpenBack;
          break;
        case LockedFront:
          hookPose.setPosition(lockedToFront);
          currentHookState = HookState.LockedFront;
          break;
        case LockedBack:
          hookPose.setPosition(lockedToBack);
          currentHookState = HookState.LockedBack;
          break;
      }
    }

    public boolean limitSwitchesPressed(){
      if(hookLimitSwitch1.get() && hookLimitSwitch2.get()){
        return true;
      }
      return false;
    }
  }

  private class Windmill {

    // Phisical controllers
    private CANSparkMax windmillMotor;
    private CANSparkMax windmillFollowerMotor;
    private RelativeEncoder windmillEncoder;

    private SparkMaxPIDController windmillPIDController;

    // private DigitalInput windmillLimitSwitch;

    // Phisical Offsets and speeds
    private double windmillRotaionZero;
    private double windmillRotationSpeed = 0.5;

    // Timers
    private Timer windmillOvertravelTimer;
    private boolean windmillOvertravelTimerStarted;

    // Gear Ratios
    private final double GEAR_RATIO = 5; // 5 to 1

    // Windmill Positions
    private final double HOME_ROTATION = 0;
    private final double CLIMB_ROTAION = 0;
    private final double START_ROTATION = 0;
    private final double SHIFT_WEIGHT_ROTATION = -0;
    private final double END_ROTATION = 0;

    // PID Values
    private double windmillP = 0.0000001;
    private double windmillI = 0.0;
    private double windmillD = 0.0;

    public Windmill(int WindmillCanId, int WindmillFollowerCanId) {
      // Creating Objects
      windmillMotor = new CANSparkMax(WindmillCanId, MotorType.kBrushless);
      windmillFollowerMotor = new CANSparkMax(WindmillFollowerCanId, MotorType.kBrushless);
      windmillOvertravelTimer = new Timer();

      // Setting Modes
      windmillFollowerMotor.follow(windmillMotor, true);
      windmillFollowerMotor.setIdleMode(IdleMode.kBrake);
      windmillMotor.setIdleMode(IdleMode.kBrake);

      // Setting Local Varbles
      windmillPIDController = windmillMotor.getPIDController();
      windmillEncoder = windmillMotor.getEncoder();
      windmillEncoder.setPositionConversionFactor(GEAR_RATIO);

      // Setting PIDs
      windmillPIDController.setP(windmillP);
      windmillPIDController.setI(windmillI);
      windmillPIDController.setD(windmillD);
      windmillPIDController.setOutputRange(windmillRotationSpeed, -windmillRotationSpeed);

    }

    // public boolean home() {
    //   if (!windmillOvertravelTimerStarted) {
    //     windmillOvertravelTimer.start();
    //   }
    //   windmillMotor.set(0.2);
    //   if (windmillLimitSwitch.get()) {
    //     windmillMotor.set(0);
    //     windmillRotaionZero = windmillEncoder.getPosition();
    //     return true;
    //   }
    //   if (windmillOvertravelTimer.get() <= 0.5) {
    //     windmillMotor.set(0);
    //     return false;
    //   }
    //   return false;
    // }

    public void rotateWindmill(WindmillState position, double offset) {
      switch (position) {
        case Home:
          windmillPIDController.setReference(HOME_ROTATION + windmillRotaionZero + offset, ControlType.kPosition);
          currentWindmillState = WindmillState.Home;
          break;
        case Start:
          windmillPIDController.setReference(START_ROTATION + windmillRotaionZero + offset, ControlType.kPosition);
          currentWindmillState = WindmillState.Start;
          break;
        case Climb:
          windmillPIDController.setReference(CLIMB_ROTAION + windmillRotaionZero + offset, ControlType.kPosition);
          currentWindmillState = WindmillState.Climb;
          break;
        case End:
          windmillPIDController.setReference(END_ROTATION + windmillRotaionZero + offset, ControlType.kPosition);
          currentWindmillState = WindmillState.End;
          break;
      }
    }

    public void shiftRobotWeight(){
      //windmillPIDController.setReference(, ControlType.kPosition);
    }
  }

  private class Elevator {

    private CANSparkMax elevatorMotor;
    private RelativeEncoder elevatorEncoder;
    private SparkMaxPIDController elevatorPIDController;
    private DigitalInput elevatorLimitSwitch;

    // Elevator PID
    private double elevatorP;
    private double elevatorI;
    private double elevatorD;

    // Limits
    private double minHeight;
    private double maxHeight = 5;

    // Gear Ratio
    private double gearRatio = 1;

    // Speed
    private double POWER = 0.2;

    public Elevator(int ElevatorCanId, int ElevatorLimitSwitch) {
      elevatorMotor = new CANSparkMax(ElevatorCanId, MotorType.kBrushless);

      elevatorEncoder = elevatorMotor.getEncoder();
      elevatorPIDController = elevatorMotor.getPIDController();

      elevatorEncoder.setPositionConversionFactor(gearRatio);

      elevatorMotor.setIdleMode(IdleMode.kBrake);

      elevatorPIDController.setP(elevatorP);
      elevatorPIDController.setI(elevatorI);
      elevatorPIDController.setD(elevatorD);
    }

    public void home(){
      elevatorMotor.set(0.2);
      if(elevatorLimitSwitch.get()){
        minHeight = elevatorEncoder.getPosition();
        elevatorMotor.set(0.0);
      }
    }

    public void setElevatorPosition(ElevatorState position) {
      switch (position) {
        case ElevatorUp:
          elevatorPIDController.setReference(maxHeight, ControlType.kDutyCycle);
          currentElevatorState = ElevatorState.ElevatorUp;
          break;
        case ElevatorDown:
          elevatorPIDController.setReference(minHeight, ControlType.kDutyCycle);
          currentElevatorState = ElevatorState.ElevatorDown;
          break;
      }
    }
  }

  private Hook hookAB;
  private Hook hookXY;
  private Windmill windmill;
  private Elevator elevator;

  public Climber(int ElevatorCanId, int WindmillCanId, int WindmillFollowerCanId, int HookABCanId, int HookXYCanId,
      int ElevatorLimitSwitchId, int HookABRotationLimitSwitchId, int HookXYRotationLimitSwitchId, int Hook1LimitSwitchId, 
      int Hook2LimitSwitchId, int Hook3LimitSwitchId, int Hook4LimitSwitchId) {

    hookAB = new Hook(HookABCanId, Hook1LimitSwitchId, Hook2LimitSwitchId, HookABRotationLimitSwitchId);
    hookXY = new Hook(HookXYCanId, Hook3LimitSwitchId, Hook4LimitSwitchId, HookXYRotationLimitSwitchId);
    windmill = new Windmill(WindmillCanId, WindmillFollowerCanId);
    elevator = new Elevator(ElevatorCanId, ElevatorLimitSwitchId);
  }

  
  public void resetClimberHome() {
    hookAB.home();
    hookXY.home();
    // windmill.home();
    elevator.home();
  }

  public void elevatorUp(){
    elevator.setElevatorPosition(ElevatorState.ElevatorUp);
  }

  public void elevatorHome(){
    elevator.home();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

//add a method in windmill to adjust robot weight
//figure out numbers for posistions (hooks,
//current spike for hook zero
//three modes, climb to first and stop, climb to second auto and stop, climb to third auto and stop
//current mode tracks which bar it is on so we can go to third from second
