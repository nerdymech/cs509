package Entity;
public class Point {
	
	private int pointID;
	private String buildName;
	private int floorNum;
	private int attributes;
	private int entranceID;
	public int xCooridinate;
    public int yCooridinate;
    private String feature;
    private int roomNum;
	
    public Point(int x, int y) {
    	xCooridinate = x;
    	yCooridinate = y;
    }
    
    public int getPointID() {
		return pointID;
	}
	public void setPointID(int pointID) {
		this.pointID = pointID;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public int getAttributes() {
		return attributes;
	}
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	public int getEntranceID() {
		return entranceID;
	}
	public void setEntranceID(int entranceID) {
		this.entranceID = entranceID;
	}
	public int getxCooridinate() {
		return xCooridinate;
	}
	public void setxCooridinate(int xCooridinate) {
		this.xCooridinate = xCooridinate;
	}
	public int getyCooridinate() {
		return yCooridinate;
	}
	public void setyCooridinate(int yCooridinate) {
		this.yCooridinate = yCooridinate;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
    

}
