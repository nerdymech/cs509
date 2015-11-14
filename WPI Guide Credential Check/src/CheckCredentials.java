import java.io.*;

public class CheckCredentials {
	
	public int Check(String userName, String passWord, String type)
	{
		String line = null;
		String actualUserName = null;
		String actualPassWord = null; 
		
		if(type.equalsIgnoreCase("admin"))
		{
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
	    				return 1;
	    			}
	    			else
	    			{
	    				System.out.println("Wrong Password. Please enter correct password");
	    				return 0;
	    			}
	    		}
	    		else
	    		{
	    			System.out.println("Wrong Username. Please correct the username");
	    			return 0;
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
		}
	    		  
		if(type.equalsIgnoreCase("user"))
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
	    			  
	    			  if(actualUserName.equalsIgnoreCase(userName))
	    				  break;
	    			  
	    			  			
	    		}
	    		
	    		bufferReader.close();
	    		
	    		if(actualUserName.equalsIgnoreCase(userName))
	    		{
	    			if(actualPassWord.equals(passWord))
	    			{
	    				System.out.println("Credentials Match");
	    				return 1;
	    			}
	    			else
	    			{
	    				System.out.println("Wrong Password. Please enter correct password");
	    				return 0;
	    			}
	    		}
	    		else
	    		{
	    			System.out.println("Wrong Username. Please correct the username");
	    			return 0;
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
		System.out.println("user/admin should be passed as the third argument");
		return 0;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CheckCredentials chk = new CheckCredentials();
		
		String userName = args[0];
		String passWord = args[1];
		
		chk.Check(userName, passWord, args[2]);
		
		
	}
		

	}


