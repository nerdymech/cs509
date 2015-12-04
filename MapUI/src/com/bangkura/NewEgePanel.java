package com.bangkura;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.bangkura.Entity.Edge;

public class NewEgePanel extends JFrame{
	JTextField start_id_field = new JTextField("Start Point Id");
	JTextField end_id__field = new JTextField("End Point Id");
	JButton save_point = new JButton("Save");
	public NewEgePanel() {
		// TODO Auto-generated constructor stub
		this.setBounds(200,200,500,500);
		this.setLayout(new GridLayout(0, 1));
		this.add(start_id_field);
		this.add(end_id__field);
		this.add(save_point);
		this.setVisible(true);
		
		save_point.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DatabaseMethods DB = new DatabaseMethods();
				Edge tmpedge = new Edge();
				tmpedge.setWeight(0);
				tmpedge.setStartPointID(Integer.parseInt(start_id_field.getText()));
				tmpedge.setEndPointID(Integer.parseInt(end_id__field.getText()));
				DB.SaveEdge(tmpedge);
				tmpedge.setEndPointID(Integer.parseInt(start_id_field.getText()));
				tmpedge.setStartPointID(Integer.parseInt(end_id__field.getText()));
				DB.SaveEdge(tmpedge);
				
			}
		});
	}
}
