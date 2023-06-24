// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500

package DP.Decorator;

import java.awt.Color;

public class ColoredVehicle implements VehicleInterface {
	
	VehicleInterface vehicle;
	Color color;
	
	public ColoredVehicle(VehicleInterface vehicle,Color color)
	{
		this.vehicle = vehicle;
		this.color = color;
	}

	@Override
	public boolean move(long distance) {
			return vehicle.move(distance);
	}

	@Override
	public void nullifyDistance() {
		vehicle.nullifyDistance();
		
	}

	@Override
	public long getDistanceTraveled() {
		return vehicle.getDistanceTraveled();
	}

	@Override
	public String getModel() {
		return vehicle.getModel();
	}

	@Override
	public int getMaxSpeed() {
		return vehicle.getMaxSpeed();
	}

	@Override
	public int getMaxPassengers() {
		return vehicle.getMaxPassengers();
	}
	@Override
	public String toString() {return vehicle.toString();}
	
	public Color getColor(){return color;}
	
	public void setColor(Color color) {this.color = color;}
	
	public VehicleInterface getVehicle() {return vehicle;}

}
