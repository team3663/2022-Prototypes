// package frc.robot.subsystems;

// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import static frc.robot.Constants.*;

// public class ShooterSubsystem extends SubsystemBase {

//   private CANSparkMax motor1 = new CANSparkMax(SHOOTER_1_CAN_ID, MotorType.kBrushless);
//   private CANSparkMax motor2 = new CANSparkMax(SHOOTER_2_CAN_ID, MotorType.kBrushless);
//   private MotorControllerGroup motorGroup = new MotorControllerGroup(motor1, motor2);
//   private RelativeEncoder encoder1 = motor1.getEncoder();

//   boolean running = false;

//     //Network tables for telemetry
//     private NetworkTableEntry runningEntry;
//     private NetworkTableEntry powerEntry;
//     private NetworkTableEntry rpmEntry;

//   /** Creates a new ExampleSubsystem. */
//   public ShooterSubsystem() {
//     motorGroup.set(power);
//     motor1.setInverted(true);

//     ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter");
//     runningEntry = shooterTab.add("Running", false)
//       .withPosition(0, 0)
//       .withSize(1, 1)
//       .getEntry();
//     powerEntry = shooterTab.add("Current Power", 0.0)
//       .withPosition(1, 1)
//       .withSize(1, 1)
//       .getEntry();
//     rpmEntry = shooterTab.add("Current RPM", 0.0)
//       .withPosition(2, 0)
//       .withSize(1, 1)
//       .getEntry();

//   }

//   @Override
//   public void periodic() {

//     // Update network tables for Shuffleboard
//     runningEntry.setBoolean(running);
//     powerEntry.setDouble(power);
//     rpmEntry.setDouble(encoder1.getVelocity());
//   }

//   public void start() {
//     running = true;
//     motorGroup.set(power);
//   }

//   public void stop() {
//     running = false;
//     motorGroup.set(0.0);
//   }

//   public void increasePower() {
//       power += powerIncrement;
//       if (power > 1.0) {
//         power = 1.0;
//       }

//       if ( running ) {
//         motorGroup.set(power);
//       }
//   }

//   public void decreasePower() {
//     power -= powerIncrement;
//     if (power < 0.0) {
//       power = 0.0;
//     }

//     if (running) {
//       motorGroup.set(power);
//     }
//   }
// }
