package com.bangkura;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.View;
import javax.tools.DocumentationTool.Location;

import org.xml.sax.Locator;

import com.bangkura.Entity.FavouritePlace;
import com.bangkura.Entity.Point;
import com.bangkura.Entity.User;

public class MapDisplay extends JPanel{
	final boolean VIEW_MODE = false;
	final boolean ROUTE_MODE = true;
	boolean  mode = VIEW_MODE;
	//absolute position of the image
	private int absolute_x = 0;
	private int absolute_y = 0;
	//the position of the mouse when start dragging
	private int mouse_x;
	private int mouse_y;
	//the position of the image last dragged
	private int relative_x = 0;
	private int relative_y = 0;
	//size of the panel
	private final int WEIDTH = 1000;
	private final int HEIGHT = 800;
	//zoom ratio
	private double ratio = 1;
	//zoom buttons
	JButton zoomin = new JButton("+");
	JButton zoomout = new JButton("-");
	JButton sidebarbutton = new JButton("guide");
	JButton login_button = new JButton("Login");
	JButton next_button = new JButton(">>");
	JButton previous_button = new JButton("<<");
	JButton back_button = new JButton("back");
	JLabel login_label = new JLabel(new ImageIcon("login.png"));
	
	//quick view panel
	QuickViewPanel qpanel = new QuickViewPanel();
	
	//sidebar
	SideBar sidebar = new SideBar();
	
	//arraylist of point
	ArrayList<Point> pointarray = new ArrayList<Point>();
	
	//building name list
	ArrayList<JLabel> buildinglist = new ArrayList<JLabel>();
	
	ArrayList<String> path_buildings = null;
	
	ArrayList<LocationLabel> location_array = new ArrayList<LocationLabel>();
	//image displayed
	Image img = Toolkit.getDefaultToolkit().getImage("campus.jpg");
	
	//database method
	DatabaseMethods DB = new DatabaseMethods();
	
	String building_name = null;
	
	JLabel building_label = null;
	
	int arrow_x = 0;
	int arrow_y = 0;
	
	String previous_building = null;
	
	
	User user = null;
	//load the favorite places of the user login
	void setuser(User user) {
		this.user = user;
		ArrayList<FavouritePlace> fav = DB.GetFavouritePlaceByUser(user.getUserID());
		for(FavouritePlace f:fav) {
			sidebar.favlocation.add(DB.GetPointById(f.getPointID()));
		}
	}
	public MapDisplay() {
		// TODO Auto-generated constructor stub
		//initial of the necessary components
		this.setBounds(0,0,WEIDTH,HEIGHT);
		this.setLayout(null);
		zoomin.setBounds(900,550, 50, 50);
		zoomout.setBounds(900,610, 50, 50);
		previous_button.setBounds(900,490, 50, 50);
		next_button.setBounds(900,430, 50, 50);
		back_button.setBounds(900, 665, 50, 50);
		previous_button.setVisible(false);
		next_button.setVisible(false);
		qpanel.setBounds(300, 580, 400, 100);
		sidebar.setBounds(0,0,400,800);
		sidebarbutton.setBounds(0,30,50,50);
		login_button.setBounds(900,20,80,50);
		login_button.setBackground(new Color(62, 119, 241));
		building_label = new JLabel("");
		building_label.setBounds(450, 20, 200, 30);
		login_label.setBounds(920, 20, 50, 50);
		
		sidebarbutton.setIcon(new ImageIcon("guide.png"));


		sidebarbutton.setBorderPainted( false );

		sidebarbutton.setFocusPainted( false );

		sidebarbutton.setContentAreaFilled( false );

		sidebarbutton.setFocusable( true );

		//sidebarbutton.setMargin( new Insets(0, 0, 0, 0));

		sidebarbutton.setText("常用交易");
		
		back_button.setVisible(false);
		
		this.add(back_button);
		this.add(login_label);
		this.add(building_label);
		this.add(previous_button);
		this.add(next_button);
		//this.add(login_button);
		this.add(sidebar);
		this.add(sidebarbutton);
		this.add(zoomin);
		this.add(zoomout);
		this.add(qpanel);
		listenerManager();
		Initial();
	}
	
	private void Initial() {
		ArrayList<Point> array = DB.ReadBuildingPoints("campus");
		for(Point p:array) {
			if(!p.getFeature().equals("null")) {
				LocationLabel label = new LocationLabel(p.getFeature(), p.getxCooridinate(), p.getyCooridinate());
				label.setPoint(p);
				this.add(label);
				location_array.add(label);
				label.addMouseListener(new MouseListener() {
					
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
						if(qpanel.isVisible())
							qpanel.setVisible(false);
						else
							qpanel.setPoint(label.point);
					}
				});
			}
			
		}
	}
	
	//management of all the listeners
	private void listenerManager() {
		
		back_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = VIEW_MODE;
				Initial();
				next_button.setVisible(false);
				previous_button.setVisible(false);
				repaint();
				back_button.setVisible(false);
			}
		});
		
		sidebar.guide_button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ReadData read = new ReadData();
		        
		        List<Vertex> points = new ArrayList<Vertex>(read.pointToVertex());
		        List<Edges> edges = new ArrayList<Edges>(read.edgeToEdges());
		        
		        Graph graph = new Graph(points, edges);
		        DJAlgorithm d = new DJAlgorithm(graph);
		        
		        Vertex start_vertex = new Vertex();
		        Vertex end_vertex = new Vertex();
		        if(sidebar.start_point != null && sidebar.end_point != null) {
		        	for(Vertex v:points) {
			        	if(v.getId().equals(Integer.toString(sidebar.start_point.getPointID())))
			        		start_vertex = v;
			        	if(v.getId().equals(Integer.toString(sidebar.end_point.getPointID())))
			        		end_vertex = v;
			        }
			        
			        System.out.println("The start point: " + start_vertex.getId());
			        System.out.println("The end point: " + end_vertex.getId());
			        
			        d.execute(start_vertex);
			        sidebar.path = d.getPath(end_vertex);
		        }
				
				for(LocationLabel l:location_array) {
					remove(l);
				}
				mode = ROUTE_MODE;
				back_button.setVisible(true);
				if(sidebar.path != null) {
					handlePath(sidebar.path);
					building_name = path_buildings.get(0);
					building_label.setText(building_name);
					img = Toolkit.getDefaultToolkit().getImage(building_name+".jpg");
					repaint();
					if(path_buildings != null  && path_buildings.size() > 1) {
						previous_button.setVisible(true);
						next_button.setVisible(true);
					}
					if(path_buildings != null  && path_buildings.size() == 1) {
						previous_button.setVisible(false);
						next_button.setVisible(false);
					}
					new Thread(new Runnable(){
			            @Override
			            public void run() {
			                for(int i = 0 ; i < 400 ; i ++){
			                    try {
			                        Thread.sleep(1);
			                    } catch (InterruptedException e1) {}
			                    final int position =i;
			                    SwingUtilities.invokeLater(new Runnable(){
			                        @Override
			                        public void run() {
			                        	sidebar.setBounds(-position,0,400,800);
			                        	sidebarbutton.setBounds(400-position,30,50,50);
			                        }
			                    });
			                }
			            }
			        }).start();
					sidebar.clear();
					sidebarbutton.setText("guide");
					sidebar.setExtended(false);
					sidebar.path = null;
				}
			}
		});
		
		next_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.print("click next");
				for(int i = 0; i < path_buildings.size(); ++i) {
					if(path_buildings.get(i).equals(building_name)) {
						if(i+1 < path_buildings.size()) {
							building_name =  path_buildings.get(i+1);
							building_label.setText(building_name);
							img = Toolkit.getDefaultToolkit().getImage(building_name + ".jpg");
							repaint();
							break;
						}
						else {
							
							building_name =  path_buildings.get(0);
							building_label.setText(building_name);
							img = Toolkit.getDefaultToolkit().getImage(building_name+".jpg");
							repaint();
							break;
						}
					}
				}
			}
		});
		
		previous_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("prev");
				for(int i = 0; i < path_buildings.size(); ++i) {
					if(path_buildings.get(i).equals(building_name)) {
						if(i-1 >= 0) {
							building_name =  path_buildings.get(i-1);
							building_label.setText(building_name);
							img = Toolkit.getDefaultToolkit().getImage(building_name + ".jpg");
							repaint();
							break;
						}
						else {
							building_name =  path_buildings.get(path_buildings.size() - 1);
							building_label.setText(building_name);
							img = Toolkit.getDefaultToolkit().getImage(building_name+".jpg");
							repaint();
							break;
						}
					}
				}
			}
		});
		
		//adding the listeners of the zoom in and zoom out button
		//we have five levels for zoom in and zoom out
		zoomin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ratio <=  1.8) {
					ratio += 0.2;
					absolute_x = 0;
					absolute_y = 0;
					relative_x = 0;
					relative_y = 0;
				}
				//System.out.println(ratio);
				repaint();
			}
		});
		zoomout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ratio > 1) {
					ratio -= 0.2;
					absolute_x = 0;
					absolute_y = 0;
					relative_x = 0;
					relative_y = 0;
				}
				//System.out.println(ratio);
				repaint();
			}
		});
		
		//listener for sidebar listener
		sidebarbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				slideAnimation(sidebar);
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
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
//				absolute_x = e.getX() - mouse_x + relative_x;
//				absolute_y = e.getY() - mouse_y + relative_y;
				repaint();
			}
		});
		
		
		qpanel.frombtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sidebar.start_point = qpanel.point;
				sidebar.start_field.setText(qpanel.point.getFeature());
				qpanel.setVisible(false);
			}
		});
		
		qpanel.gobtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sidebar.end_point = qpanel.point;
				sidebar.end_field.setText(qpanel.point.getFeature());
				qpanel.setVisible(false);
				sidebar.setBounds(-400,0,400,800);
				sidebar.setExtended(true);
				sidebar.setVisible(true);
				new Thread(new Runnable(){
		            @Override
		            public void run() {
		                for(int i = 0 ; i < 400 ; i ++){
		                    try {
		                        Thread.sleep(1);
		                    } catch (InterruptedException e1) {}
		                    final int position =i;
		                    SwingUtilities.invokeLater(new Runnable(){
		                        @Override
		                        public void run() {
		                        	sidebar.setBounds(-400+position,0,400,800);
		                        	sidebarbutton.setBounds(position,30,50,50);
		                        }
		                    });
		                }
		            }
		        }).start();
				sidebarbutton.setText("<<");
			}
		});
		
		qpanel.enter_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(qpanel.point.getAttributes() != 0 && qpanel.point.getAttributes() != 2)
					return;
				if(qpanel.point != null) {
					img = Toolkit.getDefaultToolkit().getImage(qpanel.point.getImageURL() + ".jpg");
					for(LocationLabel l:location_array) {
						remove(l);
					}
					location_array = new ArrayList<LocationLabel>();
					ArrayList<Point> pointarray = DB.ReadBuildingPoints(qpanel.point.getImageURL());
					building_label.setText(qpanel.point.getImageURL());
					for(Point p:pointarray) {
						if(!p.getFeature().equals("null")) {
							LocationLabel l = new LocationLabel(p.getFeature(), p.getxCooridinate(), p.getyCooridinate());
							l.setPoint(p);
							add(l);
							l.addMouseListener(new MouseListener() {
								
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
									if(qpanel.isVisible())
										qpanel.setVisible(false);
									else
										qpanel.setPoint(l.point);
								}
							});
							location_array.add(l);
						}
					}
					qpanel.setVisible(false);
				}
			}
		});
		
		qpanel.addfav.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user != null) {
					FavouritePlace fp = new FavouritePlace();
					fp.setPointID(qpanel.point.getPointID());
					fp.setUserID(user.getUserID());
					DB.SaveFavouritePlace(fp);
					qpanel.setVisible(false);
				}
			}
		});
	}
	
	private void slideAnimation(SideBar sidebar) {
		if(!sidebar.isExtended()) {
			sidebar.setBounds(-400,0,400,800);
			sidebar.setExtended(true);
			sidebar.setVisible(true);
			new Thread(new Runnable(){
	            @Override
	            public void run() {
	                for(int i = 0 ; i < 400 ; i ++){
	                    try {
	                        Thread.sleep(1);
	                    } catch (InterruptedException e1) {}
	                    final int position =i;
	                    SwingUtilities.invokeLater(new Runnable(){
	                        @Override
	                        public void run() {
	                        	sidebar.setBounds(-400+position,0,400,800);
	                        	sidebarbutton.setBounds(position,30,50,50);
	                        }
	                    });
	                }
	            }
	        }).start();
			sidebarbutton.setText("<<");
		}
		else {
			new Thread(new Runnable(){
	            @Override
	            public void run() {
	                for(int i = 0 ; i < 400 ; i ++){
	                    try {
	                        Thread.sleep(1);
	                    } catch (InterruptedException e1) {}
	                    final int position =i;
	                    SwingUtilities.invokeLater(new Runnable(){
	                        @Override
	                        public void run() {
	                        	sidebar.setBounds(-position,0,400,800);
	                        	sidebarbutton.setBounds(400-position,30,50,50);
	                        }
	                    });
	                }
	            }
	        }).start();
			sidebarbutton.setText("guide");
			sidebar.setExtended(false);
		}
	}
	
	//function to draw the graphics
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Point start = null;
		Point end = null;
		super.paintComponent(g);
		g.drawImage(img, absolute_x, absolute_y, (int)(1000 * ratio), (int)(1000 * ratio), this);
		g.setColor(new Color(173,0,255));
		
		((Graphics2D)g).setStroke(new BasicStroke(3));
		
		if(mode == VIEW_MODE) {
			for(LocationLabel l:location_array) {
				l.zoom(absolute_x, absolute_y, ratio);
			}
		}
		
		if(mode == ROUTE_MODE) {
			for(Point p:pointarray) {
				if(building_name.equals(p.getBuildName())) {
					if(start == null) {
						start = p;
						g.setColor(Color.ORANGE);
						g.fillArc(convert_x(start.getxCooridinate()) - 10, convert_y(start.getyCooridinate()) - 10, 20, 20, 0, 360);
					}
					else
						end = p;
					g.setColor(new Color(173,0,255));
					if(start != null && end != null ) {
						//g.fillArc(convert_x(start.getxCooridinate()) - 5, convert_y(start.getyCooridinate())  -5, 10, 10, 0, 360);
						drawArrowLine(g,convert_x(start.getxCooridinate()) , convert_y(start.getyCooridinate()), convert_x(end.getxCooridinate()), convert_y(end.getyCooridinate()));
					}
					start = p;
				}
				else {
					start = null;
				}
			}
			if(end !=   null) {
				//g.fillArc(arrow_x, arrow_y, 30, 30, 0, 360);
				System.out.println(arrow_x + " " + arrow_y);
				g.setColor(Color.ORANGE);
				g.fillArc(convert_x(end.getxCooridinate()) - 10, convert_y(end.getyCooridinate()) - 10, 20, 20, 0, 360);
			}
			start = null;
			end = null;
		}
	}
	
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
		int size = 6;
		g.drawLine(x1, y1, x2, y2);
		double offset_x = (x1+x2)/2;
		double offset_y = (y1+y2)/2;
		if(offset_x <0 || offset_y <0)
			return;
		if(x1 == x2) {
			int xPoints[] = {(int)(offset_x),(int)(size+offset_x),(int)(-size+offset_x)};
			int yPoints[] = {(int)(-size*Math.sqrt(3)+offset_y),(int)(offset_y),(int)(offset_y)};
			g.fillPolygon(xPoints, yPoints, 3);
			return;
		}

		//g.fillArc((int)Math.abs(x1+x2)/2-2,(int)Math.abs(y1+y2)/2-2,4,4,0,360);
		double k = Math.abs((double)(y1-y2)/(double)(x1-x2));
		System.out.println("k is " + k + " " + (y1-y2) + " " + (y1-y2)/(x1-x2));
		double cos = Math.sqrt(1/(1+k*k));
		double sin = Math.sqrt(k*k/(1+k*k));
		if(x2>=x1 && y2>=y1) {
			int xPoints[] = {(int)(size*Math.sqrt(3)*cos+offset_x),(int)(size*sin+offset_x),(int)(-size*sin+offset_x)};
			int yPoints[] = {(int)(size*Math.sqrt(3)*sin+offset_y),(int)(-size*cos+offset_y),(int)(size*cos+offset_y)};
			g.fillPolygon(xPoints, yPoints, 3);
		}
		else if(x2>x1 && y2<y1){
			int xPoints[] = {(int)(size*Math.sqrt(3)*cos+offset_x),(int)(-size*sin+offset_x),(int)(size*sin+offset_x)};
			int yPoints[] = {(int)(-size*Math.sqrt(3)*sin+offset_y),(int)(-size*cos+offset_y),(int)(size*cos+offset_y)};
			g.fillPolygon(xPoints, yPoints, 3);
		}
		else if(x2<x1 && y2<y1){
			int xPoints[] = {(int)(-size*Math.sqrt(3)*cos+offset_x),(int)(-size*sin+offset_x),(int)(size*sin+offset_x)};
			int yPoints[] = {(int)(-size*Math.sqrt(3)*sin+offset_y),(int)(size*cos+offset_y),(int)(-size*cos+offset_y)};
			g.fillPolygon(xPoints, yPoints, 3);
		}
		else {
			int xPoints[] = {(int)(-size*Math.sqrt(3)*cos+offset_x),(int)(size*sin+offset_x),(int)(-size*sin+offset_x)};
			int yPoints[] = {(int)(size*Math.sqrt(3)*sin+offset_y),(int)(size*cos+offset_y),(int)(-size*cos+offset_y)};
			g.fillPolygon(xPoints, yPoints, 3);
		}
		//g.fillArc((int)(size*Math.sqrt(3)*cos+offset_x)-5, (int)(size*Math.sqrt(3)*sin+offset_y)-5, 10, 10, 0, 360);
	}
	
	//convert the position from the database to the actual position
	private int convert_x(int x) {
		return (int)(x * ratio) + absolute_x;
	}
	
	private int convert_y(int y) {
		return (int)(y * ratio) + absolute_y;
	}
	
	public void handlePath(LinkedList<Vertex> path) {
		path_buildings = new ArrayList<String>();
		pointarray = new  ArrayList<Point>();
		ArrayList<Point> path_pointlist = new ArrayList<Point>();
		path_pointlist = DB.ReadPoints();
		//convert the point into vertex
		for(Vertex v:path) {
			for(Point p:path_pointlist) {
				if(Integer.parseInt(v.getId()) == p.getPointID())
					pointarray.add(p);
			}
		}
		//get how many maps included
		System.out.println((path_buildings==null) + " " +(pointarray == null));
		path_buildings.add(pointarray.get(0).getBuildName());
		for(Point p:pointarray) {
			boolean find = false;
			for(String s:path_buildings) {
				if(p.getBuildName().equals(s))
					find = true;
			}
			if(!find)
				path_buildings.add(p.getBuildName());
		}
		for(Point p:pointarray) {
			System.out.print(p.getFeature() + " - ");
		}
	}
	
	private void draw_location(Graphics g, int x, int y) {
		ImageIcon location = new ImageIcon("location.png");
		JLabel label = new JLabel(location);
		this.add(label);
		label.setBounds(100, 100, 40, 40);
		new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0 ; i < 100 ; i ++){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e1) {}
                    final int position =i;
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                        	label.setBounds(100 + position, 100, 40, 40);
                        }
                    });
                }
            }
        }).start();
	}
	
}
