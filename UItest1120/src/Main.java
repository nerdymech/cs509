import java.awt.List;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controller.DatabaseMethods;
import Entity.Edge;
import Entity.Point;

public class Main {
	public static void main(String[] args) throws IOException {
		DatabaseMethods DB = new DatabaseMethods();
//		Edge e = new Edge();
//		e.setEndPointID(222);
//		e.setStartPointID(42323);
//		e.setWeight(2312);
//		DB.SaveEdge(e);
//		e.setStartPointID(2223);
//		DB.SaveEdge(e);
		
		//DB.ReadPoints();
		
//		ArrayList<Point> points = new ArrayList<Point>();
//		MapPanel h = new MapPanel();
//		h.setTitle("hello");
//		h.setBounds(100, 100, 1000, 500);
//		h.setVisible(true);
//		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Index I = new Index();
		I.setTitle("hello");
		I.setBounds(300, 100, 600, 600);
		I.setVisible(true);
		I.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		I.setResizable(false);
//		UserUI U = new UserUI();
//		U.setTitle("hello");
//		U.setBounds(100, 100, 1000, 500);
//		U.setVisible(true);
//		U.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}