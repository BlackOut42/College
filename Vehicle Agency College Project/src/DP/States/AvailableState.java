// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.States;

import DP.Decorator.VehicleInterface;
import VehicleStats.Vehicle;

public class AvailableState implements StateInterface {

	VehicleInterface vehicle;
	public AvailableState(VehicleInterface vehicle)
	{
		this.vehicle = vehicle;
	}
	@Override
	public boolean move(long distance) {
        if (distance >= 0) {
        	long newDistance = vehicle.getDistanceTraveled() + distance;
        	((Vehicle)vehicle).setDistanceTraveled(newDistance);
            return true;
        }
        return false;
	}
	
	public String toString()
	{
		return "Status: Available ";
	}

}
