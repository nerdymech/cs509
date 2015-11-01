import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class DropDown {

	protected Shell shlSwtApplication;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DropDown window = new DropDown();
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
		
		Combo combo = new Combo(shlSwtApplication, SWT.NONE);
		combo.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		combo.setBounds(42, 257, 158, 33);
		
		Label lblSelectLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation.setBounds(222, 226, 158, 25);
		lblSelectLocation.setText("Select Location");
					
		Combo combo_1 = new Combo(shlSwtApplication, SWT.NONE);
		
		combo_1.setBounds(222, 257, 158, 33);
		
		Combo combo_2 = new Combo(shlSwtApplication, SWT.NONE);
		combo_2.setBounds(42, 386, 158, 33);
		combo_2.setItems(new String[] {"tmp", "Higgins", "Campus Centre", "Project Centre"});
		
		Label lblSelectLocation_1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectLocation_1.setBounds(222, 355, 158, 25);
		lblSelectLocation_1.setText("Select Location");
					
		Combo combo_3 = new Combo(shlSwtApplication, SWT.NONE);
		
		combo_3.setBounds(222, 386, 158, 33);
		
		//combo.addModifyListener(listener);
		
		combo.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		        //System.out.println(combo.getText());
		        
		        String startBuilding = combo.getText();
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
					
					while((line = bufferReader.readLine()) != null){
						//System.out.println(line);
						
						locations[i] = line.split(",")[7];
						
						//System.out.println(locations[i]);
						
						i++;
					}
					
					int numLocations = i;
					
					String[] finalLocations = new String[numLocations];
					
					for(int x= 0; x<numLocations; x++){
						finalLocations[x] = locations[x];
						//System.out.println(finalLocations[x]);
					}
						
					combo_1.setItems(finalLocations);

					
					
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
		        System.out.println(combo.getText());
		        

		        /*building = combo.getText();
				fileName = building + ".txt";
				
				System.out.println(fileName);*/
		      }
		    });
		
		
		combo_2.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		        //System.out.println(combo.getText());
		        
		    	String endBuilding = combo_2.getText();
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
					
					while((line = bufferReader.readLine()) != null){
						//System.out.println(line);
						
						locations[i] = line.split(",")[7];
						
						//System.out.println(locations[i]);
						
						i++;
					}
					
					bufferReader.close();
					
					int numLocations = i;
					
					String[] finalLocations = new String[numLocations];
					
					for(int x= 0; x<numLocations; x++)
						finalLocations[x] = locations[x];
					
					combo_3.setItems(finalLocations);
					
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
		        System.out.println(combo.getText());
		        

		        /*building = combo.getText();
				fileName = building + ".txt";
				
				System.out.println(fileName);*/
		      }
		    });
      


		
		
		Label lblSelectBuilding = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding.setBounds(42, 226, 158, 25);
		lblSelectBuilding.setText("Select Building");
				
		Label lblSelectStartLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectStartLocation.setBounds(118, 195, 158, 25);
		lblSelectStartLocation.setText("Select Start Location");
		
		Label lblSelectEndLocation = new Label(shlSwtApplication, SWT.NONE);
		lblSelectEndLocation.setBounds(118, 324, 158, 25);
		lblSelectEndLocation.setText("Select End Location");
		
		Label lblSelectBuilding_1 = new Label(shlSwtApplication, SWT.NONE);
		lblSelectBuilding_1.setBounds(42, 355, 158, 25);
		lblSelectBuilding_1.setText("Select Building");
		
		Button btnGuide = new Button(shlSwtApplication, SWT.NONE);
		btnGuide.setBounds(143, 458, 105, 35);
		btnGuide.setText("Guide");
		
		Label lblPleaseSelectA = new Label(shlSwtApplication, SWT.NONE);
		lblPleaseSelectA.setBounds(10, 145, 419, 47);
		lblPleaseSelectA.setText("Please Select a Building before looking for a location");
		
		
		//trial.addItemListener(new ItemListener() {
			
		//});


	}
}

