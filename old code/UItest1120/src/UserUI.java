import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.DatabaseMethods;
import Entity.Point;

public class UserUI extends JFrame{
	Vector<String> point_vector = new Vector<String>();
	ArrayList<Point> point_arraylist = new ArrayList<Point>();
	DatabaseMethods DB = new DatabaseMethods();
	JComboBox start_point_menu;
	JComboBox end_point_menu;
	Point start_point = null;
	Point end_point = null;
	LinkedList<Vertex> path = null;
	
	public void change_building(String building_name, UserMapTool map) {
		map.loadImage(building_name);
		point_arraylist = new ArrayList<Point>();
		point_arraylist = DB.ReadBuildingPoints(building_name);
		start_point_menu.removeAllItems();
		end_point_menu.removeAllItems();
		System.out.println("remove");
		start_point_menu.addItem("null");
		end_point_menu.addItem("null");
		if(!point_arraylist.isEmpty()) {
			for(Point p:point_arraylist) {
				if(!p.getFeature().equals("added")) {
					start_point_menu.addItem(p.getFeature());
					end_point_menu.addItem(p.getFeature());
				}
				//point_arraylist.remove(p);
			}
		}
		map.repaint();
	}
	
	public UserUI() {
		UserMapTool map = new UserMapTool();
		JPanel control_panel = new JPanel();
		control_panel.setLayout(new GridLayout(0, 1));
		JLabel select_building_panel = new JLabel("Please select a building");
		
		Vector<String> building_menu_item = new Vector();
		building_menu_item.add("null");
		building_menu_item.add("Campus Center 1st Floor");
		building_menu_item.add("Campus Center 2nd Floor");
		building_menu_item.add("Campus Center 3rd Floor");
		building_menu_item.add("Project Center 1st Floor");
		building_menu_item.add("Project Center 2nd Floor");
		
//		Vector<String> point_vector = new Vector<String>();
//		ArrayList<Point> point_arraylist = new ArrayList<>();
		DatabaseMethods DB = new DatabaseMethods();
		point_arraylist = DB.ReadBuildingPoints("Campus Center 1st Floor");
		point_vector.add("null");
		for(Point p:point_arraylist) {
			point_vector.add(p.getFeature());
		}
		JComboBox building_menu = new JComboBox(building_menu_item);
		start_point_menu = new JComboBox();
		end_point_menu = new JComboBox();
		building_menu.setBounds(0, 0, 400, 0);
		JButton guide_button = new JButton("Guide");
		control_panel.add(select_building_panel);
		control_panel.add(building_menu);
		control_panel.add(start_point_menu);
		control_panel.add(end_point_menu);
		control_panel.add(guide_button);
		this.setLayout(new GridLayout(0,2,20,20));
		this.add(map);
		this.add(control_panel);
		
		building_menu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!e.getItem().equals("null"))
					change_building((String)e.getItem(), map);
			}
		});
		
		start_point_menu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!e.getItem().equals("null")) {
					String point_name = (String) e.getItem();
					//System.out.println(point_name);
					for(Point p:point_arraylist) {
						if(p.getFeature().equals(point_name))
							start_point = p;
					}
					map.sp = start_point;
		        	map.ep = end_point;
		        	map.repaint();
		        	System.out.println("start point is that " + map.sp.getFeature());				
				}
			}
		});
		
		end_point_menu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!e.getItem().equals("null")) {
					String point_name = (String) e.getItem();
					//System.out.println(point_name);
					for(Point p:point_arraylist) {
						if(p.getFeature().equals(point_name))
							end_point = p;
					}
					map.sp = start_point;
					map.ep = end_point;
					map.repaint();
					System.out.println("end point is that " + end_point.getFeature());
			
				}
			}
		});
		
		guide_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ReadData read = new ReadData();
		        
		        List<Vertex> points = new ArrayList<Vertex>(read.pointToVertex());
		        List<Edges> edges = new ArrayList<Edges>(read.edgeToEdges());
		        
		        Graph graph = new Graph(points, edges);
		        DJAlgorithm d = new DJAlgorithm(graph);
//		        System.out.println(start_point.getPointID());
		        
		        Vertex start_vertex = new Vertex();
		        Vertex end_vertex = new Vertex();
		        if(map.sp != null && map.ep != null) {
		        	for(Vertex v:points) {
			        	if(v.getId().equals(Integer.toString(start_point.getPointID())))
			        		start_vertex = v;
			        	if(v.getId().equals(Integer.toString(end_point.getPointID())))
			        		end_vertex = v;
			        }
			        
			        System.out.println(start_vertex.getId());
			        System.out.println(end_vertex.getId());
			        
			        d.execute(start_vertex);
			        map.path = d.getPath(end_vertex);
			        map.sp = start_point;
			        map.ep = end_point;
			        map.repaint();
		        }
			}
		});
	}
	
}
