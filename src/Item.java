/**Enumeration of all the different items
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
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

    /**
     * constructor of the enum
     *
     * @param name name of the item
     * @param description description of the item
     * @param weight weight of the item
     */
    Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * getter for the short description
     * @return description of the item
     */
    private String getDescription() {
        return description;
    }

    /**
     * getter for the weight of the item
     * @return weight of the item
     */
    public double getWeight() {
        return weight;
    }

    /**
     * getter for the long description
     * @return string with all info about the item
     */
    public String getLongDescription() {
        return " -" + getDescription() + "- with weight of " + getWeight() + "kg each.";
    }

    /**
     * the toString method need to be overwritten otherwise the enums will correspond to numbers instead of the commandString causing problems when comparing to the string given by the user
     * @return commandString
     */
    @Override
    public String toString() { //need to be present otherwise the enums will correspond to numbers instead of the name of the item
        return name;
    }
}
