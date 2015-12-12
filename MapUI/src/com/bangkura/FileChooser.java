
//modified file chooser demo from official java tutorials
//feeds file name to the admin tool
package com.bangkura;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;


public class FileChooser extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;

    public FileChooser() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
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
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
        
        //Create the save button.
        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooser.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	//getSelectedFile() returns the file path of the selected file
                File file = fc.getSelectedFile();
                //verify that the file path is what gets returned
                //System.out.println(file); 
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
            }

        //Handle save button action.
        else if (e.getSource() == saveButton) {
        	
        	int returnVal = fc.showSaveDialog(FileChooser.this);
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		File file = fc.getSelectedFile();
        		//This is where a real application would save the file.
        		log.append("Saving: " + file.getName() + "." + newline);
        	} else {
        		log.append("Save command cancelled by user." + newline);
        	}
        	log.setCaretPosition(log.getDocument().getLength());
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
    //test to make sure that the code works properly
//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                //Turn off bold font
//                UIManager.put("swing.boldMetal", Boolean.FALSE); 
//                createAndShowGUI();
//            }
//        });
//    }
}
