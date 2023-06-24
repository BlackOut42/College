// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.LandVehicle;
import VehicleStats.Powered;

import static java.lang.Thread.sleep;

public class ElectricBicycles extends LandVehicle implements Powered {
    private int averageFuelConsumption;
    private final int averageLifetime;

    public ElectricBicycles(String model, int maxSpeed, String roadType, int averageLifetime) {
        super(model, 1, maxSpeed, 2, roadType);
        this.averageFuelConsumption = 20;
        this.averageLifetime = averageLifetime;
    }
    @Override
    public int getAverageFuelConsumption() {return averageFuelConsumption;}
    @Override
    public void setAverageFuelConsumption(int averageFuelConsumption) {this.averageFuelConsumption = averageFuelConsumption;}
    @Override
    public int getAverageEngineLifeExpectancy() {return averageLifetime;}

    @Override
    public String toString() {
        return "Electric Bicycles:" + super.toString() + ", Average Fuel Consumption: " + averageFuelConsumption + ", Engine life expectancy: "
                + averageLifetime;
    }
    public boolean equals(Object electricBicycles) {
        if (this == electricBicycles) return true;
        if (!(electricBicycles instanceof ElectricBicycles newElectricBicycles)) return false;
        if (!super.equals(newElectricBicycles)) return false;
        return averageFuelConsumption == newElectricBicycles.averageFuelConsumption && averageLifetime == newElectricBicycles.averageLifetime;
    }
}
