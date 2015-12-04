package com.bangkura;

import java.util.ArrayList;

import com.bangkura.Entity.Building;
import com.bangkura.Entity.BuildingEdge;



public class ConvertBuilding {
	public ArrayList buildingToVertex(){
		DatabaseMethods db = new DatabaseMethods();
		ArrayList<Building> all_building= new ArrayList<>(db.ReadBuildings());
    	ArrayList<Vertex> all_vertexs= new ArrayList();
    	for(Building b:all_building){
    		Vertex v=new Vertex();
    		v.setId(Integer.toString(b.getBuildingID()));
    		v.setName(b.getName());
    		all_vertexs.add(v);
    		
    	}
//    	for(Vertex v:all_vertexs){
//    		System.out.println(v.getId());
//    	}
    	return all_vertexs;
    }
	
	public ArrayList buildingEdgeToEdges(){
    	DatabaseMethods db= new DatabaseMethods();
		ArrayList<BuildingEdge> all_buildingEdge= new ArrayList<>(db.ReadBuildingEdges());
		ArrayList<Building> all_buildingPoints= new ArrayList<>(db.ReadBuildings());
    	ArrayList<Edges> all_edges= new ArrayList();
    	for(BuildingEdge e: all_buildingEdge){
    		Edges buildingEdges=new Edges();
    		Vertex startVertex=new Vertex();
    		Vertex endVertex=new Vertex();
    		for(Building p:all_buildingPoints){
    			if (p.getBuildingID()==e.getStartBuildingID()) {
					startVertex.setId(Integer.toString(p.getBuildingID()));
					startVertex.setName(p.getName());
				}else if (p.getBuildingID()==e.getEndBuildingID()) {
					endVertex.setId(Integer.toString(p.getBuildingID()));
					endVertex.setName(p.getName());
				}
    		//edges.setId(Integer.toString(e.getId()));
    		buildingEdges.setWeight(e.getWeight());
    		buildingEdges.setStart(startVertex);
    		buildingEdges.setDestination(endVertex);
    		
    		}
    		all_edges.add(buildingEdges);
    	}
//    	for(Edges e:all_edges){
//    		System.out.println(e.getStart().getId());
//    	}
    	return all_edges;
    }

}
