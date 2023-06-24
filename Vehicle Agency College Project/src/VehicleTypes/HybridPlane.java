// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleTypes;

import VehicleStats.AirVehicle;
import VehicleStats.LandVehicle;
import VehicleStats.Powered;
import VehicleStats.SeaVehicle;

import static java.lang.Thread.sleep;

public class HybridPlane extends LandVehicle implements AirVehicle, Powered, SeaVehicle {
    private boolean use; // True == military, False == civilian
    private int averageFuelConsumption;
    private final int averageLifetime;
    private String flag;
    private long tempDis;
    private boolean windDirection; // True == WithWind, False == AgainstWind
    public HybridPlane(String model, int maxPassengers, int maxSpeed, int numOfWheels, int averageFuelConsumption, int averageLifetime,String flag,boolean windDirection) {
        super(model, maxPassengers, maxSpeed, numOfWheels, "Paved road");
        use = true;
        this.averageFuelConsumption = averageFuelConsumption;
        this.averageLifetime = averageLifetime;
        this.flag = flag;
        this.windDirection = windDirection;
    }
    @Override
    public void setNewUsage(boolean newUsage) {use = newUsage;}
    @Override
    public boolean getUsage() {return use;}
    @Override
    public int getAverageFuelConsumption() {return averageFuelConsumption;}
    @Override
    public void setAverageFuelConsumption(int averageFuelConsumption) {this.averageFuelConsumption = averageFuelConsumption;}
    @Override
    public int getAverageEngineLifeExpectancy() {return averageLifetime;}
    @Override
    public String getFlag() {return flag;}
    @Override
    public void setFlag(String flag) {this.flag = flag;}
    @Override
    public void setWindDirection(boolean windDirection) {this.windDirection = windDirection;}
    @Override
    public boolean getWindDirection() {return windDirection;}

    public String toString() {
        String direction = "against";
        if (getWindDirection())
            direction = "with";

        return "Hybrid Plane: " + super.toString() + ",Military use, Under " + flag + " flag, Sails " + direction + " the wind, "+ "Average Fuel Consumption: " + averageFuelConsumption +
                "L, Engine life expectancy: " + averageLifetime + " years";
    }
    public boolean equals(Object hybridPlane) {
        if (this == hybridPlane) return true;
        if (!(hybridPlane instanceof HybridPlane newHybridPlane)) return false;
        if (!super.equals(hybridPlane)) return false;
        return averageLifetime == newHybridPlane.averageLifetime && averageFuelConsumption == newHybridPlane.averageFuelConsumption;
    }
}
