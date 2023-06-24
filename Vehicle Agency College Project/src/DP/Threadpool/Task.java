// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package DP.Threadpool;


import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DP.Decorator.ColoredVehicle;
import DP.Decorator.VehicleInterface;
import DP.Observer.DistanceKeeper;
import Graphic.ProgressBar;
import Graphic.VehicleAgency;
import VehicleStats.Vehicle;
import static Graphic.VehicleAgency.setEnableLoadButton;
import static Graphic.VehicleAgency.setEnableSaveButton;



public class Task implements Runnable  {
	VehicleInterface currentVehicle;
	JDialog waitMessage;
	int distance = 0;
	
	//Observer
	DistanceKeeper keeper = DistanceKeeper.getIntance();
	
	public Task(JDialog waitMessage) {this.waitMessage = waitMessage;}
	@Override
	public void run() {
		new Thread(() -> {
			runningProgressBar(distance, currentVehicle.getModel());
		}).start();
		try {
			Thread.sleep(100L * distance);
		} catch (Exception e) {
		}
		if (currentVehicle instanceof ColoredVehicle)
			((Vehicle) ((ColoredVehicle) currentVehicle).getVehicle()).setState("Available");
		else
			((Vehicle) currentVehicle).setState("Available");
		setEnableLoadButton();
		setEnableSaveButton();
		keeper.UpdateDistance(this.distance);
		currentVehicle.move(distance);
		JOptionPane.showMessageDialog(null,
				"Success! " + "\n" + "drove for : " + distance + " KM.", "Test Drive",
				JOptionPane.INFORMATION_MESSAGE);
		VehicleAgency.updateCenterPanel();
	}
	
	public void UpdateDistance(int distance){
		this.distance = distance;
	}
	
	
	public void setVehicle(VehicleInterface newVehicle)
	{
		waitMessage.setVisible(true);
		currentVehicle = newVehicle;
	}
	
	void runningProgressBar(long time, String name) {
		waitMessage.dispose();
		ProgressBar progressBar = new ProgressBar(name);
		progressBar.setVisible(true);
		long finishTime = System.currentTimeMillis() + 100L * time;
		progressBar.showProgress(finishTime);
	}

}
