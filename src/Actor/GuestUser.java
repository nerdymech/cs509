package Actor;

import Entity.Location;

/**
 * The class defines GuestUser's operations including: <br>
 * 1.getRoute() <br>
 * 2.selectStartLocation() <br>
 * 3.selectEndLocation() <br>
 * @author Yuxiang
 * @version 1.0
 */
public class GuestUser {
     /**
      * startLocation is used to record GuestUser's start point
      */
	 private Entity.Location startLocation;
	/**
      * EndLocation is used to record GuestUser's End point
      */
     private Location endLocation;
    
     /**
      * The function is used to select start location
      * @author Yuxiang
      * @version 1.0  
      * @see Entity.Location
      * @see Controller.LocationManager
      */
     public void selectStartLocation() {
    	 
     }
     /**
      * The function is used to select end location
      * @author Yuxiang
      * @version 1.0  
      * @see Entity.Location
      */
     public void selectEndLocation() {
    	 
     }
     /**
      * The function is used to get a route from start location to end location
      * @author Yuxiang
      * @version 1.0
      * @see Boundary.Display
      * @param startLocation
      * @param endLocation
      */
     public void getRoute(Location startLocation, Location endLocation){
    	 
     }
}
