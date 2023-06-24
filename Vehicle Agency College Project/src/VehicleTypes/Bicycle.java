// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.LandVehicle;
import VehicleStats.Unpowered;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class Bicycle extends LandVehicle implements Unpowered {
    private final String powerSource;
    private final String energyScore;

    public Bicycle(String model, int maxSpeed, String roadType) {
        super(model, 1, maxSpeed, 2, roadType);
        this.powerSource = "Manual";
        this.energyScore = "A";
    }
    public String getPowerSource() {return powerSource;}
    public String getEnergyScore() {return energyScore;}


    public String toString() {
        return "Bicycle: " + super.toString() + " Power source: " + powerSource + ", Energy Score: "
                + energyScore;
    }
    public boolean equals(Object bicycle) {
        if (this == bicycle) return true;
        if (!(bicycle instanceof Bicycle newBicycle)) return false;
        if (!super.equals(newBicycle)) return false;
        return Objects.equals(powerSource, newBicycle.powerSource) && Objects.equals(energyScore, newBicycle.energyScore);
    }
}
