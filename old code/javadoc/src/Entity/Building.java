package Entity;

import java.util.ArrayList;

/**
 * The class define the object Building: <br>
 * @author Yuxiang
 * @version 1.0
 */
public class Building {
   /**
    * building's name
    */
   private String name;
   /**
    * floors belong to this building
    */
   private ArrayList<Floor> floors;
   /**
    * building's coordinates on campus map
    */
   private Coordinates coordinates;
   
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Floor> getFloors() {
		return floors;
	}
	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	  /**
     *   * The function is used to add a building
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Building
     */
	public void addBuilding(){
		
	}
	/**
     *   * The function is used to get the building details
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Location
     */
	public void getBuilding(){
		
	}
}
