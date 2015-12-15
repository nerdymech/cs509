package com.bangkura;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bangkura.Entity.Point;

public class LocationLabel extends JLabel{
	Point point = null;
	int x;
	int y;
	public void setPoint(Point point) {
		this.point = point;
		
	}
	public LocationLabel(String  str, int x, int y) {
		// TODO Auto-generated constructor stub
		setForeground(Color.RED);
		setFont(new Font("sanserif",1,15));
		this.x = x;
		this.y = y;
		this.setText(str);
		this.setBounds(x,y,200,20);
		this.addMouseListener(new MouseListener() {
			
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
				setFont(new Font("sanserif",1,15));
				setForeground(Color.RED);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setFont(new Font("sanserif",1,24));
				setForeground(Color.ORANGE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void zoom(int absolute_x, int absolute_y, double ratio) {
		this.setBounds((int)(x * ratio) + absolute_x,(int)(y * ratio) + absolute_y,200,20);
		repaint();
	}
}
