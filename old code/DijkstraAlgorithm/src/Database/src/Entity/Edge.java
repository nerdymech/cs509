package Database.src.Entity;

import java.util.ArrayList;

import Algorithm.Point;

public class Edge {
  private int id; 
  private int startPointID;
  private int endPointID;
  private int weight;
  private Point startPoint;
  private point startPoint; 

  public Edge(int id, int startPointID, int endPointID, int weight) {
      this.id = id;
      this.startPointID = startPointID;
      this.endPointID = endPointID;
      this.weight = weight;
  }
  
  public int getStartPointID() {
	return startPointID;
}
  public void setStartPointID(int startPointID) {
	this.startPointID = startPointID;
}
  public int getEndPointID() {
	return endPointID;
}
  public void setEndPointID(int endPointID) {
	this.endPointID = endPointID;
}
  public int getWeight() {
	return weight;
}
  public void setWeight(int weight) {
	this.weight = weight;
}
  public Point getDestination() {
	// TODO Auto-generated method stub
	return null;
}
  public Point getStart() {
	// TODO Auto-generated method stub
	return null;
}
  public Integer getId() {
    return id;
}
  @Override
  public String toString() {
      return Integer.toString(startPointID) + " " + Integer.toString(endPointID);
  }
}
