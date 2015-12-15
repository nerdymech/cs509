package com.bangkura;

import java.awt.CardLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Handler;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.crypto.Data;

import com.bangkura.Entity.Point;

public class PathDisplay extends JFrame{
	JButton left_button = new JButton("<<");
	JButton right_button = new JButton(">>");
	JPanel map_panel = new JPanel();
	CardLayout cardLayout = new CardLayout();
	public PathDisplay() {
		// TODO Auto-generated constructor stub
		left_button.setBounds(0,325,50,50);
		right_button.setBounds(950,325,50,50);
		map_panel.setBounds(0,0,1000,750);
		map_panel.setLayout(cardLayout);
		left_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.previous(map_panel);
				repaint();
			}
		});
		right_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.next(map_panel);
				repaint();
			}
		});
		this.setLayout(null);
		this.add(left_button);
		this.add(right_button);
		this.add(map_panel);
		this.setBounds(0,0,1000,750);
		this.setVisible(true);
	}
	
	public void handlePath(LinkedList<Vertex> path) {
		ArrayList<String> buildings = new ArrayList<String>();
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> pointlist = new ArrayList<Point>();
		DatabaseMethods DB = new DatabaseMethods();
		pointlist = DB.ReadPoints();
		//convert the point into vertex
		for(Vertex v:path) {
			for(Point p:pointlist) {
				if(Integer.parseInt(v.getId()) == p.getPointID())
					points.add(p);
			}
		}
		//get how many maps included
		buildings.add(points.get(0).getBuildName());
		for(Point p:points) {
			System.out.println(p.getBuildName());
			boolean find = false;
			for(String s:buildings) {
				if(p.getBuildName().equals(s))
					find = true;
			}
			if(!find)
				buildings.add(p.getBuildName());
		}
		for(String s:buildings) {
			map_panel.add(new RoutMap(s, points));
			System.out.println(s + ".jpg");
		}
	}
}
