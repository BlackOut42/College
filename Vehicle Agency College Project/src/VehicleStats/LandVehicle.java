// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleStats;

import java.util.Objects;

public abstract class LandVehicle extends Vehicle {
    private final int numOfWheels;
    private final String roadType;
    public LandVehicle(String model, int maxPassengers, int maxSpeed, int numOfWheels, String roadType) {
        super(model, maxPassengers, maxSpeed);
        this.numOfWheels = numOfWheels;
        this.roadType = roadType;
    }
    public int getNumOfWheels() {
        return numOfWheels;
    }
    public String getRoadType() {
        return roadType;
    }
    public String toString() {
        return super.toString() + ",Number of wheels: " + numOfWheels + ", Drivable on road of type: " + roadType + " ";
    }
    public boolean equals(Object landVehicle) {
        if (this == landVehicle) return true;
        if (!(landVehicle instanceof LandVehicle newLandVehicle)) return false;
        if (!super.equals(landVehicle)) return false;
        return numOfWheels == newLandVehicle.numOfWheels && Objects.equals(roadType, newLandVehicle.roadType);
    }

}