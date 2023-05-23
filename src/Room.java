import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west, up and down.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Stijn Janssens
 * @version 2023/05/23
 */

public class Room {
    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<Item, Integer> items;
    private HashMap<Fighter, Integer> enemies;
    private Character character;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     *
     * @param description The room's description.
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        enemies = new HashMap<>();
    }

    /**
     * getter for the name of the room
     * @return name of the room
     */
    public String getName (){return name;}

    /**
     * getter for the exit of the room
     * @param direction direction of the exit
     * @return exit of the room
     */
    public Room getExit(String direction) {
        return exits.get(direction.toUpperCase());
    }

    /**
     * Define an exits of this room.  Every direction either leads
     * to another room or is null when no exit is given.
     * @param direction direction for which to add exit
     * @param neighbor  The exit
     */
    public void setExit(Direction direction, Room neighbor) {
        exits.put(direction.name(), neighbor);
    }

    /**
     * add an item to the room
     * @param item item to add
     * @param amount amount of the item to add
     */
    public void addItem(Item item, int amount) {
            if(items.containsKey(item)) items.put(item, items.get(item) + amount);
                else items.put(item, amount);
    }
    /**
     * add an enemy to the room
     * @param enemy enemy to add
     * @param amount amount of enemies to add
     */
    public void addEnemy(Fighter enemy, int amount){
        enemies.put(enemy, amount);
    }
    /**
     * add a character to the room
     * @param character character to add
     */
    public void setCharacter(Character character){
        this.character = character;
    }

    /**
     * getter for all the enemies in the room
     * @return hasmmap containing the enemy and the amount of enemies in the room
     */
    public HashMap<Fighter, Integer> getEnemies(){return enemies;}

    /**
     * getter for the character within the room
     * @return
     */
    public Character getCharacter(){return character;}

    /**getter for the description of the room
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /** getter for the information aboout the exits
     * @return String met alle aanwezige uitgangen
     * bv. "Exits: north west".
     */
    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }

    /**
     * check if a certain item is present in the room
     * @param itemToHave
     * @return true if present in room otherwise false
     */
    public boolean hasItem(String itemToHave) {
        for (Item item : items.keySet()) {
            if (item.toString().equals(itemToHave)) return true;
        }
        return false;
    }

    /**
     * getter for the specific item in the room
     * @param name
     * @return
     */
    public Item getItem(String name) {
        if(hasItem(name)){
            for (Item item : items.keySet()) {
                if (item.toString().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * getter for the amount of the specified item in the room
     * @param name name of the item
     * @return amount of item
     */
    public int getNumberOfItem(String name) {
        for (Item item : items.keySet()) {
            if (item.toString().equals(name)) {
                return items.get(item);
            }
        }
        return 0;
    }

    /**
     * getter for all items within the room
     * @return hashmap containing the items and their amount
     */
    public HashMap<Item, Integer> getItems(){
        return items;
    }

    /**
     * getter for the information about the location
     * @return
     */
    public String getLongDescription() {
        return "You are " + description + "\n"  + getExitString();
    }

    /**
     * getter information about items in the room
     * @return string with information of which item and their amount of it within the room
     */
    public String getShortItemDescription() {
        String returnString = "You see following items in the room: ";
        if (items.isEmpty()) return returnString + " nothing.";
            else{
            for (Item item : items.keySet()) {
                returnString += "\n   " + item + " {" + items.get(item) + "}";
            }
            return returnString;
        }
    }

    /**
     * check if there are enemies within the room
     * @return true if enemies present otherwise false
     */
    public boolean containsEnemies(){
        checkDeadEnemies();
        if(!enemies.isEmpty())return true;
        else return false;
    }
    /**
     * check if there are characters within the room
     * @return true if characters present otherwise false
     */
    public boolean containsFriendly(){
        if(character != null)return true;
        else return false;
    }

    /**
     * print information about the enemies that the room contains
     */
    public void printEnemyInfo() {
        if (containsEnemies()) {
            System.out.println("There are following enemies in this room:");
            for (Fighter enemy : getEnemies().keySet()) {
                System.out.println(getEnemies().get(enemy) + " " + enemy.getName() + " with " + enemy.getLife() + " life-points each\n");
            }
        }
    }

    /**
     * check if there are enemies within the room that died
     */
    public void checkDeadEnemies(){

        for (Fighter enemy : enemies.keySet()) {
            if (!enemy.isAlive() && enemies.get(enemy) > 1 )
            {
                enemy.resetLife();
                enemies.put(enemy, enemies.get(enemy) - 1);
                System.out.println("You have defeated 1 " + enemy.getName());
                System.out.println("There are " + enemies.get(enemy) + " " + enemy.getName() + " left.");
            }
            else if (!enemy.isAlive() && enemies.get(enemy) == 1)
            {
                System.out.println("You have defeated the last " + enemy.getName());
                enemies.remove(enemy);
            }
        }
    }

}
