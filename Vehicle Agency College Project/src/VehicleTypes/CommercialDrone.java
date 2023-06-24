// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.AirVehicle;
import VehicleStats.Unpowered;
import VehicleStats.Vehicle;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class CommercialDrone extends Vehicle implements Unpowered, AirVehicle {
    private boolean use; // True == military, False == civilian
    private final String powerSource;
    private final String energyScore;
    public CommercialDrone () {
        super("Toy", 0, 10);
        this.powerSource = "Manual";
        this.energyScore = "A";
        this.use = false;
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
            return " Commercial Drone: , Military use, " + super.toString() + " Power source: " + powerSource + ", Energy Score: "
                    + energyScore;
        }
        return " Commercial Drone: , Civilian use, " + super.toString() + " Power source: " + powerSource + ", Energy Score: "
                + energyScore;
    }
    public boolean equals(Object commercialDrone) {
        if (this == commercialDrone) return true;
        if (!(commercialDrone instanceof CommercialDrone newCommercialDrone)) return false;
        if (!super.equals(commercialDrone)) return false;
        return Objects.equals(powerSource, newCommercialDrone.powerSource) &&
                Objects.equals(energyScore, newCommercialDrone.energyScore);
    }
}

