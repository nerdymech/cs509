package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.text.AbstractDocument.BranchElement;

import Entity.Admin;
import Entity.Building;
import Entity.BuildingEdge;
import Entity.Edge;
import Entity.FavouritePlace;
import Entity.Point;
import Entity.User;

public class DatabaseMethods {
	 
	public Point GetPointById(int id) {
		ArrayList<Point> all_points = new ArrayList<Point>();
		all_points = ReadPoints();
		Point point=new Point();
		for(Point p:all_points) {
			if(p.getPointID() == id){
				point=p;
			    break;
			}
		}
		return point;
	}
	public Point GetPointByCooridinate(int x,int y){
		ArrayList<Point> all_points = new ArrayList<Point>();
		all_points = ReadPoints();
		Point point=new Point();
		for(Point p:all_points) {
			if(p.getxCooridinate()==x && p.getyCooridinate()==y){
				point=p;
			    break;
			}
		}
		return point;
	}
	public Building GetBuildingByCooridinate(int x,int y){
		ArrayList<Building> all_buildings = new ArrayList<Building>(this.ReadBuildings());
		Building building=new Building();
		for(Building b:all_buildings) {
			if(b.getxCooridinate()==x && b.getyCooridinate()==y){
				building=b;
			    break;
			}
		}
		return building;
	}
	public ArrayList<Point> ReadBuildingPoints(String building_name) {
		ArrayList<Point> all_points = new ArrayList<Point>();
		ArrayList<Point> building_points = new ArrayList<Point>();
		all_points = ReadPoints();
		for(Point p:all_points) {
			if(building_name.equals(p.getBuildName()))
				building_points.add(p);
		}
		return building_points;
	}
	
    public ArrayList<Point> ReadPoints(){
		ArrayList<Point> points= new ArrayList<Point>();
		
		try { 
            File csv = new File("Points.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                Point point=new Point(); 
                point.setPointID(Integer.parseInt(st.nextToken())); 
                point.setxCooridinate(Integer.parseInt(st.nextToken()));
                point.setyCooridinate(Integer.parseInt(st.nextToken()));
                point.setBuildName(st.nextToken());
                point.setFloorNum(Integer.parseInt(st.nextToken()));
                point.setAttributes(Integer.parseInt(st.nextToken()));
                point.setEntranceID(Integer.parseInt(st.nextToken()));
                point.setRoomNum(Integer.parseInt(st.nextToken()));
                point.setFeature(st.nextToken());
                point.setImageURL(st.nextToken());
                point.setShowOnMap(Boolean.parseBoolean(st.nextToken()));
                points.add(point); 
               
            } 
           
            br.close();

        } catch (FileNotFoundException e) { 
             
            e.printStackTrace(); 
        } catch (IOException e) { 
          
            e.printStackTrace(); 
        } 
	  return points;
	}
	public void SavePoint(Point point){
      ArrayList<Point> all_point= new ArrayList<>(this.ReadPoints());
		 try { 
	          boolean no_same_point=true;
	          for(Point p:all_point){
	        	  if (p.getxCooridinate()==point.getxCooridinate() && p.getyCooridinate()==point.getyCooridinate()) {
					no_same_point=false;
				}
	          }
	          if (no_same_point) {
	        	  File csv = new File("Points.csv"); // CSV address 
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
		          int assign_point_ID= all_point.get(all_point.size()-1).getPointID()+1;
		          point.setPointID(assign_point_ID);
		          bw.write("\n"); 
		          bw.write(point.getPointID() + "," + point.getxCooridinate() + ","+ point.getyCooridinate() + ","+ point.getBuildName() + ","+ point.getFloorNum() + ","+ point.getAttributes() + ","+ point.getEntranceID() + ","+ point.getRoomNum() + ","+ point.getFeature() +","+ point.getImageURL() +","+ point.getShowOnMap() );
		          
		          bw.close(); 
			}else {
				System.out.println("same point exists!!");
			}
			  
	    
	        } catch (FileNotFoundException e) { 
	       
	          e.printStackTrace(); 
	        } catch (IOException e) { 
	         
	          e.printStackTrace(); 
	        } 
	}
	public boolean DeletePoint(int xcooridinate, int ycooridinate){
    	boolean find_delete_point=false;
    	ArrayList<Point> all_points= new ArrayList<>(this.ReadPoints());
        for(int i=0;i<all_points.size();i++){
        	if (all_points.get(i).getxCooridinate()==xcooridinate && all_points.get(i).getyCooridinate()==ycooridinate) {
				all_points.remove(i);
				find_delete_point=true;
                break;			
        	}
        
        }
        if (find_delete_point) {
        	try{
        	
        	File csv = new File("Points.csv"); // CSV address
     	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
            bw.write("Point_ID"+","+"X"+","+"Y"+","+"Building"+","+"Floor"+","+"Attributes"+","+"Entrance ID"+","+"Room number"+","+"Feature name"+"ImageURL"+"ShowOnMap");
//	          // add a new line
//	          bw.newLine(); 
	          
	          for(Point p:all_points){
	        	  bw.write("\n"); 
	        	  bw.write(p.getPointID() + "," + p.getxCooridinate() + ","+ p.getyCooridinate() + ","+ p.getBuildName() + ","+ p.getFloorNum() + ","+ p.getAttributes() + ","+ p.getEntranceID() + ","+ p.getRoomNum() + ","+ p.getFeature() + ","+p.getImageURL()+","+p.getShowOnMap().toString());
	        	  
	          }
	          bw.close(); 
		
        	}catch (FileNotFoundException e) { 
     	       
  	          e.printStackTrace(); 
  	        } catch (IOException e) { 
  	         
  	          e.printStackTrace(); 
  	        } 
        }else{
        	System.out.println("no such a point can be deleted!!");
        }
        return find_delete_point;
      	       
	}
    public ArrayList<Building> ReadBuildings(){
    	ArrayList<Building> buildings= new ArrayList<Building>();
    	
    	try { 
            File csv = new File("Buildings.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                Building building= new Building();
                building.setBuildingID(Integer.parseInt(st.nextToken()));
                building.setName(st.nextToken());
                building.setxCooridinate(Integer.parseInt(st.nextToken()));
                building.setyCooridinate(Integer.parseInt(st.nextToken()));
                building.setNumberOfFloors(Integer.parseInt(st.nextToken()));
                buildings.add(building);
               
            } 
           
            br.close();

        } catch (FileNotFoundException e) { 
             
            e.printStackTrace(); 
        } catch (IOException e) { 
          
            e.printStackTrace(); 
        } 
    	return buildings;
    }
    public void SaveBuilding(Building building){
    	ArrayList<Building> all_buildings=new ArrayList<>(this.ReadBuildings()); 
    	try { 
    		 boolean no_same_building=true;
	          for(Building b:all_buildings){
	        	  if (b.getxCooridinate()==building.getxCooridinate() && b.getyCooridinate()==building.getyCooridinate()) {
					no_same_building=false;
				}
	          }
	          if (no_same_building) {
	        	  File csv = new File("Buildings.csv"); // CSV address    	    
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
		          int assign_building_ID=all_buildings.get(all_buildings.size()-1).getBuildingID()+1;
		          building.setBuildingID(assign_building_ID);
		          bw.write("\n");
		          bw.write(building.getBuildingID() + "," + building.getName() + ","+ building.getxCooridinate() + ","+ building.getyCooridinate() + ","+ building.getNumberOfFloors());
		          
		          bw.close(); 
			}else{
				System.out.println("same building exists!!");
			}
	          
	    
	        } catch (FileNotFoundException e) { 
	       
	          e.printStackTrace(); 
	        } catch (IOException e) { 
	         
	          e.printStackTrace(); 
	        } 
    }
    public boolean DeleteBuilding(int xcooridinate, int ycooridinate){
    	boolean find_delete_building=false;
    	ArrayList<Building> all_buildings= new ArrayList<>(this.ReadBuildings());
        for(int i=0;i<all_buildings.size();i++){
        	if (all_buildings.get(i).getxCooridinate()==xcooridinate && all_buildings.get(i).getyCooridinate()==ycooridinate) {
				all_buildings.remove(i);
				find_delete_building=true;
                break;			
        	}
        
        }
        if (find_delete_building) {
        	try{
        	
        	File csv = new File("Buildings.csv"); // CSV address
     	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
            bw.write("BuildingID"+","+"Name"+","+"X"+","+"Y"+","+"NumOfFloors");
          // add a new line
//	          bw.newLine(); 
	          
	          for(Building b:all_buildings){
	        	  bw.write("\n"); 
	        	  bw.write(b.getBuildingID()+ "," + b.getName() + ","+ b.getxCooridinate() + ","+ b.getyCooridinate() + ","+ b.getNumberOfFloors());
	        	  
	          }
	          bw.close(); 
		
        	}catch (FileNotFoundException e) { 
     	       
  	          e.printStackTrace(); 
  	        } catch (IOException e) { 
  	         
  	          e.printStackTrace(); 
  	        } 
        }else{
        	System.out.println("no such a building can be deleted!!");
        }
        return find_delete_building;
      	       
	}
    public ArrayList<Edge> ReadEdges(){
      ArrayList<Edge> edges= new ArrayList<>();
      
    	try { 
            File csv = new File("Edges.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                Edge edge=new Edge();
                edge.setStartPointID(Integer.parseInt(st.nextToken()));
                edge.setEndPointID(Integer.parseInt(st.nextToken()));
                edge.setWeight(Integer.parseInt(st.nextToken()));
                edges.add(edge);
               
            } 
           
            br.close();

        } catch (FileNotFoundException e) { 
             
            e.printStackTrace(); 
        } catch (IOException e) { 
          
            e.printStackTrace(); 
        } 
    	return edges;
    }
    public void SaveEdge(Edge edge){
    	 ArrayList<Edge> all_edges=new ArrayList<>(this.ReadEdges());
    	try {          
    		boolean no_same_edge=true;
	          for(Edge e:all_edges){
	        	  if (e.getStartPointID()== edge.getStartPointID() && e.getEndPointID()==edge.getEndPointID()) {
					no_same_edge=false;
					break;
				}
	          }
	          if (no_same_edge) {
	        	  File csv = new File("Edges.csv"); // CSV address
	      	    
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.

		          bw.write("\n"); 
		          bw.write(edge.getStartPointID() + "," + edge.getEndPointID() + ","+ edge.getWeight());
		          
		          bw.close(); 
			}else {
				System.out.println("same edge exists!!");
			}
	    
	        } catch (FileNotFoundException e) { 
	       
	          e.printStackTrace(); 
	        } catch (IOException e) { 
	         
	          e.printStackTrace(); 
	        } 
    }
    public boolean DeleteEdge(int startpoint_x, int startpoint_y, int endpoint_x, int endpoint_y){
    	boolean find_delete_edge=false;
    	int startpoint_ID=-1;
    	int endpoint_ID=-1;
    	ArrayList<Edge> all_edges= new ArrayList<>(this.ReadEdges());
        ArrayList<Point> all_points=new ArrayList<>(this.ReadPoints());
    	for(Point p:all_points){
    		if (p.getxCooridinate()==startpoint_x && p.getyCooridinate()==startpoint_y) {
				startpoint_ID=p.getPointID();
			}else if (p.getxCooridinate()==endpoint_x && p.getyCooridinate()==endpoint_y) {
				endpoint_ID=p.getPointID();
			}
    	}
        
        for(int i=0;i<all_edges.size();i++){
        	if (all_edges.get(i).getStartPointID()==startpoint_ID && all_edges.get(i).getEndPointID()==endpoint_ID) {
				all_edges.remove(i);
				find_delete_edge=true;
                break;			
        	}
        
        }
        if (find_delete_edge) {
        	try{
        	
        	File csv = new File("Edges.csv"); // CSV address
     	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
            bw.write("StartPointID"+","+"EndPointID"+","+"Weight");
	          // add a new line
//	          bw.newLine(); 
	          
	          for(Edge e:all_edges){
	        	  bw.write("\n"); 
	        	  bw.write(e.getStartPointID()+ "," + e.getEndPointID() + ","+ e.getWeight() );
	        	 
	          }
	          bw.close(); 
		
        	}catch (FileNotFoundException e) { 
     	       
  	          e.printStackTrace(); 
  	        } catch (IOException e) { 
  	         
  	          e.printStackTrace(); 
  	        } 
        }else{
        	System.out.println("no such an edge can be deleted!!");
        }
        return find_delete_edge;
      	       
	}
    public ArrayList<Admin> ReadAdmin(){
    	ArrayList<Admin> admins= new ArrayList<Admin>();
    	
    	try { 
            File csv = new File("Admin.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                Admin admin=new Admin();
                admin.setAdminName(st.nextToken());
                admin.setPassword(st.nextToken());
                admins.add(admin);
               
            } 
           
            br.close();

        } catch (FileNotFoundException e) { 
             
            e.printStackTrace(); 
        } catch (IOException e) { 
          
            e.printStackTrace(); 
        } 
    	return admins;
    } 
    public ArrayList<User> ReadUser(){
    	ArrayList<User> users= new ArrayList<User>();
    	
    	try { 
            File csv = new File("User.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                User user= new User();
                user.setUserID(Integer.parseInt(st.nextToken()));
                user.setPassword(st.nextToken());
                user.setUserName(st.nextToken());
                users.add(user);
            } 
           
            br.close();

        } catch (FileNotFoundException e) { 
             
            e.printStackTrace(); 
        } catch (IOException e) { 
          
            e.printStackTrace(); 
        } 
    	return users;
    }  
    public void SaveUser(User user){
    	ArrayList<User> all_users=new ArrayList<>(this.ReadUser()); 
    	try { 
    		 boolean no_same_user=true;
	          for(User u:all_users){
	        	  if (u.getUserName().equals(user.getUserName())) {
					no_same_user=false;
				}
	          }
	          if (no_same_user) {
	        	  File csv = new File("User.csv"); // CSV address
	      	    
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
                  int assign_user_ID =all_users.get(all_users.size()-1).getUserID()+1;
		          user.setUserID(assign_user_ID);
		          bw.write("\n");
		          bw.write(user.getUserID() + "," + user.getPassword() + ","+ user.getUserName());
		          
		          bw.close(); 
			}else{
				System.out.println("same user exists!!");
			}
	          
	    
	        } catch (FileNotFoundException e) { 
	       
	          e.printStackTrace(); 
	        } catch (IOException e) { 
	         
	          e.printStackTrace(); 
	        } 
    }
    public ArrayList<BuildingEdge> ReadBuildingEdges(){
        ArrayList<BuildingEdge> edges= new ArrayList<>();
        
      	try { 
              File csv = new File("BuildingEdges.csv"); // CSV file address

              BufferedReader br = new BufferedReader(new FileReader(csv));
              br.readLine();
              // Read until last line 
              String line = ""; 
              while ((line = br.readLine()) != null) { 
                  // split every line
                  StringTokenizer st = new StringTokenizer(line, ",");
                  BuildingEdge bEdge=new BuildingEdge();
                  bEdge.setStartBuildingID(Integer.parseInt(st.nextToken()));
                  bEdge.setEndBuildingID(Integer.parseInt(st.nextToken()));
                  bEdge.setWeight(Integer.parseInt(st.nextToken()));
                  edges.add(bEdge);
              } 
             
              br.close();

          } catch (FileNotFoundException e) { 
               
              e.printStackTrace(); 
          } catch (IOException e) { 
            
              e.printStackTrace(); 
          } 
      	return edges;
      }
    public void SaveBuildingEdge(BuildingEdge buildingEdge){
   	 ArrayList<BuildingEdge> all_building_edges=new ArrayList<>(this.ReadBuildingEdges());
   	try {          
   		boolean no_same_building_edge=true;
	          for(BuildingEdge bEdge:all_building_edges){
	        	  if (bEdge.getStartBuildingID()== buildingEdge.getStartBuildingID() && bEdge.getEndBuildingID()==buildingEdge.getEndBuildingID()) {
					no_same_building_edge=false;
					break;
				}
	          }
	          if (no_same_building_edge) {
	        	  File csv = new File("BuildingEdges.csv"); // CSV address
	      	    
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.

		          bw.write("\n"); 
		          bw.write(buildingEdge.getStartBuildingID() + "," + buildingEdge.getEndBuildingID() + ","+ buildingEdge.getWeight());
		          
		          bw.close(); 
			}else {
				System.out.println("same edge exists!!");
			}
	    
	        } catch (FileNotFoundException e) { 
	       
	          e.printStackTrace(); 
	        } catch (IOException e) { 
	         
	          e.printStackTrace(); 
	        } 
   }
    public boolean DeleteBuildingEdge(int startbuilding_x, int startbuilding_y, int endbuilding_x, int endbuilding_y){
    	boolean find_delete_edge=false;
    	int startbuilding_ID=-1;
    	int endbuilding_ID=-1;
    	ArrayList<BuildingEdge> all_building_edges= new ArrayList<>(this.ReadBuildingEdges());
        ArrayList<Building> all_buildings=new ArrayList<>(this.ReadBuildings());
    	for(Building b:all_buildings){
    		if (b.getxCooridinate()==startbuilding_x && b.getyCooridinate()==startbuilding_y) {
				startbuilding_ID=b.getBuildingID();
			}else if (b.getxCooridinate()==endbuilding_x && b.getyCooridinate()==endbuilding_y) {
				endbuilding_ID=b.getBuildingID();
			}
    	}
        
        for(int i=0;i<all_building_edges.size();i++){
        	if (all_building_edges.get(i).getStartBuildingID()==startbuilding_ID && all_building_edges.get(i).getEndBuildingID()==endbuilding_ID) {
				all_building_edges.remove(i);
				find_delete_edge=true;
                break;			
        	}
        
        }
        if (find_delete_edge) {
        	try{
        	
        	File csv = new File("BuildingEdges.csv"); // CSV address
     	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
            bw.write("StartBuidingID"+","+"EndBuildingID"+","+"Weight");
	          // add a new line
//	          bw.newLine(); 
	          
	          for(BuildingEdge buildingEdge: all_building_edges){
	        	  bw.write("\n"); 
	        	  bw.write(buildingEdge.getStartBuildingID()+ "," + buildingEdge.getEndBuildingID() + ","+ buildingEdge.getWeight() );
	        	 
	          }
	          bw.close(); 
		
        	}catch (FileNotFoundException e) { 
     	       
  	          e.printStackTrace(); 
  	        } catch (IOException e) { 
  	         
  	          e.printStackTrace(); 
  	        } 
        }else{
        	System.out.println("no such an edge can be deleted!!");
        }
        return find_delete_edge;
      	       
	}
    public ArrayList<FavouritePlace> ReadFavouritePlace(){
        ArrayList<FavouritePlace> all_favouritePlaces= new ArrayList<>();
        
      	try { 
              File csv = new File("FavouritePlaces.csv"); // CSV file address

              BufferedReader br = new BufferedReader(new FileReader(csv));
              br.readLine();
              // Read until last line 
              String line = ""; 
              while ((line = br.readLine()) != null) { 
                  // split every line
                  StringTokenizer st = new StringTokenizer(line, ",");
                  FavouritePlace fPlace=new FavouritePlace();
                  fPlace.setUserID(Integer.parseInt(st.nextToken()));
                  fPlace.setPointID(Integer.parseInt(st.nextToken()));
                  fPlace.setNotes(st.nextToken());
                  all_favouritePlaces.add(fPlace);
                 
              } 
             
              br.close();

          } catch (FileNotFoundException e) { 
               
              e.printStackTrace(); 
          } catch (IOException e) { 
            
              e.printStackTrace(); 
          } 
      	return all_favouritePlaces;
      }
    public ArrayList<FavouritePlace> GetFavouritePlaceByUser(int userid){
    	  ArrayList<FavouritePlace> all_favouritePlaces = new ArrayList<>(this.ReadFavouritePlace());
          ArrayList<FavouritePlace> user_favouritePlace =new ArrayList<>();
    	  for(FavouritePlace fPlace:all_favouritePlaces){
        	 if (fPlace.getUserID()==userid) {
				user_favouritePlace.add(fPlace);			   
        	 }
         }
    	  return user_favouritePlace;
    }
    public void SaveFavouritePlace(FavouritePlace fPlace){
      	 ArrayList<FavouritePlace> all_favouritePlace=new ArrayList<>(this.ReadFavouritePlace());
      	try {          
      		boolean no_same_FavouritePlace=true;
  	          for(FavouritePlace fp:all_favouritePlace){
  	        	  if(fp.getUserID()==fPlace.getUserID() && fp.getPointID()==fPlace.getPointID()) {
  					no_same_FavouritePlace=false;
  					break;
  				}
  	          }
  	          if (no_same_FavouritePlace) {
  	        	  File csv = new File("FavouritePlaces.csv"); // CSV address
  	      	    
  		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.

  		          bw.write("\n"); 
  		          bw.write(fPlace.getUserID() + "," + fPlace.getPointID() + ","+ fPlace.getNotes());
  		          
  		          bw.close(); 
  			}else {
  				System.out.println("the place has been saved!");
  			}
  	    
  	        } catch (FileNotFoundException e) { 
  	       
  	          e.printStackTrace(); 
  	        } catch (IOException e) { 
  	         
  	          e.printStackTrace(); 
  	        } 
      }
    public boolean DeleteFavouritePlace(int userID,int pointID){
      	boolean result=false;
      	ArrayList<FavouritePlace> all_favouritePlaces=new ArrayList<>(this.ReadFavouritePlace());
      	for(int i=0; i<all_favouritePlaces.size();i++){
      		if (all_favouritePlaces.get(i).getUserID()==userID && all_favouritePlaces.get(i).getPointID()==pointID) {
      			all_favouritePlaces.remove(i);      		
      		   result=true;
      		   break;
      		} 
      	}
      	if (result) {
      		try{
              	
              	File csv = new File("FavouritePlaces.csv"); // CSV address
           	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
                  bw.write("UserID"+","+"PointID"+","+"Notes");
      	          
      	          for(FavouritePlace fPlace:all_favouritePlaces){
      	        	  bw.write("\n"); 
      	        	  bw.write(fPlace.getUserID()+ "," + fPlace.getPointID() + ","+ fPlace.getNotes() );
      	        	 
      	          }
      	          bw.close(); 
      		
              	}catch (FileNotFoundException e) { 
           	       
        	          e.printStackTrace(); 
        	        } catch (IOException e) { 
        	         
        	          e.printStackTrace(); 
        	        } 
		  }	
      		return result;
	}
      		
           
        	       
  	}


