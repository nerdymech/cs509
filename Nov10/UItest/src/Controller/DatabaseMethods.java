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

import Entity.Building;
import Entity.Edge;
import Entity.Point;

public class DatabaseMethods {
	public int index = 10;
	
	public Point GetPointById(int id) {
		ArrayList<Point> all_points = new ArrayList<Point>();
		all_points = ReadPoints();
		for(Point p:all_points) {
			if(p.getPointID() == id)
				return p;
		}
		return null;
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
            File csv = new File("Higgins.csv"); // CSV file address

            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            // Read until last line 
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                // split every line
                StringTokenizer st = new StringTokenizer(line, ",");
                Point point=new Point(0,0); 
                point.setPointID(Integer.parseInt(st.nextToken())); 
                point.setxCooridinate(Integer.parseInt(st.nextToken()));
                point.setyCooridinate(Integer.parseInt(st.nextToken()));
                point.setBuildName(st.nextToken());
                point.setFloorNum(Integer.parseInt(st.nextToken()));
                point.setAttributes(Integer.parseInt(st.nextToken()));
                point.setEntranceID(Integer.parseInt(st.nextToken()));
                point.setRoomNum(Integer.parseInt(st.nextToken()));
                point.setFeature(st.nextToken());
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
	        	  File csv = new File("Higgins.csv"); // CSV address
	      	    
		          BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.

		          // add a new line
		           
		          bw.write(point.getPointID() + "," + point.getxCooridinate() + ","+ point.getyCooridinate() + ","+ point.getBuildName() + ","+ point.getFloorNum() + ","+ point.getAttributes() + ","+ point.getEntranceID() + ","+ point.getRoomNum() + ","+ point.getFeature() + "\n");
		          
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

		          // add a new line
		          bw.newLine(); 
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

//		          // add a new line
//		          bw.newLine(); 
		          bw.write(edge.getStartPointID() + "," + edge.getEndPointID() + ","+ edge.getWeight() + "\n");
		          
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
        	
        	File csv = new File("Higgins.csv"); // CSV address
     	    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // append - boolean if true, then data will be written to the end of the file rather than the beginning.
            bw.write("Point_ID"+","+"X"+","+"Y"+","+"Building"+","+"Floor"+","+"Attributes"+","+"Entrance ID"+","+"Room number"+","+"Feature name");
	          // add a new line
	          bw.newLine(); 
	          
	          for(Point p:all_points){
	        	  bw.write(p.getPointID() + "," + p.getxCooridinate() + ","+ p.getyCooridinate() + ","+ p.getBuildName() + ","+ p.getFloorNum() + ","+ p.getAttributes() + ","+ p.getEntranceID() + ","+ p.getRoomNum() + ","+ p.getFeature() + "\n");
	        	  
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
	          bw.newLine(); 
	          
	          for(Building b:all_buildings){
	        	  bw.write(b.getBuildingID()+ "," + b.getName() + ","+ b.getxCooridinate() + ","+ b.getyCooridinate() + ","+ b.getNumberOfFloors());
	        	  bw.newLine();
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
	          bw.newLine(); 
	          
	          for(Edge e:all_edges){
	        	  bw.write(e.getStartPointID()+ "," + e.getEndPointID() + ","+ e.getWeight() );
	        	  bw.newLine();
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
            
  }

