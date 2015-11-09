package Algorithm;

/**
 * Created by Hongnan on 11/6/15.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import org.junit.Test;

public class FindPath {

    //List<Vertex> nodes;
    //LinkedList<Edge> edges;

    /**public void addLane(String laneId, int sourceLocNo, int destLocNo,
                        int duration) {


        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }*/

    public static void main(String[] args) {

        //FindPath find = new FindPath();
    	
    	String startBuilding = args[0];
    	String startLocation = args[1];
    	String endBuilding = args[2];
    	String endLocation = args[3];
    	
    	System.out.println("From Find Path");
    	System.out.println(startBuilding);
    	System.out.println(startLocation);
    	System.out.println(endBuilding);
    	System.out.println(endLocation);

        List<Vertex> nodes;
        List<Edge> edges;

        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();



        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
            Edge edge = new Edge("Edge_" + i, location, location, 80+ 10 * i);
            nodes.add(location);
        }

        Edge edge1 = new Edge("Edge_0", nodes.get(0), nodes.get(1), 85);
        Edge edge2 = new Edge("Edge_1", nodes.get(0), nodes.get(2), 217);
        Edge edge3 = new Edge("Edge_2", nodes.get(0), nodes.get(4), 173);
        Edge edge4 = new Edge("Edge_3", nodes.get(2), nodes.get(6), 186);
        Edge edge5 = new Edge("Edge_4", nodes.get(2), nodes.get(7), 103);
        Edge edge6 = new Edge("Edge_5", nodes.get(3), nodes.get(7), 183);
        Edge edge7 = new Edge("Edge_6", nodes.get(5), nodes.get(8), 250);
        Edge edge8 = new Edge("Edge_7", nodes.get(8), nodes.get(9), 84);
        Edge edge9 = new Edge("Edge_8", nodes.get(7), nodes.get(9), 167);
        Edge edge10 = new Edge("Edge_9", nodes.get(4), nodes.get(9), 502);

        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);
        edges.add(edge8);
        edges.add(edge9);
        edges.add(edge10);

        //find.addLane("Edge_10", 9, 10, 40);
        //find.addLane("Edge_11", 1, 10, 600);

        Graph graph = new Graph(nodes, edges);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(9));

        // Lets check from location Loc_1 to Loc_10


        //assertNotNull(path);
        //assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

}

