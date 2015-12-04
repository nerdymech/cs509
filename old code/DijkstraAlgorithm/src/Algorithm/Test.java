package Algorithm; /**
 * Created by Hongnan on 11/6/15.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
    	
    	ReadData read = new ReadData();
        
        List<Vertex> points = new ArrayList<Vertex>(read.pointToVertex());
        List<Edges> edges = new ArrayList<Edges>(read.edgeToEdges());
        
        Graph graph = new Graph(points, edges);
        DJAlgorithm d = new DJAlgorithm(graph);
        d.execute(points.get(0));
        LinkedList<Vertex> path = d.getPath(points.get(4));

        for (Vertex vertex : path) {
            System.out.println(vertex.getId());
        }        
    }
}



