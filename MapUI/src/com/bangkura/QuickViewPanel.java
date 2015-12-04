package com.bangkura;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class QuickViewPanel extends JPanel{
	int width = 400;
	int height = 100;
	JTextArea info = new JTextArea();
	JButton enter_button = new JButton("Enter");
	JButton addfav = new JButton("Fav");
	JButton frombtn = new JButton("From");
	JButton gobtn = new  JButton("Go");
	public QuickViewPanel() {
		// TODO Auto-generated constructor stub
		this.setVisible(false);
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
	
//	public void show() {
//		new Thread(new Runnable(){
//            @Override
//            public void run() {
//            	setVisible(true);
//                for(int i = 0 ; i < 100 ; i ++){
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e1) {}
//                    final int x_location =i;
//                    SwingUtilities.invokeLater(new Runnable(){
//                        @Override
//                        public void run() {
//                            //System.out.println(40*Math.log((location+10)*100));
//                            Rectangle bounds = getBounds();
//                            Point location = bounds.getLocation();
//                            location.y = (int)(700 - 40*Math.log((x_location+10)*100));
//                            bounds.setLocation(location);
//                            setBounds(bounds);
//                        	//this.setBounds((int)(WEIDTH/2 - qpanel.width/2), (int)(700 - 40*Math.log((location+10)*100) + 270), qpanel.width, qpanel.height);
//                        }
//                    });
//                }
//            }
//        }).start();
//	}
}
