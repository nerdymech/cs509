package Entity;

import java.util.ArrayList;

import Controller.DatabaseMethods;

public class Test {
    public static void main(String[] args) {
    	
    	DatabaseMethods dm = new DatabaseMethods();
    	
		
//		Point p=new Point();
//		p.setPointID(10);
//		p.setxCooridinate(23423);
//		p.setyCooridinate(12312);
//		p.setBuildName("test");
//		p.setFloorNum(10);
//		p.setAttributes(1);
//		p.setEntranceID(0);
//		p.setRoomNum(100);
//		p.setFeature("bbb");
//		p.setImageURL("asdfghjkl");
//		p.setShowOnMap(false);
//		dm.SavePoint(p);
	 // dm.DeletePoint(567890, 876534);
		
//		ArrayList<Point> a=new ArrayList(dm.ReadPoints());
//		for(Point point:a){
//			System.out.println(point.getImageURL() +" "+point.getShowOnMap());
//		}
//		ArrayList<Admin> ad=new ArrayList(dm.ReadAdmin());
//		for(Admin admin:ad){
//			System.out.println( admin.getAdminName()+" "+admin.getPassword());
//		}
//		ArrayList<User> u=new ArrayList(dm.ReadUser());
//		for(User user:u){
//			System.out.println(user.getUserID() +" "+user.getUserName()+""+user.getPassword());
//		}
//	User u=new User();
//	u.setUserID(11);
//	u.setPassword("12eqwrewr");
//	u.setUserName("rene");
//	dm.SaveUser(u);
//	ArrayList<BuildingEdge>be=new ArrayList(dm.ReadBuildingEdges());
//	for(BuildingEdge buildingEdge:be){
//		System.out.println(buildingEdge.getStartBuildingID() +" "+buildingEdge.getEndBuildingID()+" "+buildingEdge.getWeight());
//	}
//    BuildingEdge b=new BuildingEdge();
//    b.setStartBuildingID(15);
//    b.setEndBuildingID(20);
//    b.setWeight(100);
//    dm.SaveBuildingEdge(b);
//    dm.DeleteBuildingEdge(100,200,108,208 );
    ArrayList<FavouritePlace> favouritePlaces=new ArrayList<>(dm.GetFavouritePlaceByUser(1));
    for(FavouritePlace fPlace:favouritePlaces){
    	System.out.println(fPlace.getPointID()+" "+fPlace.getNotes());
    }
//    FavouritePlace f=new FavouritePlace();
//    f.setUserID(3);
//    f.setPointID(10);
//    f.setNotes("test save");
//    dm.SaveFavouritePlace(f);
    System.out.println(dm.DeleteFavouritePlace(3, 10));
    
    }
}
