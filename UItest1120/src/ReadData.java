import java.util.ArrayList;

import Controller.DatabaseMethods;
import Entity.Edge;
import Entity.Point;


public class ReadData {
	public ArrayList pointToVertex(){
    	DatabaseMethods DB= new DatabaseMethods();
		ArrayList<Point> all_points= new ArrayList<>(DB.ReadPoints());
    	ArrayList<Vertex> all_vertexs= new ArrayList();
    	for(Point p:all_points){
    		Vertex v=new Vertex();
    		v.setId(Integer.toString(p.getPointID()));
    		v.setName(p.getBuildName());
    		all_vertexs.add(v);
    		
    	}
//    	for(Vertex v:all_vertexs){
//    		System.out.println(v.getId());
//    	}
    	return all_vertexs;
    }
	
	public ArrayList edgeToEdges(){
    	DatabaseMethods DB= new DatabaseMethods();
		ArrayList<Edge> all_edge= new ArrayList<>(DB.ReadEdges());
		ArrayList<Point> all_points= new ArrayList<>(DB.ReadPoints());
    	ArrayList<Edges> all_edges= new ArrayList();
    	for(Edge e: all_edge){
    		Edges edges=new Edges();
    		Vertex startVertex=new Vertex();
    		Vertex endVertex=new Vertex();
    		for(Point p:all_points){
    			if (p.getPointID()==e.getStartPointID()) {
					startVertex.setId(Integer.toString(p.getPointID()));
					startVertex.setName(p.getBuildName());
				}else if (p.getPointID()==e.getEndPointID()) {
					endVertex.setId(Integer.toString(p.getPointID()));
					endVertex.setName(p.getBuildName());
				}
    		//edges.setId(Integer.toString(e.getId()));
    		edges.setWeight(e.getWeight());
    		edges.setStart(startVertex);
    		edges.setDestination(endVertex);
    		
    		}
    		all_edges.add(edges);
    	}
//    	for(Edges e:all_edges){
//    		System.out.println(e.getStart().getId());
//    	}
    	return all_edges;
    }

}
