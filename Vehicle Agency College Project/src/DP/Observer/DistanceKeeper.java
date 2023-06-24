// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Observer;
import static  Graphic.VehicleAgency.updateTotalDistance;


public class DistanceKeeper {

	long totalDistance = 0;
	static DistanceKeeper instance;
	
	private DistanceKeeper() {
		instance = this;
	}
	
	public static DistanceKeeper getIntance()
	{
		if (instance != null)
			return instance;
		else {
			return new DistanceKeeper();
		}
	}
	
	public synchronized void UpdateDistance(int distance)
	{
		totalDistance += (long)distance;
		updateTotalDistance(totalDistance);
	}
	
	public long getTotal() {return totalDistance;}


}
