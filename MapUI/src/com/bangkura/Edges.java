package com.bangkura;
/**
 * Created by Hongnan on 11/3/15.
 */
public class Edges {
	private  String id;
    private  Vertex start;
    private  Vertex destination;
    private  int weight;
	public void setId(String id) {
		this.id = id;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	

    public Edges(String id, Vertex start, Vertex destination, int weight) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }

    public Edges() {
		// TODO Auto-generated constructor stub
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
