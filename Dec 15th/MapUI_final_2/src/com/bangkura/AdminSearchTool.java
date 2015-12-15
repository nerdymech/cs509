package com.bangkura;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminSearchTool extends JPanel{
	JTextField text = new JTextField();
	JButton search_button = new JButton("Search");
	public AdminSearchTool() {
		// TODO Auto-generated constructor stub
		this.setBounds(300,20,350,40);
		text.setBounds(0,0,300,40);
		search_button.setBounds(300,0,50,40);
		this.setLayout(null);
		this.add(text);
		this.add(search_button);
	}
}
