package com.bangkura;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.bangkura.Entity.Edge;
import com.bangkura.Entity.Point;

public class AdminTool extends JPanel{
	final int BUILDING = 0;
	final int LOCATION = 1;
	final int STAIR = 2;
	final int TURNING = 3;
	final String[] attrs = {"building","location","stair"};
	//absolute position of the image
	int absolute_x = 0;
	int absolute_y = 0;
	//the position of the mouse when start dragging
	int mouse_x;
	int mouse_y;
	//the position of the image last dragged
	int relative_x = 0;
	int relative_y = 0;
	//size of the panel
	final int SIZE = 800;
	final int WEIDTH = 1000;
	final int HEIGHT = 800;
	//zoom ratio
	double ratio = 1;
	//zoom buttons
	JButton zoomin = new JButton("+");
	JButton zoomout = new JButton("-");
	//wrap text in button through html
	JButton selectImage = new JButton("<html><center>"+"Add or"+"<br>"+"Delete Map"+"</center></html>");
	
	String building_name = null;
	
	PointLabel new_point = null;
	
	AdminButtonPanel buttonpanel = new AdminButtonPanel();
	
	Image img = Toolkit.getDefaultToolkit().getImage("welcome.jpg");
	
	DefaultComboBoxModel<String> model = null;
	
	JComboBox<Vector<String>> map_select_menu = null;
	
	DatabaseMethods DB = new DatabaseMethods();
	
	ArrayList<PointLabel> pointlist = new ArrayList<PointLabel>();
	
	PointEditingMenu menu = new PointEditingMenu();
	
	PointLabel start_point = null;
	
	ArrayList<Edge> edgelist = new ArrayList<Edge>();
	
	private void changeBuilding(String image_name) {
		for(PointLabel p:pointlist) {
			this.remove(p);
		}
		pointlist = new ArrayList<PointLabel>();
		edgelist = new ArrayList<Edge>();
		ArrayList<Point> points = DB.ReadBuildingPoints(image_name);
		edgelist = DB.ReadEdges();
		for(Point p:points) {
			pointlist.add(new PointLabel(p));
		}
		for(PointLabel p:pointlist) {
			this.add(p);
			p.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(buttonpanel.mode == buttonpanel.EDGE_MODE && start_point == null) {
						start_point = p;
					}
					if(buttonpanel.mode == buttonpanel.EDGE_MODE && start_point != null && start_point != p) {
						Edge edge = new Edge();
						edge.set_weight_from_point(start_point.getPoint(), p.getPoint());
						edge.setStartPointID(start_point.getPoint().getPointID());
						edge.setEndPointID(p.getPoint().getPointID());
						DB.SaveEdge(edge);
						edge.set_weight_from_point(start_point.getPoint(), p.getPoint());
						edge.setEndPointID(start_point.getPoint().getPointID());
						edge.setStartPointID(p.getPoint().getPointID());
						DB.SaveEdge(edge);
						edgelist.add(edge);
						start_point = null;
						repaint();
					}
					if(buttonpanel.mode == buttonpanel.POINT_MODE) {
						menu.setBounds(convert_x(p.getPoint().xCooridinate), convert_y(p.getPoint().yCooridinate),300,200);
						menu.setPoint(p.getPoint());
						menu.setVisible(true);
					}
				}
			});
		}
		img = Toolkit.getDefaultToolkit().getImage(image_name + ".jpg");
		repaint();
	}
	
	private void zoom() {
		for(PointLabel p:pointlist) {
			p.zoom(convert_x(p.x), convert_y(p.y));
		}
	}
	
	public AdminTool() {
		// TODO Auto-generated constructor stub
		//initial
		this.setBounds(0,0,WEIDTH,HEIGHT);
		this.setLayout(null);
		zoomin.setBounds(900,550, 50, 50);
		zoomout.setBounds(900,610, 50, 50);
		selectImage.setBounds(100,25,100,75);
		this.add(buttonpanel);
		this.add(zoomin);
		this.add(zoomout);
		this.add(menu);
		this.add(selectImage);
		
		//Initial of the combobox
		Vector<String> building_menu_item = MapImages.getImages();
		model = new DefaultComboBoxModel<>(building_menu_item);
		map_select_menu = new JComboBox(model);
//		Vector<String> building_menu_item = new Vector<String>();
//		building_menu_item.add("null");
//		building_menu_item.add("campus");
//		building_menu_item.add("Campus Center 1st Floor");
//		building_menu_item.add("Campus Center 2nd Floor");
//		building_menu_item.add("Campus Center 3rd Floor");
//		building_menu_item.add("Project Center 1st Floor");
//		building_menu_item.add("Project Center 2nd Floor");
//		map_select_menu = new JComboBox(building_menu_item);
		map_select_menu.setBounds(300,20,350,40);
		this.add(map_select_menu);
		map_select_menu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!e.getItem().equals("null")) {
					building_name = (String)e.getItem();
					changeBuilding(building_name);
					zoom();
					repaint();
				}
			}
		});
		selectImage.addActionListener(new ActionListener(){
			//add a new map to the GUI
			@Override
			public void actionPerformed(ActionEvent e){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						//Turn off bold font
						UIManager.put("swing.boldMetal", Boolean.FALSE);
						FileChooser fc = new FileChooser();
						fc.createAndShowGUI();
						map_select_menu = new JComboBox(model);
						repaint();
					}
				}
				);
			}
		});
		
		menu.delete_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id = menu.point.getPointID();
				ArrayList<Edge> elist = DB.ReadEdges();
				for(Edge ee:elist) {
					if(ee.getEndPointID() == id)
						DB.DeleteEdge(DB.GetPointById(ee.getStartPointID()).getxCooridinate(), DB.GetPointById(ee.getStartPointID()).getyCooridinate(), menu.point.getxCooridinate(), menu.point.getyCooridinate());
					if(ee.getStartPointID() == id)
						DB.DeleteEdge(menu.point.getxCooridinate(), menu.point.getyCooridinate(),DB.GetPointById(ee.getStartPointID()).getxCooridinate(), DB.GetPointById(ee.getStartPointID()).getyCooridinate());
				}
				DB.DeletePoint(menu.point.getxCooridinate(), menu.point.getyCooridinate());
				menu.setVisible(false);
				if(new_point != null)
					remove(new_point);
				changeBuilding(building_name);
				zoom();
				repaint();
			}
		});
		
		menu.save_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new_point.getPoint().setBuildName(building_name);
				new_point.getPoint().setRoomNum(menu.number_field.getText());
				new_point.getPoint().setFeature(menu.name_field.getText());
				new_point.getPoint().setImageURL(menu.info_field.getText());
				new_point.getPoint().setAttributes(menu.attrs_field.getSelectedIndex());
				DB.SavePoint(new_point.getPoint());
				menu.setVisible(false);
				zoom();
				repaint();
			}
		});
		
		menu.cancel_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menu.setVisible(false);
				if(new_point != null)
					remove(new_point);
				changeBuilding(building_name);
				zoom();
				repaint();
			}
		});
		//adding the listeners of the zoom in and zoom out button
		//we have five levels for zoom in and zoom out
		zoomin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ratio <=  1.8)
					ratio += 0.2;
				//System.out.println(ratio);
				zoom();
				repaint();
			}
		});
		zoomout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ratio > 1)
					ratio -= 0.2;
				//System.out.println(ratio);
				zoom();
				repaint();
			}
		});
		
		//listener for the drag function
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				relative_x = absolute_x;
				relative_y = absolute_y;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse_x = e.getX();
				mouse_y = e.getY();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			//quick view bar animation
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(buttonpanel.mode == buttonpanel.ENTRANCE_MODE || buttonpanel.mode == buttonpanel.STAIR_MODE) {
					NewEgePanel edgepanel = new NewEgePanel();
				}
				
				if(buttonpanel.mode == buttonpanel.POINT_MODE) {
					Point tmp_point = new Point(back_x(e.getX()), back_y(e.getY()));
					new_point = new PointLabel(tmp_point);
					add(new_point);
					pointlist.add(new_point);
					menu.setBounds(convert_x(new_point.x), convert_y(new_point.y),300,200);
					menu.newPoint();
					menu.setVisible(true);
					zoom();
				}
				if(buttonpanel.mode == buttonpanel.EDGE_MODE) {
					if(start_point != null && e.getButton()!=MouseEvent.BUTTON3) {
						Point tmp_point = new Point(back_x(e.getX()), back_y(e.getY()));
						tmp_point.setAttributes(1);
						tmp_point.setBuildName(building_name);
						DB.SavePoint(tmp_point);
						new_point = new PointLabel(tmp_point);
						new_point.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								if(buttonpanel.mode == buttonpanel.EDGE_MODE && start_point == null) {
									start_point = new_point;
								}
								if(start_point != null && start_point != new_point) {
									Edge edge = new Edge();
									edge.set_weight_from_point(start_point.getPoint(), new_point.getPoint());
									edge.setStartPointID(start_point.getPoint().getPointID());
									edge.setEndPointID(new_point.getPoint().getPointID());
									DB.SaveEdge(edge);
									Edge edge2 = new Edge();
									edge2.set_weight_from_point(start_point.getPoint(), new_point.getPoint());
									edge2.setStartPointID(new_point.getPoint().getPointID());
									edge2.setEndPointID(start_point.getPoint().getPointID());
									DB.SaveEdge(edge2);
									edgelist.add(edge);
									start_point = null;
									repaint();
								}
							}
						});
						add(new_point);
						pointlist.add(new_point);
						Edge edge = new Edge();
						edge.set_weight_from_point(start_point.getPoint(), tmp_point);
						edge.setStartPointID(start_point.getPoint().getPointID());
						edge.setEndPointID(tmp_point.getPointID());
						DB.SaveEdge(edge);
						edge = new Edge();
						edge.set_weight_from_point(start_point.getPoint(), tmp_point);
						edge.setStartPointID(tmp_point.getPointID());
						edge.setEndPointID(start_point.getPoint().getPointID());
						DB.SaveEdge(edge);
						edgelist.add(edge);
						start_point = new_point;
						zoom();
					}
					if(e.getButton() == MouseEvent.BUTTON3) {
						start_point = null;
						repaint();
					}
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stubs
				if(buttonpanel.mode == buttonpanel.EDGE_MODE) {
					mouse_x = e.getX();
					mouse_y = e.getY();
					repaint();
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int tmp_x = e.getX() - mouse_x + relative_x;
				int tmp_y = e.getY() - mouse_y + relative_y;
				int length = (int)(1000*ratio);
				if(tmp_x < 0 && tmp_x + length > WEIDTH)
					absolute_x = tmp_x;
				if(tmp_y < 0 && tmp_y + length > HEIGHT)
					absolute_y = tmp_y;
				zoom();
				repaint();
			}
		});
	}
	
	//function to draw the graphics
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(img, absolute_x, absolute_y, (int)(1000 * ratio), (int)(1000 * ratio), this);
		g.setColor(new Color(131, 131, 192));
		
		((Graphics2D)g).setStroke(new BasicStroke(3));
		for(PointLabel p:pointlist) {
			for(Edge e:edgelist) {
				if(e.getStartPointID() == p.getPoint().getPointID()) {
					boolean find =   false;
					for(PointLabel pp:pointlist) {
						if(pp.getPoint().getPointID() == e.getEndPointID())
							find = true;
					}
					if(find)
						g.drawLine(convert_x(DB.GetPointById(e.getStartPointID()).xCooridinate), convert_y(DB.GetPointById(e.getStartPointID()).yCooridinate), convert_x(DB.GetPointById(e.getEndPointID()).xCooridinate), convert_y(DB.GetPointById(e.getEndPointID()).yCooridinate));
				}
			}
		}
		if(start_point != null) {
			g.drawLine(convert_x(start_point.x), convert_y(start_point.y), mouse_x, mouse_y);
		}
	}
	
	//convert the position from the database to the actual position
	private int convert_x(int x) {
		return (int)(x * ratio) + absolute_x;
	}
	
	private int convert_y(int y) {
		return (int)(y * ratio) + absolute_y;
	}
	
	private int back_x(int x) {
		return (int)((x - absolute_x)/ratio);
	}
	
	private int back_y(int y) {
		return (int)((y - absolute_y)/ratio);
	}
	
}
