// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.AirVehicle;
import VehicleStats.Unpowered;
import VehicleStats.Vehicle;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class SpyDrone extends Vehicle implements Unpowered, AirVehicle {
    private boolean use; // True == military, False == civilian
    private final String powerSource;
    private final String energyScore;
    public SpyDrone (String powerSource) {
        super("Confidential", 1, 50);
        this.powerSource = powerSource;
        this.energyScore = "C";
        this.use = true;
    }
    public String getPowerSource() {
        return powerSource;
    }
    public String getEnergyScore() {
        return energyScore;
    }
    public void setNewUsage(boolean newUsage) {use = newUsage;}
    public boolean getUsage() {return use;}

    public String toString() {
        if (use) {
            return " Spy Drone: , Military use, " + super.toString() + ",Power source: " + powerSource + ", Energy Score: "
                    + energyScore;
        }
        return " Spy Drone: , Civilian use, " + super.toString() + ",Power source: " + powerSource + ", Energy Score: "
                + energyScore;
    }
    public boolean equals(Object spyDrone) {
        if (this == spyDrone) return true;
        if (!(spyDrone instanceof SpyDrone newSpyDrone)) return false;
        if (!super.equals(spyDrone)) return false;
        return Objects.equals(powerSource, newSpyDrone.powerSource) &&
                Objects.equals(energyScore, newSpyDrone.energyScore);
    }
}
