package com.bangkura;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import com.bangkura.Entity.Point;

public class PointLabel extends JLabel{
	int x;
	int y;
	private int size = 10;
	boolean isSelected = false;
	boolean isClicked = false;
	Color color = Color.RED;
	private Point point = null;
	
	public void zoom(int x, int y) {
		this.setBounds(x-5,y-5,size,size);
		repaint();
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public Point getPoint() {
		return this.point;
	}
	public PointLabel(Point point) {
		// TODO Auto-generated constructor stub
		this.x = point.getxCooridinate();
		this.y = point.getyCooridinate();
		this.setBounds(x-size/2,y-size/2,size,size);
		this.point = point;
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				isSelected = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				isSelected = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!isSelected && !isClicked) {
					//color = Color.RED;
					repaint();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!isSelected && !isClicked) {
					//color = Color.BLUE;
					repaint();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				isClicked = true;
				//color = Color.CYAN;
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(color);
		g.fillArc(0, 0, size, size, 0, 360);
	}
	
	
}
