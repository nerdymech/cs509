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

            // Compare the current selected file to the file in the project if it exists
            String s2 = s + "\\" + fc.getSelectedFile().getName();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] tokens = s2.split(pattern);
			System.out.println(fc.getSelectedFile().getName());
			System.out.println(tokens[tokens.length - 1]);
			
			// Loop to test to see if the current selected file and the file in the project already match
			while (fc.getSelectedFile().getName().equals(tokens[tokens.length - 1])) {
				// If the user cancels, exit the Windows Explorer
				if (returnVal == JFileChooser.CANCEL_OPTION)
					break;
				// If the current selected file and the file in the project match, ask for a new file
				else if (fc.getSelectedFile().getName().equals(tokens[tokens.length - 1])) {
					System.out.println("Error! The file already exists! Please choose another one.");
					log.append("Error! The file already exists! Please choose another one." + newline);
					returnVal = fc.showOpenDialog(FileChooser.this);
				}
				// If the current selected file and the file in the project don't match, exit
				else if (returnVal == JFileChooser.APPROVE_OPTION)
					break;
			}
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
        		try {
        			// Read in the file and store it as a BufferedImage
        			BufferedImage bufferedImage = ImageIO.read(new File(fc.getSelectedFile().toString()));
        			
        			// Create a file with it's name, extension, and contents
        			File file = new File(s2);
        			
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
