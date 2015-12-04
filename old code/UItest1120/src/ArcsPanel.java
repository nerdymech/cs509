import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.xml.bind.DataBindingException;

import Controller.DatabaseMethods;
import Entity.Edge;
import Entity.Point;

class ArcsPanel extends JPanel {
	public final int DEFAULT_MODE = 0;
	public final int EDIT_POINT = 1;
	public final int EDIT_EDGE = 2;
	
	private final Color POINT_COLOR = Color.BLUE;
	private final Color SELECTED_POINT_COLOR = Color.RED;
	private final Color SELECTED_START_POINT_COLOR = Color.GREEN;
	private final Color SELECTED_END_POINT_COLOR = Color.YELLOW;
	private final Color EDGE_COLOR = Color.DARK_GRAY;
	private final Color NEW_EDGE_COLOR = Color.BLACK;
	private final Color NEW_POINT_COLOR = Color.CYAN;
	
	Image img = Toolkit.getDefaultToolkit().getImage("Campus Center 1st Floor.jpg");
	
	int mode = DEFAULT_MODE;
	Point new_Point = null;
	Point selected_Point = null;
	Point Edge_start_Point = null;
	Point Edge_end_Point = null;
	
	List image_list = new ArrayList();
	ArrayList<Point> list = new ArrayList();
	ArrayList<Edge> Edge_list = new ArrayList();
	
	String building = "Campus Center 1st Floor";
	
	public void loadImage(String image_name) {
		img = Toolkit.getDefaultToolkit().getImage(image_name + ".jpg");
	}
	public ArcsPanel(){
		DatabaseMethods DB = new DatabaseMethods();
		list = DB.ReadBuildingPoints("Campus Center 1st Floor");
//				list.add(new Point(30,50));
//				list.add(new Point(40,40));
//				list.add(new Point(50,30));
//				list.add(new Point(60,20));
//				list.add(new Point(70,10));
//				list.add(new Point(80,90));
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mode == EDIT_POINT) {
					boolean find = false;
					for(int i = 0; i < list.size(); ++i) {
						Point present_point = (Point) list.get(i);
						//System.out.println("existing" + (present_point.getxCooridinate() - e.getX()) + " " + (present_point.getyCooridinate() - e.getY()));
						//System.out.println("existing" + present_point.getxCooridinate() + " " + present_point.getxCooridinate());
						if(Math.abs(present_point.getxCooridinate() - (e.getX())) < 5 && Math.abs(present_point.getyCooridinate() - (e.getY())) < 5) {
							find = true;
							if(selected_Point != null && selected_Point.getxCooridinate() == present_point.getxCooridinate() && selected_Point.getyCooridinate() == present_point.getyCooridinate()) {
								selected_Point = null;
							}
							else
								selected_Point = present_point;
							//System.out.println("the point found is: " + selected_Point.x + " " + selected_Point.y);
						}
				
					}
					if(!find) {
						new_Point = new Point(e.getX(), e.getY());
						selected_Point = null;
					}
					else
						new_Point = null;
					repaint();
				}
				if(mode == EDIT_EDGE) {
					for(int i = 0; i < list.size(); ++i) {
						Point present_point = (Point) list.get(i);
						if(Math.abs(present_point.getxCooridinate() - (e.getX())) < 5 && Math.abs(present_point.getyCooridinate() - (e.getY())) < 5) {
							if(Edge_start_Point != null && Edge_start_Point.getxCooridinate() == present_point.getxCooridinate() && Edge_start_Point.getyCooridinate() == present_point.getyCooridinate()) {
								Edge_start_Point = null;
							}
							else if(Edge_start_Point == null)
								Edge_start_Point = present_point;
							else if(Edge_end_Point != null && Edge_end_Point.getxCooridinate() == present_point.getxCooridinate() && Edge_end_Point.getyCooridinate() == present_point.getyCooridinate())
								Edge_end_Point = null;
							else
								Edge_end_Point = present_point;
						}
					}
				}
				//System.out.println("mouse " + e.getX()+ " " + e.getY());
				repaint();
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
				
			}
		});
	}
	protected void paintComponent(Graphics g) { 
		DatabaseMethods DB2 = new DatabaseMethods();
		list = DB2.ReadBuildingPoints(building);
		super.paintComponent(g);
		Edge_list = new ArrayList<Edge>();
		//this.setBounds(0,0,500,500);
		g.drawImage(img, 0, 0, 500, 500, this);
		
		//set the default color of the point
		g.setColor(EDGE_COLOR); 		
		DatabaseMethods DB = new DatabaseMethods();
		ArrayList<Edge> all_edge = new ArrayList<Edge>();
		all_edge = DB.ReadEdges();
		for(Edge e:all_edge) {
			//System.out.println("the edge" + e.getStartPointID());
			for(Point p:list) {
				if(p.getPointID() == e.getStartPointID()) {
					Edge_list.add(e);
					//System.out.println("selected " + e.getStartPointID());
				}
			}
		}
		System.out.println("the size of list is" + Edge_list.size());
		for(Edge e:Edge_list) {
			//System.out.println("edge: " + e.getStartPointID() + " " + e.getEndPointID());
			//System.out.println("id is " + DB.GetPointById(33940).getPointID());
		    g.drawLine(DB.GetPointById(e.getStartPointID()).xCooridinate, DB.GetPointById(e.getStartPointID()).yCooridinate, DB.GetPointById(e.getEndPointID()).xCooridinate, DB.GetPointById(e.getEndPointID()).yCooridinate);
		}
		    //System.out.println("");
		g.setColor(POINT_COLOR);
		for(Point np:list) {
			//System.out.println(np.getxCooridinate() + " " + np.getyCooridinate());
		    g.fillArc(np.xCooridinate - 3, np.yCooridinate - 3, 6, 6, 0, 360); 
		}
		System.out.println("");
		g.setColor(SELECTED_POINT_COLOR);
		if(selected_Point != null) {
		    g.fillArc(selected_Point.getxCooridinate() - 3, selected_Point.getyCooridinate() - 3, 6, 6, 0, 360);
		}
		    
		g.setColor(SELECTED_START_POINT_COLOR);
		if(Edge_start_Point != null) {
			g.fillArc(Edge_start_Point.getxCooridinate() - 3, Edge_start_Point.getyCooridinate() - 3, 6, 6, 0, 360);
		}
		    
		g.setColor(SELECTED_END_POINT_COLOR);
		if(Edge_end_Point != null) {
			g.fillArc(Edge_end_Point.getxCooridinate() - 3, Edge_end_Point.getyCooridinate() - 3, 6, 6, 0, 360);
		}
		    
		g.setColor(NEW_POINT_COLOR);;
		if(new_Point != null) {
			g.fillArc(new_Point.getxCooridinate() - 3, new_Point.getyCooridinate() - 3, 6, 6, 0, 360);
		}
	}  
}  