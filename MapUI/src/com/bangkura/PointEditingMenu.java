package com.bangkura;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bangkura.Entity.Point;

public class PointEditingMenu extends JPanel{
	JTextField name_field = new JTextField("Room Name");
	JTextField number_field = new JTextField("Room Number");
	JTextArea info_field = new JTextArea("Introduction");
	JButton save_button = new JButton("Save");
	JButton cancel_button = new JButton("Cancel");
	Point point = null;
	
	public PointEditingMenu() {
		// TODO Auto-generated constructor stub
		this.setBounds(0,0,300,200);
		this.setVisible(false);
		this.setLayout(null);
		name_field.setBounds(10,10,135,40);
		this.add(name_field);
		number_field.setBounds(155,10,135,40);
		this.add(number_field);
		info_field.setBounds(15,60,270,80);
		this.add(info_field);
		save_button.setBounds(10,150,100,30);
		this.add(save_button);
		cancel_button.setBounds(120, 150, 100, 30);
		this.add(cancel_button);
	}
}
