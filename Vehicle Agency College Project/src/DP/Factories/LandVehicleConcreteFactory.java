// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Factories;


import java.util.Dictionary;

import VehicleStats.Vehicle;
import VehicleTypes.Bicycle;
import VehicleTypes.ElectricBicycles;
import VehicleTypes.Jeep;

public class LandVehicleConcreteFactory implements AbstractVehicleFactory {

	Vehicle wantedVehicle;
	
	public LandVehicleConcreteFactory(){}
	
	@Override
	public Vehicle CreateVehicle(String type,Dictionary<String, Object> params) {
		String model= null;
		int maxSpeed = 0;
		int averageFuelConsumption = 0;
		int averageLifetime = 0;
		String roadType = null;
		
		switch(type) {
		case "Jeep": {
			if(params.get("M") instanceof String)
				model = (String)params.get("M");
			if(params.get("MS") instanceof Integer)
				maxSpeed = ((Integer)params.get("MS")).intValue();
			if(params.get("AFC") instanceof Integer)
				averageFuelConsumption = ((Integer)params.get("AFC")).intValue();
			if(params.get("AL") instanceof Integer)
				averageLifetime = ((Integer)params.get("AL")).intValue();
			wantedVehicle = new Jeep(model, maxSpeed, averageFuelConsumption, averageLifetime);
			break;
		}
		case "Bicycle": {
			if(params.get("M") instanceof String)
				model = (String)params.get("M");
			if(params.get("MS") instanceof Integer)
				maxSpeed = ((Integer)params.get("MS")).intValue();
			if(params.get("RT") instanceof String)
				roadType = (String)params.get("RT");
			wantedVehicle = new Bicycle(model, maxSpeed,roadType);
			break;
			
		}
		case "Electric Bicycle": {
			if(params.get("M") instanceof String)
				model = (String)params.get("M");
			if(params.get("MS") instanceof Integer)
				maxSpeed = ((Integer)params.get("MS")).intValue();
			if(params.get("RT") instanceof String)
				roadType = (String)params.get("RT");
			if(params.get("AFC") instanceof Integer)
				averageFuelConsumption = ((Integer)params.get("AFC")).intValue();
			wantedVehicle = new ElectricBicycles(model, maxSpeed,roadType,averageLifetime);
			break;
			
		}
		}

		
		return wantedVehicle;
	}

	
}