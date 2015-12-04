 /**
  * Created by Hongnan on 11/3/15.
 */

import java.util.List;

public class Graph {
    private final List<Vertex> points;
    private final List<Edges> edges;

    public Graph(List<Vertex> points, List<Edges> edges) {
        this.points = points;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return points;
    }

    public List<Edges> getEdges() {
        return edges;
    }

}

