import java.io.*;

public class CheckCredentials {
	
	private int checkStatus;
	
	public void Check(String userName, String passWord)
	{
		String line = null;
		String actualUserName = null;
		String actualPassWord = null; 
		String actualUserID = null;
		LoadFavouritePlaces FavPlaces;
		
			FileReader adminFileReader = null;
			try {
	    		  //System.out.println("entered start try");
				adminFileReader = new FileReader("admin.csv");
	    		BufferedReader bufferReader = new BufferedReader(adminFileReader);
	    		
	    		while((line = bufferReader.readLine()) != null)  {
	    			  
	    			System.out.println(line);
	    			
	    			  actualUserName = line.split(",")[0];
	    			  actualPassWord = line.split(",")[1];
	    			  	    			  
	    			  if(actualUserName.equalsIgnoreCase(userName))
	    				  break;
	    			  
	    			  			
	    		}
	    		
	    		bufferReader.close();
	    		
	    		if(actualUserName.equalsIgnoreCase(userName))
	    		{
	    			if(actualPassWord.equals(passWord))
	    			{
	    				System.out.println("Credentials Match");
	    				checkStatus = 1;
	    			}
	    			else
	    			{
	    				System.out.println("Wrong Password. Please enter correct password");
	    				checkStatus = 0;
	    			}
	    		}
	    		else
	    		{
	    			System.out.println("Wrong Username. Please correct the username");
	    			checkStatus = 0;
	    		}
	    		
			}  catch (IOException f) {
	    		  // TODO Auto-generated catch block
	    		  f.printStackTrace();
	    	  } finally {
	    		  if(adminFileReader != null)
	    			  try {
	    				  adminFileReader.close();
	    			  } catch (IOException g) {
	    				  // TODO Auto-generated catch block
	    				  g.printStackTrace();
	    			  }
	    		  
	    	  }
		
	    		  
		if(checkStatus == 0)
		{
			FileReader userFileReader = null;
			try {
	    		  //System.out.println("entered start try");
				userFileReader = new FileReader("user.csv");
	    		BufferedReader bufferReader = new BufferedReader(userFileReader);
	    		
	    		while((line = bufferReader.readLine()) != null)  {
	    			  
	    			System.out.println(line);
	    			
	    			  actualUserName = line.split(",")[0];
	    			  actualPassWord = line.split(",")[1];
	    			  actualUserID = line.split(",")[2];
	    			  
	    			  if(actualUserName.equalsIgnoreCase(userName))
	    				  break;
	    			  
	    			  			
	    		}
	    		
	    		bufferReader.close();
	    		
	    		if(actualUserName.equalsIgnoreCase(userName))
	    		{
	    			if(actualPassWord.equals(passWord))
	    			{
	    				System.out.println("Credentials Match");
	    				checkStatus = 1;
	    				FavPlaces = new LoadFavouritePlaces();
	    				//LoadFavouritePlaces.main(actualUserID);
	    				FavPlaces.load(actualUserID);
	    			}
	    			else
	    			{
	    				System.out.println("Wrong Password. Please enter correct password");
	    				checkStatus = 0;
	    			}
	    		}
	    		else
	    		{
	    			System.out.println("Wrong Username. Please correct the username");
	    			checkStatus = 0;
	    		}
	    		
			}  catch (IOException f) {
	    		  // TODO Auto-generated catch block
	    		  f.printStackTrace();
	    	  } finally {
	    		  if(userFileReader != null)
	    			  try {
	    				  userFileReader.close();
	    			  } catch (IOException g) {
	    				  // TODO Auto-generated catch block
	    				  g.printStackTrace();
	    			  }
	    		  
	    	  }
		}
		//System.out.println("user/admin should be passed as the third argument");
		//return 0;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckCredentials test = new CheckCredentials(); 
		test.Check("user2", "1234");
	}

}
