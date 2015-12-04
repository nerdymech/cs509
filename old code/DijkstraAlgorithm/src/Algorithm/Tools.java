package Algorithm;

import java.util.ArrayList;

import Database.src.DatabaseMethods.DatabaseMethods;

public class Tools {
	public Point GetStartPoint(Edge edge){
    	DatabaseMethods DB= new DatabaseMethods();
		ArrayList<Point> all_points= new ArrayList<>(DB.ReadPoints());
    	Point point=new Point();
    	for(Point p:all_points){
    		if(p.getPointID()==edge.getStartPointID()){
    			point=p;
    			break;
    		}
    	}
    	return point;
    }
    public Point GetEndPoint(Edge edge){
    	DatabaseMethods DB= new DatabaseMethods();
    	ArrayList<Point> all_points= new ArrayList<>(DB.ReadPoints());
    	Point point=new Point();
    	for(Point p:all_points){
    		if(p.getPointID()==edge.getEndPointID()){
    			point=p;
    			break;
    		}
    	}
    	return point;
    }
}
