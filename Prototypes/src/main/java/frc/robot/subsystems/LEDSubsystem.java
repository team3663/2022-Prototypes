// Dummy subsystem class

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {

    boolean led0;
    boolean led1;
    boolean led2;
    boolean led3;

    private final int ledCount = 4;

    boolean[] leds = new boolean[ledCount];

    NetworkTableEntry[] entries = new NetworkTableEntry[ledCount];
    NetworkTableEntry slider;

    public LEDSubsystem() {
        initTelemetry();
    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

    public void set(int index, boolean value) {
        leds[index] = value;
    }

    public boolean get(int index) {
        return leds[index];
    }

    private void initTelemetry() {
        ShuffleboardTab tab = Shuffleboard.getTab("Main");

        for (int n = 0; n < ledCount; n++) {
            entries[n] = tab.add(Integer.toString(n), false)
                    .withPosition(n, 0)
                    .withSize(1, 1)
                    .getEntry();
        }

        slider = tab.add("Shooter Adjust", 0)
                .withPosition(0, 2)
                .withSize(4, 1)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", -200, "max", 200, "block increment", 25))
                .getEntry();

    }

    private void updateTelemetry() {

        for (int n = 0; n < ledCount; n++) {
            entries[n].forceSetBoolean(leds[n]);
        }
    }
}