// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Factories;

import java.util.Dictionary;

import VehicleStats.Vehicle;
import VehicleTypes.CommercialDrone;
import VehicleTypes.SpyDrone;

public class AirVehicleConcreteFactory implements AbstractVehicleFactory{

	Vehicle wantedVehicle;
	
	public AirVehicleConcreteFactory(){}
	
	@Override
	public Vehicle CreateVehicle(String type,Dictionary<String, Object> params) {

		String powerSource = null;
		
		switch(type){
			case "Toy Drone" :
			{
				wantedVehicle = new CommercialDrone();
				break;
			}
			case "Spy Drone" :
			{
				if(params.get("PS") instanceof String)
					powerSource = (String)params.get("PS");
				wantedVehicle = new SpyDrone(powerSource);
				break;
			}
			
		}
		
		return wantedVehicle;
	}
}
