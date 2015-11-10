import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.stream.events.StartDocument;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Controller.DatabaseMethods;
import Entity.Edge;
import Entity.Point;


public class MapPanel extends JFrame{
	//private ArrayList map_list = new ArrayList();
	//This function load the images of the map into the class
	//final Image img = Toolkit.getDefaultToolkit().getImage("./Images" + image_name + ".jpg"); 
	
	public boolean isNumeric(String str) {
		  for (int i = 0; i < str.length(); i++){
		   //System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
	}
	
	public MapPanel() throws IOException {
		//Get the database to manage the data
		DatabaseMethods DB = new DatabaseMethods();
		
		//Set the layout of the whole UI of admin tool
		this.setLayout(new GridLayout(1,2,4,4));
		
		//Get the panel to display the map
		ArcsPanel arc = new ArcsPanel();
		
		//The panel used to choose which map to be edited
		JPanel map_select_dropdown_panel = new JPanel();
		
		//The panel used to input the necessary information of point or edge
		JPanel input_panel = new JPanel();
		input_panel.setLayout(new GridLayout(0,4,4,4));
		
		//The radio button to choose the mode edge or point
		ButtonGroup group = new ButtonGroup();
		JRadioButton Edge_radio = new JRadioButton("Edge");
		JRadioButton Point_radio = new JRadioButton("Point");
		
		//The buttons to add and remove the points and edges
		JButton Edge_remove_button = new JButton("Remove");
		JButton Edge_add_button = new JButton("Add");
		JButton Point_remove_buton = new JButton("Remove");
		JButton Point_add_button = new JButton("Add");
		
		//Initialization of some items
		Point_add_button.setEnabled(false);
		Point_remove_buton.setEnabled(false);
		Edge_add_button.setEnabled(false);
		Edge_remove_button.setEnabled(false);
		
		//TextFields needed in the input panel
		JTextField X_Start_Point = new JTextField("X-start");
		JTextField Y_Start_Point = new JTextField("Y-start");
		JTextField X_End_Point = new JTextField("X-end");
		JTextField Y_End_Point = new JTextField("Y-end");
		JTextField X_Point = new JTextField("X");
		JTextField Y_Point = new JTextField("Y");
		JTextField Room_Number = new JTextField("Room No.");
		JTextField Room_Name = new JTextField("Name");
		
		JTextArea console = new JTextArea("");
		
		//Add listener when the radio clicked
		Point_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = arc.EDIT_POINT;
				//clear the data of the edge mode
				arc.Edge_end_Point = null;
				arc.Edge_start_Point = null;
				arc.selected_Point = null;
				arc.new_Point = null;
				//Refresh the map
				arc.repaint();
				//Enable the buttons
				Point_add_button.setEnabled(true);
				Point_remove_buton.setEnabled(true);
				Edge_add_button.setEnabled(false);
				Edge_remove_button.setEnabled(false);
				//Reset the text
				X_Start_Point.setText("X-start");
				Y_Start_Point.setText("Y-start");
				X_End_Point.setText("X-end");
				Y_End_Point.setText("Y-end");
			}
		});
		
		Edge_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = arc.EDIT_EDGE;
				//clear the data of the point mode
				arc.Edge_end_Point = null;
				arc.Edge_start_Point = null;
				arc.selected_Point = null;
				arc.new_Point = null;
				
				//Refresh the map
				arc.repaint();
				//Enable the buttons
				Point_add_button.setEnabled(false);
				Point_remove_buton.setEnabled(false);
				Edge_add_button.setEnabled(true);
				Edge_remove_button.setEnabled(true);
				//Reset the text
				X_Start_Point.setText("X-start");
				Y_Start_Point.setText("Y-start");
				X_End_Point.setText("X-end");
				Y_End_Point.setText("Y-end");
			}
		});
		//Add the radio box into the group
		group.add(Edge_radio);
		group.add(Point_radio);
		
		//fix up the input panel
		input_panel.add(Edge_radio);
		input_panel.add(new JLabel(""));
		input_panel.add(Point_radio);
		input_panel.add(new JLabel(""));
		input_panel.add(X_Start_Point);
		input_panel.add(Y_Start_Point);
		input_panel.add(Room_Number);
		input_panel.add(Room_Name);
		input_panel.add(X_End_Point);
		input_panel.add(Y_End_Point);
		input_panel.add(X_Point);
		input_panel.add(Y_Point);
		input_panel.add(Edge_add_button);
		
		//Add the listener to the add button for edge
		Edge_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//judge if here is one edge that user want to add 
				if(arc.Edge_end_Point != null && arc.Edge_start_Point != null) {
					Edge tmp = new Edge();
					//set the attributes of the edge
					tmp.setStartPointID(arc.Edge_start_Point.getPointID());
					tmp.setEndPointID(arc.Edge_end_Point.getPointID());
					//set the weight of the point
					tmp.set_weight_from_point(arc.Edge_start_Point, arc.Edge_end_Point);
					//save the edge into the database
					DB.SaveEdge(tmp);
					tmp.setStartPointID(arc.Edge_end_Point.getPointID());
					tmp.setEndPointID(arc.Edge_start_Point.getPointID());
					DB.SaveEdge(tmp);
					
				}
				//refresh the map	
				arc.repaint();
			}
			
		});
		input_panel.add(Edge_remove_button);
		
		//Add the listener to the remove edge button
		Edge_remove_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//If user really choose one edge
				if(arc.Edge_start_Point != null && arc.Edge_end_Point != null) {
					DB.DeleteEdge(Integer.parseInt(X_Start_Point.getText()),Integer.parseInt(Y_Start_Point.getText()),Integer.parseInt(X_End_Point.getText()),Integer.parseInt(Y_End_Point.getText()));
					DB.DeleteEdge(Integer.parseInt(X_End_Point.getText()),Integer.parseInt(Y_End_Point.getText()), Integer.parseInt(X_Start_Point.getText()),Integer.parseInt(Y_Start_Point.getText()));
				}
				//refresh the map
				arc.repaint();
			}
		});
		
		//vector used for the point attribute
		Vector<String> Point_attribute_item = new Vector<String>();
		Point_attribute_item.add("Position");
		Point_attribute_item.add("Turning");
		JComboBox<String> Point_attribute = new JComboBox<String>(Point_attribute_item);
		
		//vector used for the point entrance id
		Vector<String> Point_Entrance_Id_Item = new Vector<String>();
		Point_Entrance_Id_Item.add("1");
		Point_Entrance_Id_Item.add("2");
		Point_Entrance_Id_Item.add("3");
		JComboBox<String> Point_Entrance_Id = new JComboBox<String>(Point_attribute_item);
		input_panel.add(Point_attribute);
		input_panel.add(Point_Entrance_Id);
		input_panel.add(new JPanel());
		input_panel.add(new JPanel());
		input_panel.add(Point_add_button);
		input_panel.add(Point_remove_buton);
		
		//Add the listener to the remove point button
		Point_remove_buton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//delete the selected point first
				if(arc.selected_Point != null) {
					ArrayList<Edge> all_edges = new ArrayList<Edge>();
					all_edges = DB.ReadEdges();
					int edge_no = 0;
					//delete the edges related to this point
					for(Edge edge:all_edges) {
						if(edge.getStartPointID() == arc.selected_Point.getPointID()) {
							++edge_no;
							DB.DeleteEdge(arc.selected_Point.getxCooridinate(), arc.selected_Point.getyCooridinate(), DB.GetPointById(edge.getEndPointID()).getxCooridinate(),  DB.GetPointById(edge.getEndPointID()).getyCooridinate());
						}
						else if(edge.getEndPointID() == arc.selected_Point.getPointID()) {
							DB.DeleteEdge(DB.GetPointById(edge.getStartPointID()).getxCooridinate(),  DB.GetPointById(edge.getStartPointID()).getyCooridinate(), arc.selected_Point.getxCooridinate(), arc.selected_Point.getyCooridinate());
							++edge_no;
						}
					}
					//delete the point
					DB.DeletePoint(arc.selected_Point.getxCooridinate(), arc.selected_Point.getyCooridinate());
					//System.out.println("Delete successfully! And " + edge_no + " related edges deleted");
					console.setText("Delete successfully! And " + edge_no + " related edges deleted");
				}
				arc.selected_Point = null;
				arc.repaint();
			}
		});
		
		//The dropdown menu to choose the map
		map_select_dropdown_panel.setLayout(new GridLayout(0, 1,10,10));
		JPanel select_building_menu = new JPanel();
		select_building_menu.setLayout(new GridLayout(0,1,4,4));
		JLabel label = new JLabel("Select the building");
		select_building_menu.add(new JPanel());
		select_building_menu.add(label);
		Vector<String> menu = new Vector();
		menu.add("Campus Center 1st Floor");
		menu.add("Campus Center 2nd Floor");
		menu.add("Campus Center 3rd Floor");
		menu.add("Project Center 1st Floor");
		menu.add("Project Center 2nd Floor");
		JComboBox box = new JComboBox(menu);
		
		
		//Add the listener to the add point button
		Point_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arc.new_Point != null) {
					arc.new_Point.setBuildName((String)box.getSelectedItem());
					//This is one hash function to make sure that the ID is unique for any point
					arc.new_Point.setPointID(arc.new_Point.getxCooridinate() * 100 + arc.new_Point.getyCooridinate() + arc.new_Point.getBuildName().length());
					boolean is_correct = true;
					//set the function name
					if(Room_Name.getText().equals("Name")) {
						console.setText("Please input the name of the point");
						is_correct = false;
					}
					arc.new_Point.setFeature(Room_Name.getText());
					//set the room number
					if(!isNumeric(Room_Number.getText())) {
						console.setText("Please input the number of the room");
						is_correct = false;
					}
					if(is_correct) {
						//add the point
						DB.SavePoint(arc.new_Point);
						arc.repaint();
					}
					//reset the new_point
					arc.new_Point = null;
				}
			}	
		});
		
		//Add the listener to the dropdown menu
		ItemListener itemListener = new ItemListener() {   
			public void itemStateChanged(ItemEvent itemEvent) {   
				int state = itemEvent.getStateChange();   
				if(state == ItemEvent.SELECTED) {  
					//refresh the state of the map
					arc.selected_Point = null;
					arc.Edge_start_Point = null;
					arc.Edge_end_Point = null;
					arc.new_Point = null;
					arc.loadImage((String)itemEvent.getItem());
					arc.building = (String)itemEvent.getItem();
					arc.list = DB.ReadBuildingPoints((String)itemEvent.getItem());
					arc.repaint();
				}
			}   
		    };   
		box.addItemListener(itemListener);
		select_building_menu.add(box);
		select_building_menu.add(new JPanel());
		map_select_dropdown_panel.add(select_building_menu);
		//map_select_dropdown_panel.add(new JPanel());
		map_select_dropdown_panel.add(input_panel);
		map_select_dropdown_panel.add(console);
		
		
		this.add(arc);
		this.add(map_select_dropdown_panel);
		
		//Add the listener to the map panel
		arc.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { 
				if(arc.new_Point != null) {
					X_Point.setText(arc.new_Point.getxCooridinate() + "");
					Y_Point.setText(arc.new_Point.getyCooridinate() + "");
				}
				if(arc.Edge_start_Point != null) {
					X_Start_Point.setText(arc.Edge_start_Point.getxCooridinate() + "");
					Y_Start_Point.setText(arc.Edge_start_Point.getyCooridinate() + "");
				}
				if(arc.Edge_end_Point != null) {
					X_End_Point.setText(arc.Edge_end_Point.getxCooridinate() + "");
					Y_End_Point.setText(arc.Edge_end_Point.getyCooridinate() + "");
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}