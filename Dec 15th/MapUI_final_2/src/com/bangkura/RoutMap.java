package com.bangkura;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bangkura.Entity.Point;

public class RoutMap extends JLabel{
	String building_name = null;
	ArrayList<Point> points = null;
	public RoutMap(String map_name, ArrayList<Point> points) {
		// TODO Auto-generated constructor stub
		this.building_name = map_name;
		this.setBounds(0,0,1000,1000);
		this.setIcon(new ImageIcon(map_name+".jpg"));
		this.points = points;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Point start = null;
		Point end = null;
		super.paintComponent(g);
		for(Point p:points) {
			if(building_name.equals(p.getBuildName())) {
				if(start == null)
					start = p;
				else
					end = p;
				if(start != null && end != null ) {
					g.fillArc(start.getxCooridinate() - 5, start.getyCooridinate() - 5, 10, 10, 0, 360);
					g.fillArc(end.getxCooridinate() - 5, end.getyCooridinate() - 5, 10, 10, 0, 360);
					g.drawLine(start.getxCooridinate(), start.getyCooridinate(), end.getxCooridinate(), end.getyCooridinate());
				}
				start = p;
			}
			else {
				start = null;
			}
		}
	}
}
