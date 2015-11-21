import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DatabaseMethods;
import Entity.Point;

public class UserMapTool extends JPanel{
	Point sp = null;
	Point ep = null;
	Image img = Toolkit.getDefaultToolkit().getImage("Campus Center 1st 2Floor.jpg");
	public LinkedList<Vertex> path = null;
	public void loadImage(String image_name) {
		img = Toolkit.getDefaultToolkit().getImage( image_name + ".jpg");
	}
	
	protected void paintComponent(Graphics g) {
		ArrayList<Point> point_array = new ArrayList<Point>();
		DatabaseMethods DB = new DatabaseMethods();
		point_array = DB.ReadPoints();
		super.paintComponent(g);
		//this.setBounds(0,0,500,500);
		g.drawImage(img, 0, 0, 500, 500, this);
		Point start = null;
		Point end = null;
		Point tmp = null;
		if(sp != null) {
			System.out.println("find" + sp.getxCooridinate() + " " + sp.getyCooridinate());
			g.fillArc(sp.getxCooridinate(), sp.getyCooridinate(), 10, 10, 0, 360);
		}
		if(ep != null)
			g.fillArc(ep.getxCooridinate(), ep.getyCooridinate(), 10, 10, 0, 360);
		if(path != null) {
			for(Vertex v:path) {
				for(Point p:point_array) {
					if(v.getId().equals(Integer.toString(p.getPointID()))) {
						start = end;
						end = p;
						g.fillArc(p.getxCooridinate(), p.getyCooridinate(), 10, 10, 0, 360);
						if(start != null && end != null) {
							g.drawLine(start.getxCooridinate()+5, start.getyCooridinate()+5, end.getxCooridinate()+5, end.getyCooridinate()+5);
						}
					}
				}
				System.out.print("the point is " + v.getId());
			}
		}
	}
}
