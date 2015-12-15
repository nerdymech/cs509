package com.bangkura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminButtonPanel extends JPanel{
	final int POINT_MODE = 0;
	final int EDGE_MODE = 1;
	final int STAIR_MODE = 2;
	final int ENTRANCE_MODE = 4;
	public int mode;
	JButton point_mode_button = new JButton("P");
	JButton edge_mode_button = new JButton("E");
	JButton stair_mode_button = new JButton("S");
	JButton entrance_mode_button = new JButton("N");
	public AdminButtonPanel() {
		// TODO Auto-generated constructor stub
		mode = POINT_MODE;
		point_mode_button.setSelected(true);
		this.setBounds(300,60,200,50);
		this.setLayout(null);
		point_mode_button.setBounds(0,0,50,50);
		edge_mode_button.setBounds(50,0,50,50);
		stair_mode_button.setBounds(100,0,50,50);
		entrance_mode_button.setBounds(150,0,50,50);
		this.add(point_mode_button);
		this.add(edge_mode_button);
		this.add(stair_mode_button);
		this.add(entrance_mode_button);
		point_mode_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = POINT_MODE;
				point_mode_button.setSelected(true);
				edge_mode_button.setSelected(false);
				stair_mode_button.setSelected(false);
				entrance_mode_button.setSelected(false);
			}
		});
		edge_mode_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = EDGE_MODE;
				point_mode_button.setSelected(false);
				edge_mode_button.setSelected(true);
				stair_mode_button.setSelected(false);
				entrance_mode_button.setSelected(false);
			}
		});
		stair_mode_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = STAIR_MODE;
				point_mode_button.setSelected(false);
				edge_mode_button.setSelected(false);
				stair_mode_button.setSelected(true);
				entrance_mode_button.setSelected(false);
			}
		});
		entrance_mode_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = ENTRANCE_MODE;
				point_mode_button.setSelected(false);
				edge_mode_button.setSelected(false);
				stair_mode_button.setSelected(false);
				entrance_mode_button.setSelected(true);
			}
		});
	}
}
