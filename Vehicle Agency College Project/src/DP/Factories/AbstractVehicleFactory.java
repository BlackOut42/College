// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Factories;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;

import Graphic.VehicleCreator;
import VehicleStats.Vehicle;

public interface AbstractVehicleFactory {
	
	Vehicle CreateVehicle(String type ,Dictionary<String, Object> params);
	

}
