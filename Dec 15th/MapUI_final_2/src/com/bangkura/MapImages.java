/*
 * Stores and updates the list of image files used for maps in the AdminTool
 * 
*/
package com.bangkura;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

public class MapImages extends WindowAdapter{
	
	static File[] listOfMapFiles;
	
	/***
	 * Initializes the combobox for selecting a map
	 */
	public static Vector<String> getImages() {
		// Store the path of the project into a variable
    	Path currentRelativePath = Paths.get("");
    	String s = currentRelativePath.toAbsolutePath().toString();
		String folderPath = s  ;
        File mapImage = new File(folderPath);
        
        // Initialize the combobox with null
     	Vector<String> building_menu_item = new Vector<String>();
     	building_menu_item.removeAllElements();
     	building_menu_item.add("null");
        
		// Create a variable to hold the list of images
        listOfMapFiles = mapImage.listFiles();
		
        // Search through the directory
		if (mapImage.isDirectory()) {
			
			
			// Print to console for testing purposes
			if (listOfMapFiles.length == 0)
				System.out.println("There are no maps inside the Maps Folder");
			else
				System.out.println("List of images:");	
			
			for (File file : listOfMapFiles) {
				if(!file.isDirectory()) {
					//System.out.println(file.getName().substring(0, file.getName().lastIndexOf('.')));
					building_menu_item.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
					//model.addElement(building_menu_item.get(building_menu_item.size() - 1));
				}
			}
		}
		
		return building_menu_item;
	}
	
	@Override
	public void windowClosing(WindowEvent e){
		//update map images
		getImages();
		System.out.println("print list of buildings: ");
		System.out.println(getImages());//displays list of buildings
		//need to update jcombobox

	}
}
