package Entity;

public class BuildingEdge {
	 private int startBuildingID;
	private int endBuildingID;
	 private int weight;

	  public void set_weight_from_building(Building s, Building e) {
		  int x = (int)(Math.pow(s.getxCooridinate()-e.getxCooridinate(),2));
		  int y = (int)(Math.pow(s.getxCooridinate()-e.getxCooridinate(),2));
		  weight = (int)(Math.sqrt(x+y));
	  }
	  
	  public int getStartBuildingID() {
			return startBuildingID;
		}


		public void setStartBuildingID(int startBuildingID) {
			this.startBuildingID = startBuildingID;
		}


		public int getEndBuildingID() {
			return endBuildingID;
		}


		public void setEndBuildingID(int endBuildingID) {
			this.endBuildingID = endBuildingID;
		}
	  public int getWeight() {
		return weight;
	}
	  public void setWeight(int weight) {
		this.weight = weight;
	}
}
