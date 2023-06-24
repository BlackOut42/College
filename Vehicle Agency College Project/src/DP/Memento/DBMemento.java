// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500

package DP.Memento;

import Graphic.VehiclePanel;
import VehicleStats.VehicleLock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBMemento {
    private final ArrayList<VehiclePanel> VehiclePanelArrayM;
    private final String formattedDateTime;
    private final long totalDistance;

    public DBMemento(ArrayList<VehiclePanel> VehiclePanelArray,long totalDistance) {
        VehiclePanelArrayM = VehiclePanelArray;
        this.totalDistance = totalDistance;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        formattedDateTime = currentDateTime.format(formatter);
    }
    public ArrayList<VehiclePanel> getVehiclePanelArray() {return VehiclePanelArrayM;}
    public String toString() {
        return "Saved at: " + formattedDateTime;
    }
    public long getTotalDistance() {return totalDistance;}
}
