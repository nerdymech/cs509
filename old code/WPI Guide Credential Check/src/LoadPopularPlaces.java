import java.io.*;

public class LoadPopularPlaces {
	
	//create method to read popular places file
	public void readPopularPlaces()
	{
		//init the string variables that will hold the values from the csv
		String line = null;
		String givenPointID = null;
		String givenName = null;
		String[] popularPlaces = new String[10];
		
		//popularPlaces[0] = "test";
		
		System.out.println(popularPlaces[0]);
		
		try{
		
		FileReader popularPlacesFileReader = null;
		//file that will be read by FileReader
		popularPlacesFileReader = new FileReader("PopularPlaces.csv");
		BufferedReader bufferReader = new BufferedReader(popularPlacesFileReader);
		int i = 0;
		
			while((line = bufferReader.readLine()) != null)  {

				//System.out.println(line.split(","));
				
				  givenPointID = line.split(",")[0];
				  givenName = line.split(",")[1]; 
				  //Print out the given name
				  //System.out.println(givenPointID + " " + givenName);
				  //store into an array
				 // System.out.println(popularPlaces[0]);
				  //System.out.println(i);
				  //System.out.println("Given Name: " + givenName);
				  
				  
				  //this is the array of places
				  popularPlaces[i] = givenName;
				  System.out.println("Assigned value: " + popularPlaces[i]);
				  i++;
				}
			bufferReader.close();
			popularPlacesFileReader.close();
			//return popularPlaces;
		} catch (IOException e){
			e.printStackTrace();
		}
		
			}
		
		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoadPopularPlaces loadPopularPlaces = new LoadPopularPlaces();
		loadPopularPlaces.readPopularPlaces();

	}

}
