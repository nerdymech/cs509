package Algorithm; /**
 * Created by Hongnan on 11/5/15.
 */

import java.util.*;

public class DJAlgorithm {
    private final List<Vertex> nodes;
    private final List<Edges> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DJAlgorithm(Graph graph){
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edges>(graph.getEdges());
    }

    public void execute(Vertex start){
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();

        distance.put(start, 0);
        unSettledNodes.add(start);

        while (unSettledNodes.size() > 0){
            Vertex node = getMin(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinDistance(node);
        }

    }

    private void findMinDistance(Vertex node){
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex i : adjacentNodes){
            if (getShortestDistance(i) > getShortestDistance(node) + getDistance(node, i)){
                distance.put(i, getShortestDistance(node) + getDistance(node, i));

                predecessors.put(i, node);

                unSettledNodes.add(i);
            }
        }
    }

    private int getDistance(Vertex node, Vertex target){
        for (Edges edge:edges){
            if (edge.getStart().equals(node) && edge.getDestination().equals(target)){
                return edge.getWeight();
            }
        }

        throw new RuntimeException("Error");
    }

    private List<Vertex> getNeighbors(Vertex node){
        List<Vertex> neighbors = new ArrayList<Vertex>();

        for (Edges edge:edges){
            if (edge.getStart().equals(node) && !isSettled(edge.getDestination())){
                neighbors.add(edge.getDestination());
            }
        }

        return neighbors;
    }

    private  Vertex getMin(Set<Vertex> vertexes){
        Vertex min = null;
        for (Vertex vertex: vertexes){
            if (min == null){
                min = vertex;
            }else  {
                if (getShortestDistance(vertex) < getShortestDistance(min)){
                    min = vertex;
                }
            }
        }
        return  min;
    }


    private boolean isSettled(Vertex vertex){
        return settledNodes.contains((vertex));
    }

    private int getShortestDistance(Vertex destination){
        Integer d = distance.get(destination);

        if (d == null){
            return Integer.MAX_VALUE;
        }else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath (Vertex target){
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;

        if (predecessors.get(step) == null){
            return  null;
        }

        path.add(step);

        while (predecessors.get(step) != null){
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);

        return path;
    }


}