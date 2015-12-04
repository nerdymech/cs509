package com.bangkura;
/**
 * Created by Hongnan on 11/3/15.
 */
public class Vertex {
    

	private String id;
    private String name;
    public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
//    final private String building;
//    final private String floor;
    // final private String attribute;
    // final private String feature;

    public Vertex() {
      
//        this.building = building;
//        this.floor = floor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String getBuilding{
//        return building;
//    }

//    public  String getFloor{
//        return floor;
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
