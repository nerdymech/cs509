package Algorithm;

public class Edge {
  private int id; 
  private int startPointID;
  private int endPointID;
  private int weight;
//  private Point startPoint; 
//  private Point endPoint; 

  public Edge(int id, int startPointID, int endPointID, int weight) {
      this.id = id;
      this.startPointID = startPointID;
      this.endPointID = endPointID;
      this.weight = weight;
//      this.startPoint = startPoint;
//      this.endPoint = startPoint; 
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
  
  public Integer getId() {
    return id;
}
  @Override
  public String toString() {
      return Integer.toString(startPointID) + " " + Integer.toString(endPointID);
  }
  
//public Point getStartPoint() {
//	// TODO Auto-generated method stub
//	return null;
//}

//public Point getDestination() {
//	// TODO Auto-generated method stub
//	return ;
//}
  
}
