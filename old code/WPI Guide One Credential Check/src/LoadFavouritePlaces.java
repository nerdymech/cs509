import java.io.*;
import java.util.ArrayList;

public class LoadFavouritePlaces {

	public static void main(String args) {
		// Instantiate a new object to call functions.
		LoadFavouritePlaces places = new LoadFavouritePlaces();
		
		// Store the userID into a variable and create a new list to store the list of favorite locations
		String passedUserID = args;
		ArrayList<String> list = new ArrayList<String>();
		
		// Call the "load" method, passing in the passedUserID
		list = places.load(passedUserID);
		
		/*if (list.isEmpty())
			System.out.println("There are no favorite places associated to this user.");
		else
			// Print out the list
			System.out.println(list);*/
	}
	
	// Purpose: Pass to the next function the ArrayList containing the favorite locations
	public ArrayList<String> load(String passedUserID)  {
		
		// Variables to for reading files and to temporarily store data
		FileReader fr;
		BufferedReader br;
		@SuppressWarnings("unused")
		String rowUser = null, userID = null, pointID = null, notes = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			// Create a new FileReader and a new BufferReader
			fr = new FileReader("FavouritePlaces.csv");
			br = new BufferedReader(fr);
			
			// Loop through the CSV file while it is not the end of the file
			while ((rowUser = br.readLine()) != null) {
				
				// Parse through the file and save the data into a variable
				userID = rowUser.split(",")[0];
				pointID = rowUser.split(",")[1];
				notes = rowUser.split(",")[2];
				
				// If the userID is equal to the passedUserID, add it to the ArrayList 
				if (userID.equals(passedUserID))
					list.add(notes);
			}
			
			// Close the readers
			br.close();
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (list.isEmpty())
			System.out.println("There are no favorite places associated to this user.");
		else
			// Print out the list
			System.out.println(list);
		
		return list;
	}
}
