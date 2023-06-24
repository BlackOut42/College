// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Decorator;

public interface VehicleInterface {
	boolean move(long distance);
	void nullifyDistance();
	long getDistanceTraveled();
	String getModel();
	int getMaxSpeed();
	int getMaxPassengers();
	String toString();
	boolean equals(Object vehicle);
}
