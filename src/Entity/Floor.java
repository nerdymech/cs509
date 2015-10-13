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
	private ArrayList<Location> locations;
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Location> getLoacations() {
		return locations;
	}
	public void setLoacations(ArrayList<Location> loacations) {
		this.locations = loacations;
	}
    /**
     *   * The function is used to add a floor to building
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Building
     */
	public void addFloor(){
		
	}
	/**
     *   * The function is used to get the floors details
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Location
     */
	public void getFloor(){
		
	}
}
