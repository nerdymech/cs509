package GUI;

import GUI2.testPassedArg;
import Algorithm.FindPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.*;

class testPass {
	void printArgPassed(String stB, String stL, String enB, String enL) {
		System.out.println(stB + stL + enB + enL);
	}
}

public class MapPageGUI {

	protected Shell shlSwtApplication;
	
	public String stLoc;
	public String stBuilding;
	
	public String enBuilding;
	public String enLoc;
	
	testPassedArg tp = new testPassedArg();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MapPageGUI window = new MapPageGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSwtApplication.open();
		shlSwtApplication.layout();
		
		Label banner = new Label(shlSwtApplication, SWT.NONE);
		banner.setText("Welcome to WPI!");
		banner.setBounds(0, 0, 435, 95);
		banner.setImage(new Image(display,"wpiBanner.jpg"));
		
		while (!shlSwtApplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSwtApplication = new Shell();
		shlSwtApplication.setSize(451, 559);
		shlSwtApplication.setText("WPI Navigator");
				
		Combo comboBuilding1 = new Combo(shlSwtApplication, SWT.NONE);
		comboBuilding1.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		comboBuilding1.setBounds(42, 271, 158, 33);
		
		Label lblSelectLocation1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation1.setBounds(233, 236, 158, 29);
		lblSelectLocation1.setText("Select Location");
					
		Combo comboLocation1 = new Combo(shlSwtApplication, SWT.NONE);
		comboLocation1.setBounds(233, 271, 158, 33);
		
		Combo comboBuilding2 = new Combo(shlSwtApplication, SWT.NONE);
		comboBuilding2.setBounds(42, 401, 158, 33);
		comboBuilding2.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		
		Label lblSelectLocation2 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation2.setBounds(233, 366, 158, 29);
		lblSelectLocation2.setText("Select Location");
					
		Combo comboLocation2 = new Combo(shlSwtApplication, SWT.NONE);
		comboLocation2.setBounds(233, 401, 158, 33);
		
		//comboBuilding1.addModifyListener(listener);
		
		comboBuilding1.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		    	  //System.out.println(comboBuilding1.getText());        
		    	  String startBuilding = comboBuilding1.getText();
		    	  stBuilding = startBuilding;
		    	  //System.out.println(stBuilding);
		    	  
		    	  String startFileName = startBuilding + ".csv";
		    	  //System.out.println(startBuilding);
				
		    	  FileReader startFileReader = null;
		    	  String line = null;
		    	  String locations[] = new String[10];
		    	  int i = 0;
				
		    	  try {
		    		  //System.out.println("entered start try");
		    		  startFileReader = new FileReader(startFileName);
		    		  BufferedReader bufferReader = new BufferedReader(startFileReader);
		    		  i = 0;
					
		    		  while((line = bufferReader.readLine()) != null) {
		    			  //System.out.println(line);
		    			  locations[i] = line.split(",")[7];
		    			  //System.out.println(locations[i]);
						
		    			  i++;
		    		  }
		    		  
		    		  bufferReader.close();
					
		    		  int numLocations = i;
		    		  String[] finalLocations = new String[numLocations];
					
		    		  for (int x = 0; x < numLocations; x++) {
		    			  finalLocations[x] = locations[x];
		    			  //System.out.println(finalLocations[x]);
		    		  }
						
		    		  comboLocation1.setItems(finalLocations);
		    		  
		    		  comboLocation1.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							stLoc = comboLocation1.getText();
							//System.out.println(stLoc);
						}
		    			  
		    		  });

		    		  
		    		  
		    		  
		    			    		  
		    	  } catch (IOException f) {
		    		  // TODO Auto-generated catch block
		    		  f.printStackTrace();
		    	  } finally {
		    		  if(startFileReader != null)
		    			  try {
		    				  startFileReader.close();
		    			  } catch (IOException g) {
		    				  // TODO Auto-generated catch block
		    				  g.printStackTrace();
		    			  }
		    	  }
		    	  //System.out.println(startFileName);				
		      }
		      
		      public void widgetDefaultSelected(SelectionEvent e) {
		    	  System.out.println(comboBuilding1.getText());
		    	  /*building = comboBuilding1.getText();
					fileName = building + ".txt";
				
					System.out.println(fileName);*/
		      }
		});
		
		comboBuilding2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				//System.out.println(comboBuilding1.getText());
		    	String endBuilding = comboBuilding2.getText();
		    	
		    	enBuilding = endBuilding;
		    	
		  		String endFileName = endBuilding + ".csv";
		  		//System.out.println(endFileName);
				
				FileReader endFileReader = null;
				String line = null;
				String locations[] = new String[10];
				//int entered = 1;
				int i = 0;
				
				try {
					endFileReader = new FileReader(endFileName);
					BufferedReader bufferReader = new BufferedReader(endFileReader);
					i = 0;
					
					while ((line = bufferReader.readLine()) != null) {
						//System.out.println(line);
						locations[i] = line.split(",")[7];
						//System.out.println(locations[i]);
						
						i++;
					}
					
					bufferReader.close();
					
					int numLocations = i;
					String[] finalLocations = new String[numLocations];
					
					for (int x = 0; x < numLocations; x++)
						finalLocations[x] = locations[x];
					
					comboLocation2.setItems(finalLocations);
					
					comboLocation2.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							enLoc = comboLocation2.getText();
						}
						
					});
					
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				} finally {
					if(endFileReader != null)
						try {
							endFileReader.close();
						} catch (IOException g) {
							// TODO Auto-generated catch block
							g.printStackTrace();
						}
				}
				//System.out.println(startFileName);
			}
		      
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println(comboBuilding1.getText());
		        /*building = comboBuilding1.getText();
				fileName = building + ".txt";
				
				System.out.println(fileName);*/
			}
		});
      
		Label lblSelectBuilding1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding1.setBounds(42, 236, 158, 29);
		lblSelectBuilding1.setText("Select Building");
				
		Label lblSelectStartLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectStartLocation.setBounds(130, 189, 190, 33);
		lblSelectStartLocation.setText("Select Start Location");
		
		Label lblSelectEndLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectEndLocation.setBounds(130, 328, 190, 25);
		lblSelectEndLocation.setText("Select End Location");
		
		Label lblSelectBuilding2 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding2.setBounds(42, 366, 158, 29);
		lblSelectBuilding2.setText("Select Building");
		
		Button btnGuide = new Button(shlSwtApplication, SWT.NONE);
		btnGuide.setBounds(164, 457, 105, 35);
		btnGuide.setText("Guide");
		
		btnGuide.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				//tp.printArgPassed(stBuilding, stLoc, enBuilding, enLoc);
				String nothing[] = {stBuilding, stLoc, enBuilding, enLoc};
				//testPassedArg.main(nothing);
				FindPath.main(nothing);
			}
		});
		
		Label lblPleaseSelectA = new Label(shlSwtApplication, SWT.NONE);
		lblPleaseSelectA.setBounds(84, 134, 278, 25);
		lblPleaseSelectA.setText("Please select a building before looking for a location.");
		
		//trial.addItemListener(new ItemListener() {
		//});
	}
}