// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.Powered;
import VehicleStats.SeaVehicle;
import VehicleStats.Vehicle;

import static java.lang.Thread.sleep;

public class Frigate extends Vehicle implements SeaVehicle, Powered {
    private String flag;
    private boolean windDirection; // True == WithWind, False == AgainstWind
    private int averageFuelConsumption;
    private final int averageLifetime;
    public Frigate(String model, int maxPassengers, int maxSpeed) {
        super(model, maxPassengers, maxSpeed);
        flag = "Israel";
        windDirection = false;
        this.averageFuelConsumption = 500;
        this.averageLifetime = 4;
    }
    public void setFlag(String flag) {this.flag = flag;}
    public void setWindDirection(boolean windDirection) {
        this.windDirection = windDirection;
    }
    public boolean getWindDirection() {
        return windDirection;
    }
    public String getFlag() {
        return flag;
    }
    public int getAverageFuelConsumption() {
        return averageFuelConsumption;
    }
    public void setAverageFuelConsumption(int averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }
    public int getAverageEngineLifeExpectancy() {
        return averageLifetime;
    }

    public String toString() {
        String direction = "against";
        if (getWindDirection())
            direction = "with";
        return "Frigate: " + super.toString() + ",Under " + flag + " flag, Sails " + direction + " the wind, " + "Average Fuel Consumption: " + averageFuelConsumption +
                "L, Engine life expectancy: " + averageLifetime + " years";
    }
    public boolean equals(Object frigate) {
        if (this == frigate) return true;
        if (!(frigate instanceof Frigate newFrigate)) return false;
        if (!super.equals(frigate)) return false;
        return averageLifetime == newFrigate.averageLifetime &&
                averageFuelConsumption == newFrigate.averageFuelConsumption;

    }
}
