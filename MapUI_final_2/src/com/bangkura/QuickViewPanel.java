package com.bangkura;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.bangkura.Entity.Point;

public class QuickViewPanel extends JPanel{
	int width = 400;
	int height = 100;
	Point point;
	JTextArea info = new JTextArea();
	JButton enter_button = new JButton("Enter");
	JButton addfav = new JButton("Fav");
	JButton frombtn = new JButton("From");
	JButton gobtn = new  JButton("Go");
	public QuickViewPanel() {
		// TODO Auto-generated constructor stub
		this.setVisible(false);
		this.setBounds(0,0,400,100);
		this.setBackground(new Color(220, 220, 220));
		this.setLayout(null);
		ImageIcon icon = new ImageIcon("quick_view_icon.jpg");
		JLabel iconlabel = new JLabel(icon);
		iconlabel.setBounds(10,10,80,80);
		this.add(iconlabel);
		enter_button.setBounds(310,10,80,80);
		this.add(enter_button);
		addfav.setBounds(240,10,60,20);
		this.add(addfav);
		frombtn.setBounds(240,38,60,20);
		this.add(frombtn);
		gobtn.setBounds(240,65,60,20);
		this.add(gobtn);
		info.setBounds(100, 10, 140,80);
		info.setBackground(new Color(220, 220, 220));
		info.setText("Building\ndescription");
		this.add(info);
	}
	
	public void setPoint(Point p) {
		point = p;
		this.setVisible(true);
		info.setText(p.getFeature());
	}
}
