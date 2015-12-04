/**
 * Created by Hongnan on 11/3/15.
 */
package Algorithm;

public class Edge  {
    private final String id;
    private final Vertex start;
    private final Vertex destination;
    private final int weight;

    public Edge(String id, Vertex start, Vertex destination, int weight) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getStart() {
        return start;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return start + " " + destination;
    }


}
