// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.Commercial;
import VehicleStats.Powered;
import VehicleStats.SeaVehicle;
import VehicleStats.Vehicle;

import static java.lang.Thread.sleep;

public class CruiseShip extends Vehicle implements SeaVehicle, Powered, Commercial {
    private String flag;
    private boolean windDirection; // True == WithWind, False == AgainstWind
    private int averageFuelConsumption;
    private final int averageLifetime;
    private final String licenseType = "Unlimited";

    public CruiseShip(String model, int maxPassengers, int maxSpeed, int averageFuelConsumption, int averageLifetime,String flag) {
        super(model, maxPassengers, maxSpeed);
       this.flag = flag;
        windDirection = true;
        this.averageFuelConsumption = averageFuelConsumption;
        this.averageLifetime = averageLifetime;
    }
    public void setFlag(String flag) {this.flag = flag;}
    public int getAverageFuelConsumption() {return averageFuelConsumption;}
    public void setAverageFuelConsumption(int averageFuelConsumption) {this.averageFuelConsumption = averageFuelConsumption;}
    public int getAverageEngineLifeExpectancy() {return averageLifetime;}
    public String getFlag() {return flag;}
    public void setWindDirection(boolean windDirection) {this.windDirection = windDirection;}
    public boolean getWindDirection() {return windDirection;}
    public String getLicenseType() {return licenseType;}

    public String toString() {
        String direction = "against";
        if (getWindDirection())
            direction = "with";
        return "Cruise Ship: " + super.toString() + ",Under " + flag + " flag, Sails " + direction + " the wind,  License type: " + licenseType  + ", Average Fuel Consumption: " + averageFuelConsumption +
                "L, Engine life expectancy: " + averageLifetime + " years";
    }
    public boolean equals(Object cruiseShip) {
        if (this == cruiseShip) return true;
        if (!(cruiseShip instanceof CruiseShip newCruiseShip)) return false;
        if (!super.equals(cruiseShip)) return false;
        return averageLifetime == newCruiseShip.averageLifetime && averageFuelConsumption == newCruiseShip.averageFuelConsumption;

    }
}
