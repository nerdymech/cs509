package com.bangkura;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.bangkura.Entity.Point;

public class SideBar extends JPanel{
	boolean extended = false;
	JPanel panel = new JPanel();
	ArrayList<Point> dictionary = new ArrayList<Point>();
	ArrayList<Point> favlocation = new ArrayList<Point>();
	JPanel suggestionpanel = new JPanel();
	JTextField start_field = new JTextField();
	JTextField end_field = new JTextField();
	JButton guide_button = new JButton("guide");
	JButton admin_button = new JButton("Admin Tool");
	DatabaseMethods DB = new DatabaseMethods();
	Point start_point = null;
	Point end_point = null;
	LinkedList<Vertex> path = null;
	int cursor = 0;
	
	public void clear() {
		start_field.setText("");
		end_field.setText("");
		start_point=null;
		end_point=null;
	}
	
	public SideBar() {
		// TODO Auto-generated constructor stub
		//guide_button.setIcon(new ImageIcon("guide.png"));
		dictionary = DB.ReadPoints();
		this.setLayout(null);
		this.setVisible(false);
		panel.setBounds(0,0,400,150);
		panel.setBackground(new Color(68, 128, 244));
		this.setBackground(new Color(206, 222, 241));
		start_field.setBounds(20,30,280,40);
		end_field.setBounds(20,80,280,40);
		guide_button.setBounds(310, 50, 70, 50);
		suggestionpanel.setBounds(0,150,400,400);
		suggestionpanel.setBackground(new Color(206, 222, 241));
		admin_button.setBounds(45, 600, 300, 50);
		suggestionpanel.setLayout(null);
		
		this.add(admin_button);
		this.add(start_field);
		this.add(end_field);
		this.add(guide_button);
		this.add(suggestionpanel);
		this.add(panel);
		
		admin_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdminTool mp = new AdminTool();
				JFrame jf = new JFrame("Admin Tool");
				jf.setBounds(150,0,1000, 750);
				jf.setVisible(true);
				jf.add(mp);
			}
		});
		
		start_field.addMouseListener(new MouseListener() {
			
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
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(start_field.getText().equals("")) {
					cursor = 0;
					showFavLocations(favlocation, start_field);
				}
			}
		});
		
		guide_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//PathDisplay pathDisplay = new PathDisplay();
				
			}
		});
		
		start_field.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("update");
				checkandShowSuggestions(start_field,false);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("update");
				checkandShowSuggestions(start_field,false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("update");
				checkandShowSuggestions(start_field,false);
			}
		});
		
		end_field.addMouseListener(new MouseListener() {
			
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
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(end_field.getText().equals("")) {
					cursor = 1;
					showFavLocations(favlocation, end_field);
				}
			}
		});
		
		end_field.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				checkandShowSuggestions(end_field, true);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				checkandShowSuggestions(end_field, true);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				checkandShowSuggestions(end_field, true);
			}
		});
	}
	
	
	
	public boolean isExtended() {
		return extended;
	}
	
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	
	private void checkandShowSuggestions(JTextField text, boolean mode) {
		String wordtyped = text.getText();
		ArrayList<Point> matched = new ArrayList<Point>();
		//System.out.println(wordtyped.length());
		if(text.getText().length() !=0) {
			for(Point s:dictionary) {
				if(wordtyped.equals(s.getFeature().substring(0, Math.min(text.getText().length(),s.getFeature().length()))) || wordtyped.equals(s.getRoomNum().substring(0, Math.min(text.getText().length(),s.getRoomNum().length()))) || wordtyped.equals(s.getBuildName().substring(0, Math.min(text.getText().length(),s.getBuildName().length())))) {
					//System.out.println(wordtyped);
					matched.add(s);
				}
			}
			if(!matched.isEmpty())
				showSuggestions(matched,text,mode);
		}
		else {
			suggestionpanel.removeAll();
			suggestionpanel.repaint();
		}
	}
	
	private void showSuggestions(ArrayList<Point> matched, JTextField textField, boolean mode) {
		suggestionpanel.removeAll();
		for(int i = 0; i < matched.size(); ++i) {
			JLabel tmpLabel = new JLabel(matched.get(i).getBuildName() + ", " + matched.get(i).getFeature() + ", " + matched.get(i).getRoomNum() + ", " + matched.get(i).getPointID());
			tmpLabel.setBounds(10,10 + 50 * i,380,40);
			tmpLabel.setBackground(Color.WHITE);
			tmpLabel.setOpaque(true);
			tmpLabel.addMouseListener(new MouseListener() {
				
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
					tmpLabel.setBackground(Color.WHITE);
					tmpLabel.repaint();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					tmpLabel.setBackground(Color.GRAY);
					tmpLabel.repaint();
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					textField.setText(tmpLabel.getText());
					String[] pointinfo = tmpLabel.getText().split(", ");
					ArrayList<Point> tmparray = DB.ReadBuildingPoints(pointinfo[0]);
					if(mode == false) {
						for(Point p:tmparray) {
							if(p.getPointID() == Integer.parseInt(pointinfo[3]))
								start_point = p;
						}
					}
					if(mode == true) {
						for(Point p:tmparray) {
							if(p.getPointID() == Integer.parseInt(pointinfo[3]))
								end_point = p;
						}
					}
					suggestionpanel.removeAll();
					suggestionpanel.repaint();
					//if(start_point != null)
					//System.out.println(start_point.getFeature());
				}
			});
			suggestionpanel.add(tmpLabel);
		}
		
		suggestionpanel.repaint();
	}
	
	private void showFavLocations(ArrayList<Point> locations, JTextField textField) {
		suggestionpanel.removeAll();
		if(locations.size() == 0)
			return;
		for(int i = 0; i < locations.size(); ++i) {
			JLabel tmpLabel = new JLabel(locations.get(i).getBuildName() + ", " + locations.get(i).getFeature() + ", " + locations.get(i).getRoomNum() + ", " + locations.get(i).getPointID());
			tmpLabel.setBounds(10,10 + 50 * i,380,40);
			tmpLabel.setBackground(Color.WHITE);
			tmpLabel.setOpaque(true);
			tmpLabel.addMouseListener(new MouseListener() {
				
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
					tmpLabel.setBackground(Color.WHITE);
					tmpLabel.repaint();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					tmpLabel.setBackground(Color.GRAY);
					tmpLabel.repaint();
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					textField.setText(tmpLabel.getText());
					String[] pointinfo = tmpLabel.getText().split(", ");
					ArrayList<Point> tmparray = DB.ReadBuildingPoints(pointinfo[1]);
					if(cursor == 0) {
						for(Point p:tmparray) {
							if(p.getFeature().equals(pointinfo[0]))
								start_point = p;
						}
					}
					if(cursor == 1) {
						for(Point p:tmparray) {
							if(p.getFeature().equals(pointinfo[0]))
								end_point = p;
						}
					}
					suggestionpanel.removeAll();
					suggestionpanel.repaint();
				}
			});
			suggestionpanel.add(tmpLabel);
		}
		
		suggestionpanel.repaint();
	}
}
