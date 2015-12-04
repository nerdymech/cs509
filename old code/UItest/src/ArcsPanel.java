import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class ArcsPanel extends JPanel {
	public final int DEFAULT_MODE = 0;
	public final int EDIT_POINT = 1;
	public final int EDIT_EDGE = 2;
	
	private final Color POINT_COLOR = Color.BLUE;
	private final Color SELECTED_POINT_COLOR = Color.RED;
	private final Color SELECTED_START_POINT_COLOR = Color.GREEN;
	private final Color SELECTED_END_POINT_COLOR = Color.YELLOW;
	private final Color EDGE_COLOR = Color.YELLOW;
	private final Color NEW_EDGE_COLOR = Color.CYAN;
	
	Image img = Toolkit.getDefaultToolkit().getImage("1st floor_CC.jpg");
	
	int mode = DEFAULT_MODE;
	Point new_Point = null;
	Point selected_Point = null;
	Point Edge_start_Point = null;
	Point Edge_end_Point = null;
	
	List image_list = new ArrayList();
	List list = new ArrayList();
	List Edge_list = new ArrayList();
	
	public void loadImage(String image_name) {
		img = Toolkit.getDefaultToolkit().getImage("/Users/bangkura/Documents/workspace/UI/" + image_name + ".jpg");
	}
	public ArcsPanel(){
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
						Point pp = (Point) list.get(i);
						if(Math.abs(pp.x - (e.getX())) < 5 && Math.abs(pp.y - (e.getY())) < 5) {
							find = true;
							if(selected_Point != null && selected_Point.x == pp.x && selected_Point.y == pp.y) {
								selected_Point = null;
							}
							else
								selected_Point = pp;
						}
						System.out.print(find);
					}
					if(!find) {
						new_Point = new Point(e.getX(), e.getY());
						selected_Point = null;
					}
					else
						new_Point = null;
				}
				if(mode == 2) {
					for(int i = 0; i < list.size(); ++i) {
						Point pp = (Point) list.get(i);
						if(Math.abs(pp.x - (e.getX())) < 5 && Math.abs(pp.y - (e.getY())) < 5) {
							if(Edge_start_Point != null && Edge_start_Point.x == pp.x && Edge_start_Point.y == pp.y) {
								System.out.print(i + "----" + list.size());
								Edge_start_Point = null;
							}
							else if(Edge_start_Point == null)
								Edge_start_Point = pp;
							else if(Edge_end_Point != null && Edge_end_Point.x == pp.x && Edge_end_Point.y == pp.y)
								Edge_end_Point = null;
							else
								Edge_end_Point = pp;
						}
					}
				}
				System.out.print(e.getX()+ " " + e.getY());
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
		super.paintComponent(g);
		//this.setBounds(0,0,500,500);
		g.drawImage(img, 0, 0, 500, 500, this);
		
		//set the default color of the point
		g.setColor(POINT_COLOR); 		
		
		for(int i = 0; i < Edge_list.size(); ++i) {
		    Edge e = (Edge) Edge_list.get(i);
		    g.drawLine(e.start.x+5, e.start.y+5, e.end.x+5, e.end.y+5);
		}
		    
		for(int i = 0; i < list.size(); ++i) {
			Point p = (Point) list.get(i);
		    g.fillArc(p.x, p.y, 10, 10, 0, 360);  
		}
		    
		g.setColor(SELECTED_START_POINT_COLOR);
		if(selected_Point != null) {
		    g.fillArc(selected_Point.x, selected_Point.y, 10, 10, 0, 360);
		}
		    
		g.setColor(SELECTED_END_POINT_COLOR);
		if(Edge_start_Point != null) {
			g.fillArc(Edge_start_Point.x, Edge_start_Point.y, 10, 10, 0, 360);
		}
		    
		g.setColor(EDGE_COLOR);
		if(Edge_end_Point != null) {
			g.fillArc(Edge_end_Point.x, Edge_end_Point.y, 10, 10, 0, 360);
		}
		    
		g.setColor(NEW_EDGE_COLOR);
		if(new_Point != null) {
			g.fillArc(new_Point.x, new_Point.y, 10, 10, 0, 360);
		}
	}  
}  