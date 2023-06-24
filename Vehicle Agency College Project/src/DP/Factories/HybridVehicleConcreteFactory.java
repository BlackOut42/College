// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Factories;

import java.util.Dictionary;

import VehicleStats.Vehicle;
import VehicleTypes.AmphibiousVehicle;
import VehicleTypes.HybridPlane;

public class HybridVehicleConcreteFactory implements AbstractVehicleFactory {

	Vehicle wantedVehicle;

	public HybridVehicleConcreteFactory() {
	}

	@Override
	public Vehicle CreateVehicle(String type, Dictionary<String, Object> params) {
		String model = null;
		int maxPassenger = 0;
		int maxSpeed = 0;
		int numberOfWheels = 0;
		int averageFuelConsumption = 0;
		int averageLifetime = 0;
		String flag = null;
		boolean windDirection = false;

		switch (type) {

		case "Amphibious": {
			if (params.get("M") instanceof String)
				model = (String) params.get("M");
			if (params.get("MP") instanceof Integer)
				maxPassenger = ((Integer) params.get("MP")).intValue();
			if (params.get("MS") instanceof Integer)
				maxSpeed = ((Integer) params.get("MS")).intValue();
			if (params.get("NOW") instanceof Integer)
				numberOfWheels = ((Integer) params.get("NOW")).intValue();
			if (params.get("AFC") instanceof Integer)
				averageFuelConsumption = ((Integer) params.get("AFC")).intValue();
			if (params.get("AL") instanceof Integer)
				averageLifetime = ((Integer) params.get("AL")).intValue();
			if (params.get("F") instanceof String)
				flag = (String) params.get("F");
			if (params.get("WD") instanceof Boolean)
				windDirection = ((Boolean) params.get("WD")).booleanValue();
			wantedVehicle = new AmphibiousVehicle(model, maxPassenger, maxSpeed, numberOfWheels, averageFuelConsumption,
					averageLifetime,flag, windDirection);
			break;
		}
		case "Hybrid": {
			if (params.get("M") instanceof String)
				model = (String) params.get("M");
			if (params.get("MP") instanceof Integer)
				maxPassenger = ((Integer) params.get("MP")).intValue();
			if (params.get("MS") instanceof Integer)
				maxSpeed = ((Integer) params.get("MS")).intValue();
			if (params.get("NOW") instanceof Integer)
				numberOfWheels = ((Integer) params.get("NOW")).intValue();
			if (params.get("AFC") instanceof Integer)
				averageFuelConsumption = ((Integer) params.get("AFC")).intValue();
			if (params.get("AL") instanceof Integer)
				averageLifetime = ((Integer) params.get("AL")).intValue();
			if (params.get("F") instanceof String) {
				flag = (String) params.get("F");
				System.out.println(flag);}
			if (params.get("WD") instanceof Boolean)
				windDirection = ((Boolean) params.get("WD")).booleanValue();
			wantedVehicle = new HybridPlane(model, maxPassenger, maxSpeed, numberOfWheels, averageFuelConsumption,
					averageLifetime,flag, windDirection);
			break;
		}
		}

		return wantedVehicle;

	}

}
