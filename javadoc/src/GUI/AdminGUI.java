package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminGUI extends JFrame{
	final Image img = Toolkit.getDefaultToolkit().getImage("/Users/bangkura/Documents/workspace/UI/image.jpg"); 
	class point {
		int x;
		int y;
		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	class edge {
		point start;
		point end;
		public edge(point start, point end) {
			this.start = start;
			this.end = end;
		}
		public int calculateLength(){
			int squares = (int)Math.pow(start.x-end.x, 2) + (int)Math.pow(start.y-end.y, 2);
			return (int)Math.sqrt(squares);
		}
	}
	class ArcsPanel extends JPanel {
			//draw map panel of GUI
			int mode = 0;
			point new_point = null;
			point selected_point = null;
			point edge_start_point = null;
			point edge_end_point = null;
			List list = new ArrayList(); //List of Points
			List edge_list = new ArrayList();//List of Edges
			public ArcsPanel(){
//				list.add(new point(30,50));
//				list.add(new point(40,40));
//				list.add(new point(50,30));
//				list.add(new point(60,20));
//				list.add(new point(70,10));
//				list.add(new point(80,90));
			}
			protected void paintComponent(Graphics g) { 
		    super.paintComponent(g);  
		    g.setColor(Color.BLUE);  
		    this.setBounds(0,0,500,500); 
		    int xCenter = getWidth() / 2;  
		    int yCenter = getHeight() / 2;  
		    int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);  
		    int x = xCenter - radius;  
		    int y = yCenter - radius;
		    
		    g.drawImage(img, 0, 0, 500, 500, this);
		    for(int i = 0; i < edge_list.size(); ++i) {
		    	System.out.println("size " + i);
		    	edge e = (edge) edge_list.get(i);
		    	g.drawLine(e.start.x+5, e.start.y+5, e.end.x+5, e.end.y+5);
		    	System.out.println("the edge is created");
		    }
		    
		    for(int i = 0; i < list.size(); ++i) {
		    	point p = (point) list.get(i);
		        g.fillArc(p.x, p.y, 10, 10, 0, 360);  
		    }
		    g.setColor(Color.RED);
		    if(selected_point != null) {
		    	g.fillArc(selected_point.x, selected_point.y, 10, 10, 0, 360);
		    }
		    g.setColor(Color.GREEN);
		    if(edge_start_point != null) {
		    	g.fillArc(edge_start_point.x, edge_start_point.y, 10, 10, 0, 360);
		    }
		    g.setColor(Color.YELLOW);
		    if(edge_end_point != null) {
		    	g.fillArc(edge_end_point.x, edge_end_point.y, 10, 10, 0, 360);
		    }
		    g.setColor(Color.CYAN);
		    if(new_point != null) {
		    	g.fillArc(new_point.x, new_point.y, 10, 10, 0, 360);
		    }
		  }  
	}  
	public AdminGUI() throws IOException {
		JLabel l=new JLabel("lll");
		Icon icon=new ImageIcon("/Users/bangkura/Documents/workspace/UI/image.jpg");
		//在此直接创建对象
		l.setIcon(icon);
		this.setLayout(new GridLayout(1,2,4,4));
		JPanel col1 = new JPanel();
		col1.setSize(300,300);
		//col1.add(l);
		ArcsPanel arc = new ArcsPanel();

		col1.add(arc);
		JPanel col2 = new JPanel();
		JPanel col3 = new JPanel();
		col3.setLayout(new GridLayout(0,4,4,4));
		ButtonGroup group = new ButtonGroup();
		JRadioButton edge_radio = new JRadioButton("Edge");
		JRadioButton point_radio = new JRadioButton("Point");
		point_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 1;
				System.out.println("point");
				arc.edge_end_point = null;
				arc.edge_start_point = null;
				arc.repaint();
			}
		});
		edge_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 2;
				System.out.println("edge");
				arc.selected_point = null;
				arc.new_point = null;
				arc.repaint();
			}
		});
		group.add(edge_radio);
		group.add(point_radio);
		col3.add(edge_radio);
		col3.add(new JLabel(""));
		col3.add(point_radio);
		col3.add(new JLabel(""));
		col3.add(new JTextField("X-start"));
		col3.add(new JTextField("Y-start"));
		col3.add(new JTextField("X"));
		col3.add(new JTextField("Y"));
		col3.add(new JTextField("X-end"));
		col3.add(new JTextField("Y-end"));
		JButton point_add_button = new JButton("Add");
		col3.add(point_add_button);
		point_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arc.new_point != null) {
					arc.list.add(arc.new_point);
					arc.new_point = null;
					arc.repaint();
				}
			}
			
		});
		col3.add(new JButton("Remove"));
		JButton edge_add_button = new JButton("Add");
		col3.add(edge_add_button);
		edge_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("ddddddd");
				if(arc.edge_end_point != null && arc.edge_start_point != null)
					arc.edge_list.add(new edge(arc.edge_start_point,arc.edge_end_point));
				arc.repaint();
			}
			
		});
		col3.add(new JButton("Remove"));
		//col2.setLayout(new GridLayout(0,1));
		JPanel col77 = new JPanel();
		col77.setLayout(new GridLayout(0,1,4,4));
		JLabel label = new JLabel("Select the building");
		label.setBounds(0,0,500,100);
		col77.add(label);
		JComboBox box = new JComboBox();
		box.setBounds(0,0,5000,100);
		col77.add(box);
		col2.add(col77);
		col2.add(col3);
		JPanel col4 = new JPanel();
		col4.setLayout(new GridLayout(0,2,4,4));
		col4.add(new JTextArea("Here display the points"));
		col4.add(new JTextArea("Here display the edges"));
		col4.setBounds(0, 0, 500, 300);
		col2.add(col4);
		this.add(col1);
		this.add(col2);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { 
				System.out.println("clicked");
				if(arc.mode == 1) {
					boolean find = false;
					for(int i = 0; i < arc.list.size(); ++i) {
						point pp = (point) arc.list.get(i);
						if(Math.abs(pp.x - (e.getX()-5)) < 5 && Math.abs(pp.y - (e.getY()-30)) < 5) {
							find = true;
							if(arc.selected_point != null && arc.selected_point.x == pp.x && arc.selected_point.y == pp.y) {
								System.out.print(i + "----" + arc.list.size());
								arc.selected_point = null;
							}
							else
								arc.selected_point = pp;
						}
						System.out.print(find);
					}
					if(!find) {
						arc.new_point = new point(e.getX()-5, e.getY()-30);
						arc.selected_point = null;
					}
					else
						arc.new_point = null;
				}
				if(arc.mode == 2) {
					for(int i = 0; i < arc.list.size(); ++i) {
						point pp = (point) arc.list.get(i);
						if(Math.abs(pp.x - (e.getX()-5)) < 5 && Math.abs(pp.y - (e.getY()-30)) < 5) {
							if(arc.edge_start_point != null && arc.edge_start_point.x == pp.x && arc.edge_start_point.y == pp.y) {
								System.out.print(i + "----" + arc.list.size());
								arc.edge_start_point = null;
							}
							else if(arc.edge_start_point == null)
								arc.edge_start_point = pp;
							else if(arc.edge_end_point != null && arc.edge_end_point.x == pp.x && arc.edge_end_point.y == pp.y)
								arc.edge_end_point = null;
							else
								arc.edge_end_point = pp;
						}
					}
				}
				System.out.print(e.getX()+ " " + e.getY());
				arc.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/10);
	}
	public static void main(String[] args) throws IOException {
		AdminGUI h = new AdminGUI();
		h.setTitle("hello");
		h.setSize(1000,500);
		h.setVisible(true);
	}
}  