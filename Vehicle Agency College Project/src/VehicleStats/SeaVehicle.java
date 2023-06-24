// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleStats;

import VehicleTypes.Frigate;

public interface SeaVehicle {
    String getFlag();
    void setFlag(String flag);
    void setWindDirection(boolean windDirection);
    boolean getWindDirection();
}
