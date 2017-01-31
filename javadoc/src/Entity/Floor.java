package Entity;

import java.util.ArrayList;

/**
 * The class define the object Floor: <br>
 * @author Yuxiang
 * @version 1.0
 */
public class Floor {
    /**
     * the number of this floor
     */
	private int number;
	/**
	 * the list of locations which are on this floor
	 */
	private ArrayList<Location> loacations;
	/**
	 * the building which this floor belongs to
	 */
	private Building building;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Location> getLoacations() {
		return loacations;
	}
	public void setLoacations(ArrayList<Location> loacations) {
		this.loacations = loacations;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
}
