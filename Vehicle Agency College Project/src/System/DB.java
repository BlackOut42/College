// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package System;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import DP.Decorator.ColoredVehicle;
import DP.Decorator.VehicleInterface;
import DP.Memento.DBMemento;
import DP.Observer.DistanceKeeper;
import Graphic.VehicleAgency;
import Graphic.VehiclePanel;
import VehicleStats.Vehicle;
import VehicleStats.VehicleLock;
import static Graphic.VehicleAgency.setDisableColorButton;
import static Graphic.VehicleAgency.setDisableTestButton;
import static Graphic.VehicleAgency.setDisableBuyButton;
import static  Graphic.VehicleAgency.updateTotalDistance;
import static  Graphic.VehicleAgency.setEnableVisibleButtonPanel;


public class DB {

	public static ArrayList<VehiclePanel> VehiclePanelArray;
	private static DistanceKeeper keeperDistance = DistanceKeeper.getIntance();
	private static VehiclePanel chosenPanel;
	static boolean created;

	public DB() {

		if (!created) {
			VehiclePanelArray = new ArrayList<VehiclePanel>();
			created = true;
		}
	}

	public static void setChosen(VehiclePanel chosen) {
		chosenPanel = chosen;
		for (VehiclePanel vehicle : VehiclePanelArray) {
			if (vehicle != chosenPanel) {
				if (vehicle.getVehicle() instanceof ColoredVehicle)
					vehicle.setBorder(
							BorderFactory.createLineBorder(((ColoredVehicle) vehicle.getVehicle()).getColor(), 5));
				else
					vehicle.setBorder(BorderFactory.createLineBorder(new Color(0xFCFFB2), 5));
				vehicle.selected = false;
			}
		}
	}

	public static VehiclePanel getChosen() {
		return chosenPanel;
	}

	public static DBMemento createMemento() {
		VehiclePanel newVehicle = null ;
		Object newVehicleInterface = null;
		ArrayList<VehiclePanel> newList = new ArrayList<>();
		for (VehiclePanel currentVehicle : VehiclePanelArray) {
			if (currentVehicle.getVehicle() instanceof ColoredVehicle) {
				try {
				newVehicleInterface = ((Vehicle)((ColoredVehicle) (currentVehicle.getVehicle())).getVehicle()).clone();
				}
				catch (Exception e) {System.out.println("Problem cloning in MEMENTO DB.line: 62");}
				newVehicle = new VehiclePanel(
						new ColoredVehicle((Vehicle)newVehicleInterface,
								((ColoredVehicle) (currentVehicle.getVehicle())).getColor()),
						currentVehicle.getImageText());
			} else
				try {
				newVehicle = new VehiclePanel((Vehicle)(((Vehicle)currentVehicle.getVehicle()).clone()), currentVehicle.getImageText());
				}
			catch (Exception e) {System.out.println("Problem cloning in MEMENTO DB.line: 71");}

			newList.add(newVehicle);
		}
		return new DBMemento(newList,keeperDistance.getTotal());
	}

	public static void restore(DBMemento m) {
		VehiclePanelArray.clear();
		ArrayList<VehiclePanel> newPanel = m.getVehiclePanelArray();
		for (VehiclePanel vehicle : newPanel) {
			VehiclePanelArray.add(vehicle);
		}
		setChosen(null);
		setEnableVisibleButtonPanel();
		setDisableBuyButton();
		setDisableTestButton();
		setDisableColorButton();
		updateTotalDistance(m.getTotalDistance());
		VehicleAgency.updateCenterPanel();
	}

}
