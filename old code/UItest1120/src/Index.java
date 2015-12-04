import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Index extends JFrame{
	public Index() {
		//this.setLayout(new BorderLayout());
		JLabel banner = new JLabel();
		Image icon_image = Toolkit.getDefaultToolkit().getImage("BANNER.jpg");
		ImageIcon Icon = new ImageIcon(icon_image);
		JPanel input_panel = new JPanel();
		banner.setIcon(Icon);
		this.add(banner);
		JButton admin_btn = new JButton("Admin Tool");
		JButton usr_btn = new JButton("User Tool");
		input_panel.setLayout(new GridLayout(0,1,40,40));
		input_panel.setSize(100,0);
		input_panel.add(usr_btn);
		input_panel.add(admin_btn);
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 3));
		container.add(new JPanel());
		container.add(input_panel);
		this.setLayout(new GridLayout(0, 1,50,50));
		this.add(container);
		this.add(new JPanel());
		admin_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MapPanel h;
				try {
					h = new MapPanel();
					h.setTitle("hello");
					h.setBounds(100, 100, 1000, 500);
					h.setVisible(true);
					h.setResizable(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		usr_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserUI U = new UserUI();
				U.setTitle("user");
				U.setBounds(100, 100, 1000, 500);
				U.setVisible(true);
			}
		});
	}
}
