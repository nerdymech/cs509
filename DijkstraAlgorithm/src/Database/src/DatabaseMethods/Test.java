package Database.src.DatabaseMethods;

import java.util.ArrayList;

import Database.src.Entity.*;

public class Test {
	public static void main(String[] args) {
		DatabaseMethods d=new DatabaseMethods();
		System.out.println(d.ReadPoints().get(4).getPointID());
		System.out.println(d.ReadPoints().get(4).getxCooridinate());
		System.out.println(d.ReadPoints().get(4).getyCooridinate());
		System.out.println(d.ReadPoints().get(4).getBuildName());
		System.out.println(d.ReadPoints().get(4).getFloorNum());
		System.out.println(d.ReadPoints().get(4).getAttributes());
		System.out.println(d.ReadPoints().get(4).getEntranceID());
		System.out.println(d.ReadPoints().get(4).getFeature());
		System.out.println(d.ReadPoints().get(4).getRoomNum());
		
		Point p=new Point();
		p.setPointID(10);
		p.setxCooridinate(100);
		p.setyCooridinate(100);
		p.setBuildName("test");
		p.setFloorNum(10);
		p.setAttributes(1);
		p.setEntranceID(0);
		p.setRoomNum(100);
		p.setFeature("bbb");
		d.SavePoint(p);
		
		ArrayList<Building> b=new ArrayList<>(d.ReadBuildings());
		for(int i=0;i<10;i++){
			System.out.println(b.get(i).getBuildingID() + " " + b.get(i).getName());
		}
		ArrayList<Edge> e=new ArrayList<>(d.ReadEdges());
		for(int i=0;i<9;i++){
			System.out.println(e.get(i).getStartPointID() + " " + e.get(i).getEndPointID() + " " +e.get(i).getWeight());
		}
		Building bu = new Building();
		bu.setBuildingID(99);
		bu.setName("ren");
		bu.setxCooridinate(99);
		bu.setyCooridinate(99);
		bu.setNumberOfFloors(88);
		d.SaveBuilding(bu);
		
		Edge ed = new Edge();
		ed.setStartPointID(16);
		ed.setEndPointID(99);
		ed.setWeight(66);
		d.SaveEdge(ed);
	}
}
