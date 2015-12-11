package com.bangkura;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bangkura.Entity.Admin;
import com.bangkura.Entity.Edge;
import com.bangkura.Entity.User;

public class Main {
	// JFrame made public so the object can be called within this class
	// and in SideBar.java without passing the object
	public static JFrame jf = new JFrame("hello");
	
	public static void main(String[] args) {
		DatabaseMethods DB = new DatabaseMethods();
		ArrayList<Edge> edgelist = DB.ReadEdges();
		Edge ed=new Edge();
		for(Edge e:edgelist) {
			Edge tmpe = new Edge();
			tmpe.setEndPointID(e.getStartPointID());
			tmpe.setStartPointID(e.getEndPointID());
			tmpe.setWeight(e.getWeight());
			DB.SaveEdge(tmpe);
		}
		
			MapDisplay ex = new MapDisplay();
			AdminTool mp = new AdminTool();
			ImageIcon icon = new ImageIcon("Campus Center 1st Floor.jpg");
			JLabel map = new JLabel(icon);
			map.setSize(1000,750);
			jf.setSize(1000, 750);
			CardLayout cardlayout = new CardLayout();
			jf.setLayout(cardlayout);
			JPanel mapcontainer = new JPanel();
			mapcontainer.setLayout(null);
			mapcontainer.setSize(1000, 750);
			mapcontainer.add(ex);
			jf.add(mapcontainer);
			jf.add(map);
			jf.setVisible(true);
			ex.sidebar.admin_button.setVisible(false);
			ex.login_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					UserLoginPage userloginpanel = new UserLoginPage();
					userloginpanel.login.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							DatabaseMethods DB = new DatabaseMethods();
							ArrayList<User> usrarray = DB.ReadUser();
							ArrayList<Admin> adminarray = DB.ReadAdmin();
							for(User u:usrarray) {
								//System.out.println(u.getUserName().equals(login.psw.getPassword()) + " " + u.getPassword() + " " + new String(login.psw.getPassword()));
								if(u.getUserName().equals(userloginpanel.usr_name.getText()) && u.getPassword().equals(new String(userloginpanel.psw.getPassword()))) {
									userloginpanel.frame.setVisible(false);
									ex.setuser(u);
								}
							}
							for(Admin a:adminarray){
								if(a.getAdminName().equals(userloginpanel.usr_name.getText()) && a.getPassword().equals(new String(userloginpanel.psw.getPassword()))) {
									System.out.println("admin");
									userloginpanel.frame.setVisible(false);
									ex.sidebar.admin_button.setVisible(true);
									//ex.repaint();
								}
							}
						}
					});
				}
			});
			
			//jf.setDefaultCloseOperation();
	}
}
