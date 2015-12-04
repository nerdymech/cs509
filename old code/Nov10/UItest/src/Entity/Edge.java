package Entity;

public class Edge {
  private int startPointID;
  private int endPointID;
  private int weight;

  public void set_weight_from_point(Point s, Point e) {
	  int x = (int)(Math.pow(s.getxCooridinate()-e.getxCooridinate(),2));
	  int y = (int)(Math.pow(s.getxCooridinate()-e.getxCooridinate(),2));
	  weight = (int)(Math.sqrt(x+y));
	  //System.out.println("The weight is " + (s.getxCooridinate()-e.getxCooridinate()));
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
  
  
  
}
