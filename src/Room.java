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
    private HashMap<NPC, Integer> npcs;

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
        npcs = new HashMap<>();
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
        items.put(item, amount);
    }
    public void addItemsFromLoot(HashMap<Item, Integer> loot) {

        for (Item item: loot.keySet()) {
            if(items.containsKey(item)) items.put(item, items.get(item) + loot.get(item));
                else items.put(item, loot.get(item));
        }
    }
    public void addNPC(NPC npc, int amount){
        npcs.put(npc, amount);
    }
    public HashMap<NPC, Integer> getNPCs(){return npcs;}

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

    public String getItemsString() {
        if (!items.isEmpty()) {
            String returnString = "; it contains items:\n";
            for (Item item : items.keySet()) {
                returnString += "   " + item.getLongDescription() + "\n";
            }
            return returnString;
        }
        return "";
    }

    public boolean hasItem(String name) {
        for (Item item : items.keySet()) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        for (Item item : items.keySet()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
    public int getNumberOfItem(String name) {
        for (Item item : items.keySet()) {
            if (item.getName().equals(name)) {
                return items.get(item);
            }
        }
        return 0;
    }
    public void removeItem(Item itemToBeRemoved){
        if(items.containsKey(itemToBeRemoved)) items.remove(itemToBeRemoved);
    }//do not remove a key while in a hashmap iteration, it will result in error when hashmap has multiple keys

    public String getLongDescription() {
        return "You are " + description + "\n"  + getExitString();
    }

    public String getLongItemDescription() {
        return "You see following items in the room:\n" + getItemsString();
    }
    public String getShortItemDescription() {
        String returnString = "You see following items in the room: ";
        if (items.isEmpty()) return returnString + " nothing.";
            else{
            for (Item item : items.keySet()) {
                returnString += "\n   " + item.getName() + " {" + items.get(item) + "}";
            }
            return returnString;
        }
    }

    public boolean containsEnemy(){
        checkDeadEnemies();
        if(!npcs.isEmpty()){
            for (NPC npc: npcs.keySet()) {
                if(npc.IsEnemy()) return true;
            }
        }
        return false;

    }
    public boolean containsfriendly(){
        if(!npcs.isEmpty()){
            for (NPC npc: npcs.keySet()) {
                if(!npc.IsEnemy()) return true;
            }
        }
        return false;

    }
    public void checkDeadEnemies(){
        for (NPC npc: npcs.keySet()) {
            if (!npc.isAlive() && npcs.get(npc) > 1 )
            {
                npc.resetLife();
                npcs.put(npc, npcs.get(npc) - 1);
                System.out.println("You have defeated 1 " + npc.getName());
                System.out.println("There are " + npcs.get(npc) + " " + npc.getName() + " left.");
                addItemsFromLoot(npc.dropLoot());
            }
            else if (!npc.isAlive() && npcs.get(npc) == 1)
            {
                System.out.println("You have defeated the last " + npc.getName());
                addItemsFromLoot(npc.dropLoot());
                npcs.remove(npc);
            }
        }
    }

}
