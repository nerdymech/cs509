package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPageGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainPageGUI window = new MainPageGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Button button = new Button("Admin");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("You Clicked the Admin Button");
				//Admin_class adminClick = new Admin_class();
				//adminClick.open();
				
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 21));
		button.setBackground(Color.BLUE);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(49, 121, 139, 72);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("User");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("You Clicked the User Button");
				//create instance of the MapPageGUI in order to open page
				MapPageGUI userClick = new MapPageGUI();
				//open the GUI
				userClick.open();
			}
		});
		button_1.setFont(new Font("Dialog", Font.PLAIN, 22));
		button_1.setBackground(Color.BLUE);
		button_1.setForeground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(224, 121, 145, 72);
		frame.getContentPane().add(button_1);
		
		JPanel panel = new JPanel();
		//panel.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("wpiBanner.jpg"))));
		panel.setBounds(0, 0, 435, 95);
		frame.getContentPane().add(panel);
		
}
}