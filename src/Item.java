public class Item {
    private String name;
    private String description;
    private double weight;

    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getWeight() {
        return weight;
    }
    public String getLongDescription() {
        return this.name + " (" + this.description + ") with weight of " + this.weight + "kg";
    }
}
