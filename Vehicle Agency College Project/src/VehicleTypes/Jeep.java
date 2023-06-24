// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.Commercial;
import VehicleStats.LandVehicle;
import VehicleStats.Powered;

import static java.lang.Thread.sleep;


public class Jeep extends LandVehicle implements Powered, Commercial {
    private int averageFuelConsumption;
    private final int averageLifetime;
    private final String licenseType = "MINI";

    public Jeep(String model, int maxSpeed, int averageFuelConsumption, int averageLifetime) {
        super(model, 5, maxSpeed, 4, "Dirt road");
        this.averageFuelConsumption = averageFuelConsumption;
        this.averageLifetime = averageLifetime;
    }
    public void setAverageFuelConsumption(int averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }
    public int getAverageFuelConsumption() {
        return averageFuelConsumption;
    }
    public int getAverageEngineLifeExpectancy() {
        return averageLifetime;
    }
    public String getLicenseType() {
        return licenseType;
    }

    public String toString() {
        return "Jeep: " + super.toString() + ", Average Fuel Consumption: " + averageFuelConsumption +
                "L, Engine life expectancy: " + averageLifetime + " years, License type: " + getLicenseType();
    }
    public boolean equals(Object jeep) {
        if (this == jeep) return true;
        if (!(jeep instanceof Jeep newJeep)) return false;
        if (!super.equals(jeep)) return false;
        return averageLifetime == newJeep.averageLifetime && averageFuelConsumption == newJeep.averageFuelConsumption;
    }
}
