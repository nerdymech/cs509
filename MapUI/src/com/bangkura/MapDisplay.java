package com.bangkura;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.bangkura.Entity.FavouritePlace;
import com.bangkura.Entity.Point;
import com.bangkura.Entity.User;

public class MapDisplay extends JPanel{
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
	JButton sidebarbutton = new JButton("guide");
	JButton login_button = new JButton("Login");
	JButton next_button = new JButton(">>");
	JButton previous_button = new JButton("<<");
	//quick view panel
	QuickViewPanel qpanel = new QuickViewPanel();
	
	//sidebar
	SideBar sidebar = new SideBar();
	
	//arraylist
	ArrayList<Point> pointarray = new ArrayList<Point>();
	
	//building name list
	ArrayList<JLabel> buildinglist = new ArrayList<JLabel>();
	
	ArrayList<String> path_buildings = null;
	
	Image img = Toolkit.getDefaultToolkit().getImage("welcome.jpg");
	
	DatabaseMethods DB = new DatabaseMethods();
	
	String building_name = null;
	
	JLabel building_label = null;
	
	void setuser(User user) {
		ArrayList<FavouritePlace> fav = DB.GetFavouritePlaceByUser(user.getUserID());
		for(FavouritePlace f:fav) {
			sidebar.favlocation.add(DB.GetPointById(f.getPointID()));
		}
	}
	
	public MapDisplay() {
		// TODO Auto-generated constructor stub
		//initial
		this.setBounds(0,0,WEIDTH,HEIGHT);
		this.setLayout(null);
		zoomin.setBounds(900,550, 50, 50);
		zoomout.setBounds(900,610, 50, 50);
		previous_button.setBounds(900,490, 50, 50);
		next_button.setBounds(900,430, 50, 50);
		previous_button.setVisible(false);
		next_button.setVisible(false);
		qpanel.setBounds((int)(WEIDTH/2 - qpanel.width/2), 700, qpanel.width, qpanel.height);
		sidebar.setBounds(0,0,400,800);
		sidebarbutton.setBounds(0,30,50,50);
		login_button.setBounds(900,20,80,50);
		login_button.setBackground(new Color(62, 119, 241));
		building_label = new JLabel("Welcome!");
		building_label.setBounds(450, 20, 200, 30);
		this.add(building_label);
		this.add(previous_button);
		this.add(next_button);
		this.add(login_button);
		this.add(sidebar);
		this.add(sidebarbutton);
		this.add(zoomin);
		this.add(zoomout);
		this.add(qpanel);
		
		
		
		sidebar.guide_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
					sidebarbutton.setText("guide");
					sidebar.setExtended(false);
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
				if(ratio <=  1.8)
					ratio += 0.2;
				//System.out.println(ratio);
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
				repaint();
			}
		});
		
		//listener for sidebar listener
		sidebarbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
	}
	//function to draw the graphics
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Point start = null;
		Point end = null;
		super.paintComponent(g);
		g.drawImage(img, absolute_x, absolute_y, (int)(1000 * ratio), (int)(1000 * ratio), this);
		g.setColor(Color.RED);
		for(Point p:pointarray) {
			if(building_name.equals(p.getBuildName())) {
				if(start == null)
					start = p;
				else
					end = p;
				if(start != null && end != null ) {
					g.fillArc(convert_x(start.getxCooridinate()) - 5, convert_y(start.getyCooridinate())  -5, 10, 10, 0, 360);
					//g.fillArc(convert_x(end.getxCooridinate()) - 5, convert_y(end.getyCooridinate()) -5, 10, 10, 0, 360);
					g.drawLine(convert_x(start.getxCooridinate()) , convert_y(start.getyCooridinate()), convert_x(end.getxCooridinate()), convert_y(end.getyCooridinate()));
				}
				start = p;
			}
			else {
				start = null;
			}
			//g.fillArc(convert_x(p.getxCooridinate()), convert_y(p.getyCooridinate()), 10, 10, 0, 360);
			//buildinglist.get(i).setBounds(convert_x((int)tmp_point.getX()), convert_y((int)tmp_point.getY()),300,30);
		}
		if(end !=   null) {
			g.fillArc(convert_x(end.getxCooridinate()) - 5, convert_y(end.getyCooridinate()) -5, 10, 10, 0, 360);
		}
		start = null;
		end = null;
		//System.out.println("finished");
		//draw_location(g, 100, 100);
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
