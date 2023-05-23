import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west, up and down.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Michael Kölling, David J. Barnes and Stijn Janssens
 * @version 2011.07.31
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
    public String getName (){return name;}

    public Room getExit(String direction) {
        return exits.get(direction.toUpperCase());
    }
    /**
     * Define an exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     *
     * @param direction direction for which to add exit
     * @param neighbor  The exit
     */
    public void setExit(Direction direction, Room neighbor) {
        exits.put(direction.name(), neighbor);
    }

    public void addItem(Item item, int amount) {
            if(items.containsKey(item)) items.put(item, items.get(item) + amount);
                else items.put(item, amount);
    }
    public void addEnemy(Fighter enemy, int amount){
        enemies.put(enemy, amount);
    }
    public void setCharacter(Character friendly){
        character = friendly;
    }
    public HashMap<Fighter, Integer> getEnemies(){return enemies;}
    public Character getCharacter(){return character;}

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
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


    public boolean hasItem(String itemToHave) {
        for (Item item : items.keySet()) {
            if (item.toString().equals(itemToHave)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        for (Item item : items.keySet()) {
            if (item.toString().equals(name)) {
                return item;
            }
        }
        return null;
    }
    public int getNumberOfItem(String name) {
        for (Item item : items.keySet()) {
            if (item.toString().equals(name)) {
                return items.get(item);
            }
        }
        return 0;
    }
    public HashMap<Item, Integer> getItems(){
        return items;
    }

    public String getLongDescription() {
        return "You are " + description + "\n"  + getExitString();
    }

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

    public boolean containsEnemies(){
        checkDeadEnemies();
        if(!enemies.isEmpty())return true;
        else return false;
    }
    public boolean containsFriendly(){
        if(character != null)return true;
        else return false;
    }
    public void printEnemyInfo() {
        if (containsEnemies()) {
            System.out.println("There are folowing enemies in this room:");
            for (Fighter enemy : getEnemies().keySet()) {
                System.out.println(getEnemies().get(enemy) + " " + enemy.getName() + " with " + enemy.getLife() + " life-points each\n");
            }
        }
    }
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
