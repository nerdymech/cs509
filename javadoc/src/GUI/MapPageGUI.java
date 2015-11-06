package GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class MapPageGUI {

	protected Shell shlSwtApplication;

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
		shlSwtApplication.setText("SWT Application");
		
		Combo comboBuilding1 = new Combo(shlSwtApplication, SWT.NONE);
		comboBuilding1.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		comboBuilding1.setBounds(42, 257, 158, 33);
		
		Label lblSelectLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation.setBounds(222, 226, 80, 15);
		lblSelectLocation.setText("Select Location");
					
		Combo comboLocation1 = new Combo(shlSwtApplication, SWT.NONE);
		comboLocation1.setBounds(222, 257, 158, 33);
		
		Combo comboBuilding2 = new Combo(shlSwtApplication, SWT.NONE);
		comboBuilding2.setBounds(42, 386, 158, 33);
		comboBuilding2.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		
		Label lblSelectLocation_1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation_1.setBounds(222, 355, 80, 15);
		lblSelectLocation_1.setText("Select Location");
					
		Combo comboLocation2 = new Combo(shlSwtApplication, SWT.NONE);
		comboLocation2.setBounds(222, 386, 158, 33);
		
		//comboBuilding1.addModifyListener(listener);
		
		comboBuilding1.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		    	  //System.out.println(comboBuilding1.getText());        
		    	  String startBuilding = comboBuilding1.getText();
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
					
		    		  int numLocations = i;
		    		  String[] finalLocations = new String[numLocations];
					
		    		  for (int x = 0; x < numLocations; x++) {
		    			  finalLocations[x] = locations[x];
		    			  //System.out.println(finalLocations[x]);
		    		  }
						
		    		  comboLocation1.setItems(finalLocations);

		    		  bufferReader.close();
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
      
		Label lblSelectBuilding = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding.setBounds(42, 226, 78, 15);
		lblSelectBuilding.setText("Select Building");
				
		Label lblSelectStartLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectStartLocation.setBounds(118, 195, 107, 15);
		lblSelectStartLocation.setText("Select Start Location");
		
		Label lblSelectEndLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectEndLocation.setBounds(118, 324, 105, 15);
		lblSelectEndLocation.setText("Select End Location");
		
		Label lblSelectBuilding_1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding_1.setBounds(42, 355, 80, 15);
		lblSelectBuilding_1.setText("Select Building");
		
		Button btnGuide = new Button(shlSwtApplication, SWT.NONE);
		btnGuide.setBounds(143, 458, 105, 35);
		btnGuide.setText("Guide");
		
		Label lblPleaseSelectA = new Label(shlSwtApplication, SWT.NONE);
		lblPleaseSelectA.setBounds(10, 145, 278, 15);
		lblPleaseSelectA.setText("Please select a building before looking for a location.");
		
		//trial.addItemListener(new ItemListener() {
		//});
	}
}