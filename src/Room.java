import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Define an exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     *
     * @param direction direction for which to add exit
     * @param neighbor  The exit
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return string met alle aanwezige uitgangen
     * bvb. "Exits: north west".
     */
    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }
}