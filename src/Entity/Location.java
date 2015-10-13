package Entity;
/**
 * The class define the object Location: <br>
 * @author Yuxiang
 * @version 1.0
 */
public class Location {
	/**
     *  Location's name
     */
   private String name;
  
    /**
    *   Location's coordinate
    */
   private Coordinates coordinate;
  
   
   public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coordinates getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinates coordinate) {
		this.coordinate = coordinate;
	}

	  /**
     *   * The function is used to add a location to a floor
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Building
     */
	public void addLocation(){
		
	}
	/**
     *   * The function is used to get the location details
     * @author Yuxiang
     * @version 1.0  
     * @see Entity.Location
     */
	public void getLocation(){
		
	}
}
