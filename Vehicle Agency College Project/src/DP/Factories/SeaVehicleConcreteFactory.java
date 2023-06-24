// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Factories;

import java.util.Dictionary;

import VehicleStats.Vehicle;
import VehicleTypes.CruiseShip;
import VehicleTypes.Frigate;

public class SeaVehicleConcreteFactory implements AbstractVehicleFactory {

	Vehicle wantedVehicle;

	@Override
	public Vehicle CreateVehicle(String type, Dictionary<String, Object> params) {

		String model = null;
		int maxPassenger = 0;
		int maxSpeed = 0;
		int averageFuelConsumption = 0;
		int averageLifetime = 0;
		String flag = null;
		switch (type) {
		case "Cruise Ship": {
			if (params.get("M") instanceof String)
				model = (String) params.get("M");
			if (params.get("M") instanceof Integer)
				maxPassenger = ((Integer) params.get("MP")).intValue();
			if (params.get("M") instanceof Integer)
				maxSpeed = ((Integer) params.get("MS")).intValue();
			if (params.get("AFC") instanceof Integer)
				averageFuelConsumption = ((Integer) params.get("AFC")).intValue();
			if (params.get("AL") instanceof Integer)
				averageLifetime = ((Integer) params.get("AL")).intValue();
			if (params.get("F") instanceof String)
				flag = (String) params.get("F");
			wantedVehicle = new CruiseShip(model, maxPassenger, maxSpeed, averageFuelConsumption, averageLifetime,
					flag);
			break;

		}
		case "Frigate": {
			if (params.get("M") instanceof String)
				model = (String) params.get("M");
			if (params.get("M") instanceof Integer)
				maxPassenger = ((Integer) params.get("MP")).intValue();
			if (params.get("M") instanceof Integer)
				maxSpeed = ((Integer) params.get("MS")).intValue();
			wantedVehicle = new Frigate(model, maxPassenger, maxSpeed);
			break;
		}
		}
		return wantedVehicle;
	}

}
