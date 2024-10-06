public class Owner {
    private String name;
    private float ownership;
    static private int nOwners;

    public Owner(String name, float ownership) {
        this.name = name;
        this.ownership = ownership;
        nOwners++;
    }

    public String getName() {return name;}

    public float getOwnership() {return ownership;}

    public void setOwnership(float newOwnership) {
        ownership = newOwnership;
    }

    public String toString() {
        return name + " owns " + ownership + " %.";
    }


}
