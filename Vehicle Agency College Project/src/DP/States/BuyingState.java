// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.States;

import DP.Decorator.VehicleInterface;

public class BuyingState implements StateInterface {

	VehicleInterface vehicle;
	public BuyingState(VehicleInterface vehicle)
	{
		this.vehicle = vehicle;
	}
	@Override
	public boolean move(long distance) {
		return false;
	}
	
	public String toString()
	{
		return "Status: Buying stage ";
	}

}
