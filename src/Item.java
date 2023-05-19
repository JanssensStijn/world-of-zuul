public enum Item {
    COIN("coin", "Something you can pay with", 0.01),
    GLINTSTONE("glintstone", "To you it's just a shine blue rock", 0.3),
    SWORD("sword", "A pointy and sharp thing", 2.3),
    STAFF("staff", "a staff that wields great power", 1.2),
    MAP("map", "a map of the town", 0.1),
    ARMOR("armor", "the armor of a black knight", 15.2),
    BEER("beer", "an alcoholic drink", 0.4),
    BROWNIE("brownie", "brownie with magical powers", 0.2);

    private String name;
    private String description;
    private double weight;

    Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String toString() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getWeight() {
        return weight;
    }
    public String getLongDescription() {
        return " -" + this.description + "- with weight of " + this.weight + "kg each.";
    }
}
