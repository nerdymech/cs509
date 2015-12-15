package com.bangkura;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JFrame{
	JTextField usr_name = new JTextField("User Name");
	JPasswordField psw = new JPasswordField("Password");
	JButton login = new JButton("Login");
	public LoginPanel() {
		JPanel input_panel = new JPanel();
		this.add(new JPanel());
		input_panel.setLayout(new GridLayout(0,1,40,40));
		input_panel.setSize(100,0);
		input_panel.add(usr_name);
		input_panel.add(psw);
		input_panel.add(login);
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 3));
		container.add(new JPanel());
		container.add(input_panel);
		this.setLayout(new GridLayout(0, 1,50,50));
		this.add(container);
		this.add(new JPanel());
	}
}
