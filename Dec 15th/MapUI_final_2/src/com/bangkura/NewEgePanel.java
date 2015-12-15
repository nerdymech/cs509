package com.bangkura;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.xml.crypto.Data;

import com.bangkura.Entity.Edge;
import com.bangkura.Entity.Point;

public class NewEgePanel extends JFrame{
	JButton save_point = new JButton("Save");
	public NewEgePanel() {
		// TODO Auto-generated constructor stub
		Vector<String> items_1 = new Vector<String>();
		Vector<String> items_2 = new Vector<String>();
		DatabaseMethods DB = new DatabaseMethods();
		ArrayList<Point> all_list = DB.ReadPoints();
		ArrayList<Point> edge_point = new ArrayList<Point>();
		for(Point p:all_list) {
			if(p.getAttributes() == 0 || p.getAttributes() == 2) {
				String tmpstr = "" + p.getPointID() + ", " + p.getBuildName() + ", " + p.getFeature() + ", " + p.getRoomNum();
				items_1.add(tmpstr);
				items_2.add(tmpstr);
			}
		}
		JComboBox<String> start_field = new JComboBox<String>(items_1);
		JComboBox<String> end_field = new JComboBox<String>(items_2);
		this.setBounds(200,200,500,300);
		this.setLayout(new GridLayout(0, 1));
		this.add(start_field);
		this.add(end_field);
		this.add(save_point);
		this.setVisible(true);
		
		save_point.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tmpstr = (String)start_field.getSelectedItem();
				System.out.println(tmpstr);
				String[] startpoint_info = tmpstr.split(", ");
				int start_id = Integer.parseInt(startpoint_info[0]);
				tmpstr = (String)end_field.getSelectedItem();
				System.out.println(tmpstr);
				String[] endpoint_info = tmpstr.split(", ");
				int end_id = Integer.parseInt(endpoint_info[0]);
				System.out.println(start_id + " " + end_id);
				if(start_id != end_id) {
					Edge e1 = new Edge();
					e1.setWeight(0);
					e1.setStartPointID(start_id);
					e1.setEndPointID(end_id);
					System.out.println("saving");
					DB.SaveEdge(e1);
					Edge e2 = new Edge();
					e2.setWeight(0);
					e2.setStartPointID(end_id);
					e2.setEndPointID(start_id);
					DB.SaveEdge(e2);
				}
			}
		});
	}
}
