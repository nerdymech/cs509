package com.bangkura;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bangkura.Entity.Point;

public class PointEditingMenu extends JPanel{
	JTextField name_field = new JTextField("Room Name");
	JTextField number_field = new JTextField("Room Number");
	JTextField info_field = new JTextField("Image Url");
	final String[] attrs = {"building","location","stair","null"};
	JComboBox<String> attrs_field = new JComboBox<String>(attrs);
	JButton delete_button = new JButton("Delete");
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
		info_field.setBounds(10,100,280,40);
		this.add(info_field);
		attrs_field.setBounds(10,60,280,30);
		this.add(attrs_field);
		save_button.setBounds(10,160,100,30);
		this.add(save_button);
		delete_button.setBounds(10,160,100,30);
		this.add(delete_button);
		cancel_button.setBounds(120, 160, 100, 30);
		this.add(cancel_button);
	}
	
	public void setPoint(Point p) {
		name_field.setText(p.getFeature());
		number_field.setText(p.getRoomNum());
		info_field.setText(p.getImageURL());
		attrs_field.setSelectedIndex(p.getAttributes());
		delete_button.setVisible(true);
		save_button.setVisible(false);
		this.point = p;
	}
	
	public void newPoint() {
		attrs_field.setSelectedIndex(1);
		name_field.setText("Room Name");
		number_field.setText("Room Number");
		info_field.setText("Image Url");
		attrs_field.setSelectedIndex(0);
		delete_button.setVisible(false);
		save_button.setVisible(true);
	}
}
