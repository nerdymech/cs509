package com.bangkura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bangkura.Entity.Admin;
import com.bangkura.Entity.Edge;
import com.bangkura.Entity.User;

public class GPS {
	public GPS() {
		// TODO Auto-generated constructor stub
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
		
		JFrame jf = new JFrame("WPI GPS App");
		jf.setBounds(150,0,1000, 750);
		JPanel mapcontainer = new JPanel();
		mapcontainer.setLayout(null);
		mapcontainer.setSize(1000, 750);
		mapcontainer.add(ex);
		jf.add(mapcontainer);
		jf.setVisible(true);
		ex.sidebar.admin_button.setVisible(false);
		ex.login_label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ex.login_label.setIcon(new ImageIcon("login.png"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				ex.login_label.setIcon(new ImageIcon("login_focus.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
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
								userloginpanel.frame.setVisible(false);
								ex.sidebar.admin_button.setVisible(true);
								//ex.repaint();
							}
						}
					}
				});
			}
		});

	}
}
