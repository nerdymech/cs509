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
   /**
    *   Location's floor
    */
   private Floor floor;
   
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
	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
}
