package Entity;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private int userID;
    private ArrayList<FavouritePlace> favouritePlaces;

 public String getUserName() {
	return userName;
}
 public void setUserName(String userName) {
	this.userName = userName;
}
 public String getPassword() {
	return password;
}
 public void setPassword(String password) {
	this.password = password;
}
 public int getUserID() {
	return userID;
}
 public void setUserID(int userID) {
	this.userID = userID;
}
public ArrayList<FavouritePlace> getFavouritePlaces() {
	return favouritePlaces;
}
public void setFavouritePlaces(ArrayList<FavouritePlace> favouritePlaces) {
	this.favouritePlaces = favouritePlaces;
}

}
