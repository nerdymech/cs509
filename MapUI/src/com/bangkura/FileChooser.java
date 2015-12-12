//modified file chooser demo from official java tutorials
//feeds file name to the admin tool
package com.bangkura;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FileChooser extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fc;

    public FileChooser() {
        super(new BorderLayout());

        // Create the log first because the action listeners need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //allow the user to select a file or a directory
        //if the line below is commented it will be set to default
        //default chooses files only
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  
        openButton = new JButton("Open and Save File");
        openButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
    	// Store the path of the project into a variable
    	Path currentRelativePath = Paths.get("");
    	String s = currentRelativePath.toAbsolutePath().toString();
    	
        // Handle open and save button action.
        if (e.getSource() == openButton) {
        	
        	// Open a Windows Explorer to choose a file
            int returnVal = fc.showOpenDialog(FileChooser.this);
            
            // Create a variable to contain the absolute location of the location you want to
            // save the file to
            String s2 = s + "\\Maps\\" + fc.getSelectedFile().getName();
            System.out.println(s2);
            System.out.println(fc.getSelectedFile().getName());
            
            // Variables needed to search through the Maps folder
            String folderPath = s + "\\Maps\\";
            File mapImage = new File(folderPath);
            
            // Search through the directory
            if (mapImage.isDirectory()) {
            	
            	// Create an array for all of the files, and variables needed to search through the directory
	            File[] listOfFiles = mapImage.listFiles();
	            boolean restart = true;
	            int filesLeft = listOfFiles.length;
	            
	            // Print to the console for testing purposes
	            if (listOfFiles.length == 0)
	            	System.out.println("There are no images inside the Maps folder.");
	            else
	            	System.out.println("List of images:");
	            
	            // While loop used here in case if a new file is selected and already exists
	            while (restart == true) {
	            	
	            	// The for loop actually iterates through the Maps folder
		            for (File file : listOfFiles) {
		            	
		            	// Iterate only through files that are not directories
		            	if(!file.isDirectory())
							try {
								// Create a pattern for the parse.
								// Parse the files by a "/" delimiter and save it into an array
								// containing the image file name
								String pattern = Pattern.quote(System.getProperty("file.separator"));
								String[] tokens = file.getCanonicalPath().toString().split(pattern);
								
								// If there are no images, add the image to the Map folder
								if (tokens.length == 0) {
									System.out.println("There are no images inside the Maps folder.");
									restart = false;
									break;
								}
								// If there are images in the folder, print out the last index's image name
								else
									System.out.println(tokens[tokens.length - 1]);
								
								// Compare the current selected file to the parsed array containing the image name
								if (tokens[tokens.length - 1].equals(fc.getSelectedFile().getName())) {
									System.out.println("The file already exists.");
									
									// While the current selected file equals the parsed array image name, open
									// the Windows Explorer again to choose a new file
									while (tokens[tokens.length - 1].equals(fc.getSelectedFile().getName())) {
										
										// If the user cancels, exit the Windows Explorer
										if (returnVal == JFileChooser.CANCEL_OPTION) {
											if (restart == true)
												restart = false;
											break;
										}
										// If the current selected file and the file in the project match, ask for a new file
										// and reset the number of files left in case if the new file they select already exists
										else if (tokens[tokens.length - 1].equals(fc.getSelectedFile().getName())) {
											System.out.println("Error! The file already exists! Please choose another one.");
											log.append("Error! The file already exists! Please choose another one." + newline);
											returnVal = fc.showOpenDialog(FileChooser.this);
											filesLeft = listOfFiles.length;
											restart = true;
										}
										// If the current selected file and the file in the project don't match, exit
										else if (returnVal == JFileChooser.APPROVE_OPTION) {
											if (restart == true)
												restart = false;
											break;
										}
									}
									break;
								}
								// Count down the number of files. If the current selected file doesn't match any file
								// in the parsed array, then add it to the Maps folder.
								else {
									--filesLeft;
									if (filesLeft == 0) {
										restart = false;
										break;
									}
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		            }
	            }
            }
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
        		try {
        			// Read in the file and store it as a BufferedImage
        			BufferedImage bufferedImage = ImageIO.read(new File(fc.getSelectedFile().toString()));
        			
        			// Create a file with it's name, extension, and contents
        			File file = new File(s2);
        			
        			//System.out.println(file.getPath()); // C:\Users\Kennedy Tran\Desktop\cs509\MapUI\Maps\Project Center 3rd Floor.jpg
        			
        			// Log to the Window's console that the file is being read
          	      	log.append("Opening: " + file.getName() + "." + newline);
          	      	
          	      	// Save the file to the project
          	      	ImageIO.write(bufferedImage, "jpg", file);
          	      	
          	      	// Log to the Window's console that the file is being read
          	      	log.append("Saving: " + file.getName() + "." + newline);
          	    } catch (Exception e1) {
          	    	e1.printStackTrace();
          	    }
        	}
            else {
            	// If the user cancels the Windows Explorer before choosing a file
                log.append("Save command cancelled by user." + newline);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    protected void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileChooser());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
