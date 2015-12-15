package com.bangkura;

import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.bangkura.Entity.Admin;
import com.bangkura.Entity.FavouritePlace;
import com.bangkura.Entity.User;

import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class UserLoginPage {
	JButton login = new JButton("Login");
	JButton register = new JButton("Sign up");
	JTextField usr_name = new JTextField("User Name");
	JPasswordField psw = new JPasswordField("Password");
	JTextField new_usr_name = new JTextField("User Name");
	JPasswordField new_psw = new JPasswordField("Password");
	JFrame frame = new JFrame("Login");
	public UserLoginPage() {
		// TODO Auto-generated constructor stub
		JRadioButton userbutton = new JRadioButton("User");
		JRadioButton adminbutton = new JRadioButton("Admin");
		
		// group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(userbutton);
		group.add(adminbutton);
		
		//put the radio buttons in a column in a panel.
		JLabel radioPanel = new JLabel("");
		//radioPanel.add(userbutton);
		//radioPanel.add(adminbutton);
		
		//set the login page on the left.
		JPanel UserLoginPage = new JPanel();
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setLayout(new GridLayout(4,0,10,10));
		LoginPanel.add(usr_name);
		LoginPanel.add(psw);
		LoginPanel.add(new JPanel());
		LoginPanel.add(login);
		LoginPanel.setBorder(BorderFactory.createTitledBorder("Have an account? Log In!"));
		
		//set the register page on the right.
		JPanel RegisterPanel = new JPanel();
		RegisterPanel.setLayout(new GridLayout(4,0,10,10));
		RegisterPanel.add(new_usr_name);
		RegisterPanel.add(new_psw);
		RegisterPanel.add(radioPanel);
		RegisterPanel.add(register);
		RegisterPanel.setBorder(BorderFactory.createTitledBorder("New here? Join us!"));
		
		
		UserLoginPage.setLayout(new GridLayout(1,2,50,50));
        UserLoginPage.add(LoginPanel);
        UserLoginPage.add(RegisterPanel);
        UserLoginPage.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(UserLoginPage);
        frame.setBounds(250,200,600, 300);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
		
        DatabaseMethods DB = new DatabaseMethods();
        
        register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User usr = new User();
				usr.setFavouritePlaces(new ArrayList<FavouritePlace>());
				usr.setUserName(new_usr_name.getText());
				usr.setPassword(new String(new_psw.getPassword()));
				DB.SaveUser(usr);
				radioPanel.setText("Successful!");
			}
		});
	}
}