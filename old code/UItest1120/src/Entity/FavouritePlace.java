package Entity;

public class FavouritePlace {
      private int userID;
      private int pointID;
      private String notes;
	
    public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPointID() {
		return pointID;
	}
	public void setPointID(int pointID) {
		this.pointID = pointID;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
