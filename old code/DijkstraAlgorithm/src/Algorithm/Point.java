package Algorithm;

public class Point {
	private int pointID;
	private String buildName;
	private int floorNum;
	private int attributes;
	private int entranceID;
	private int xCooridinate;
    private int yCooridinate;
    private String feature;
    private int roomNum;
	
    
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
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (Integer.toString(pointID) == null) {
            if (Integer.toString(other.pointID) != null)
                return false;
        } else if (!Integer.toString(pointID).equals(other.pointID))
            return false;
        return true;
    }
}
