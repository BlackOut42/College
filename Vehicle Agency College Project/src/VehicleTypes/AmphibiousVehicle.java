// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.LandVehicle;
import VehicleStats.Powered;
import VehicleStats.SeaVehicle;

import static java.lang.Thread.sleep;

public class AmphibiousVehicle extends LandVehicle implements SeaVehicle, Powered {
    private int averageFuelConsumption;
    private final int averageLifetime;
    private String flag;
    private boolean windDirection; // True == WithWind, False == AgainstWind
    public AmphibiousVehicle(String model, int maxPassengers, int maxSpeed, int numOfWheels, int averageFuelConsumption, int averageLifetime,String flag,boolean windDirection) {
        super(model, maxPassengers, maxSpeed, numOfWheels, "Paved road");
        this.averageFuelConsumption = averageFuelConsumption;
        this.averageLifetime = averageLifetime;
        this.flag = flag;
        this.windDirection = windDirection;

    }
    public void setFlag(String flag) {this.flag = flag;}
    public int getAverageFuelConsumption() {return averageFuelConsumption;}
    public void setAverageFuelConsumption(int averageFuelConsumption) {this.averageFuelConsumption = averageFuelConsumption;}
    public int getAverageEngineLifeExpectancy() {return averageLifetime;}
    public String getFlag() {return flag;}
    public void setWindDirection(boolean windDirection) {this.windDirection = windDirection;}
    public boolean getWindDirection() {return windDirection;}

    public String toString() {
        String direction = "against";
        if (getWindDirection())
            direction = "with";
        return "Amphibious vehicle: " + super.toString() + "Under " + flag + " flag, Sails " + direction + " the wind, "+ "Average Fuel Consumption: " + averageFuelConsumption +
                "L, Engine life expectancy: " + averageLifetime + " years";
    }
    public boolean equals(Object amphibiousVehicle) {
        if (this == amphibiousVehicle) return true;
        if (!(amphibiousVehicle instanceof AmphibiousVehicle newAmphibiousVehicle)) return false;
        if (!super.equals(amphibiousVehicle)) return false;
        return averageLifetime == newAmphibiousVehicle.averageLifetime && averageFuelConsumption == newAmphibiousVehicle.averageFuelConsumption;
    }
}
