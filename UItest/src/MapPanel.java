import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MapPanel extends JFrame{
	private ArrayList map_list = new ArrayList();
	//This function load the images of the map into the class
	final Image img = Toolkit.getDefaultToolkit().getImage("1st floor_CC.jpg"); 
	public MapPanel() throws IOException {
		this.setLayout(new GridLayout(1,2,4,4));
		ArcsPanel arc = new ArcsPanel();
		JPanel col2 = new JPanel();
		JPanel input_panel = new JPanel();
		input_panel.setLayout(new GridLayout(0,4,4,4));
		ButtonGroup group = new ButtonGroup();
		JRadioButton Edge_radio = new JRadioButton("Edge");
		JRadioButton Point_radio = new JRadioButton("Point");
		Point_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 1;
				System.out.println("Point");
				arc.Edge_end_Point = null;
				arc.Edge_start_Point = null;
				arc.repaint();
			}
		});
		Edge_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 2;
				System.out.println("Edge");
				arc.selected_Point = null;
				arc.new_Point = null;
				arc.repaint();
			}
		});
		group.add(Edge_radio);
		group.add(Point_radio);
		input_panel.add(Edge_radio);
		input_panel.add(new JLabel(""));
		input_panel.add(Point_radio);
		input_panel.add(new JLabel(""));
		input_panel.add(new JTextField("X-start"));
		input_panel.add(new JTextField("Y-start"));
		input_panel.add(new JTextField("X"));
		input_panel.add(new JTextField("Y"));
		input_panel.add(new JTextField("X-end"));
		input_panel.add(new JTextField("Y-end"));
		JButton Point_add_button = new JButton("Add");
		input_panel.add(Point_add_button);
		Point_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arc.new_Point != null) {
					arc.list.add(arc.new_Point);
					arc.new_Point = null;
					arc.repaint();
				}
			}
			
		});
		input_panel.add(new JButton("Remove"));
		JButton Edge_add_button = new JButton("Add");
		input_panel.add(Edge_add_button);
		Edge_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("ddddddd");
				if(arc.Edge_end_Point != null && arc.Edge_start_Point != null)
					arc.Edge_list.add(new Edge(arc.Edge_start_Point,arc.Edge_end_Point));
				arc.repaint();
			}
			
		});
		input_panel.add(new JButton("Remove"));
		col2.setLayout(new FlowLayout());
		JPanel col77 = new JPanel();
		col77.setLayout(new GridLayout(0,1,4,4));
		JLabel label = new JLabel("Select the building");
		//label.setBounds(0,0,500,100);
		col77.add(label);
		Vector<String> menu = new Vector();
		menu.add("Campus Center 1st Floor");
		menu.add("Campus Center 2nd Floor");
		menu.add("Campus Center 3rd Floor");
		JComboBox box = new JComboBox(menu);
		ItemListener itemListener = new ItemListener() {   
			public void itemStateChanged(ItemEvent itemEvent) {   
				int state = itemEvent.getStateChange();   
				if(state == ItemEvent.SELECTED) {   
					System.out.println(itemEvent.getItem());    
					arc.loadImage((String)itemEvent.getItem());
					arc.repaint();
				}
			}   
		    };   
		box.addItemListener(itemListener);  
		col77.add(box);
		col2.add(col77);
		col2.add(input_panel);
		this.add(arc);
		this.add(col2);
		arc.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { 
				System.out.println("dfsdf");
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
	}
}