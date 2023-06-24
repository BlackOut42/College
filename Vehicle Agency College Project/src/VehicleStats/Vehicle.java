// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500

package VehicleStats;

import VehicleTypes.AmphibiousVehicle;
import VehicleTypes.Bicycle;

import java.util.Objects;

import DP.Decorator.VehicleInterface;
import DP.States.AvailableState;
import DP.States.BuyingState;
import DP.States.StateInterface;
import DP.States.TestingState;

import static java.lang.Thread.sleep;

import java.io.ObjectInputFilter.Status;

public abstract class Vehicle implements VehicleInterface, Cloneable {
	private long distanceTraveled;
	private final String model;
	private final int maxPassengers;
	private final int maxSpeed;

	/// State design pattern
	private AvailableState availableState;
	private BuyingState buyingState;
	private TestingState testingState;
	private StateInterface state;

	public Vehicle(String model, int maxPassengers, int maxSpeed) {
		distanceTraveled = 0;
		this.model = model;
		this.maxPassengers = maxPassengers;
		this.maxSpeed = maxSpeed;

		availableState = new AvailableState(this);
		buyingState = new BuyingState(this);
		testingState = new TestingState(this);
		state = availableState;

	}

	public synchronized boolean move(long distance) {
		return state.move(distance);
	}

	public void nullifyDistance() {
		distanceTraveled = 0;
	}

	public long getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(long distance) {
		distanceTraveled = distance;
	}

	public String getModel() {
		return model;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public String toString() {
		return "Model: " + model + ", Mileage: " + distanceTraveled + "KM, Max speed: " + maxSpeed
				+ "MPH, Passenger capacity: " + maxPassengers + " people, " + state.toString();
	}

	private void setDistance(long newDistance) {
		distanceTraveled = newDistance;
	}

	public void setState(String state) {
		switch (state) {
		case "Available": {
			this.state = availableState;
			break;
		}
		case "Test": {
			this.state = testingState;
			break;
		}
		case "Buy": {
			this.state = buyingState;
			break;
		}
		}

	}

	public boolean equals(Object vehicle) {
		if (this == vehicle)
			return true;
		if (!(vehicle instanceof Vehicle newVehicle))
			return false;
		return newVehicle.distanceTraveled == distanceTraveled && Objects.equals(model, newVehicle.model)
				&& maxPassengers == newVehicle.maxPassengers && maxSpeed == newVehicle.maxSpeed;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}