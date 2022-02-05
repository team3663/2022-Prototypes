// Dummy subsystem class

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriverVisionSubsystem extends SubsystemBase {

  UsbCamera cameraA;
  UsbCamera cameraB;
  String cameraAName;
  String cameraBName;
  NetworkTableEntry cameraSelection;

  public DriverVisionSubsystem() {

    cameraA = CameraServer.startAutomaticCapture(0);
    cameraAName = cameraA.getName();

    cameraB = CameraServer.startAutomaticCapture(1);
    cameraBName = cameraB.getName();

    cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
  }

  @Override
  public void periodic() {}

  // Dummy method
  public void enableCameraA() {
    System.out.println("Enabling Camera A");
    cameraSelection.setString(cameraAName);
  }

  public void enableCameraB() {
    System.out.println("Enabling Camera B");
    cameraSelection.setString(cameraBName); 
  }
}