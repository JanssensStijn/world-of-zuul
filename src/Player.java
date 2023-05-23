import java.util.HashMap;
import java.util.Stack;

/**Class used for creating the player
 * child class of 'Fighter'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class Player extends Fighter{
    private Room currentRoom;
    private Stages currentStage = Stages.STAGE1;
    private int maxInventoryWeight;
    private Stack<Room> roomHistory;

    /**initialising a new player using the constructor of the parent class
     *
     * @param name name of the player
     * @param currentRoom room where the player currently is
     * @param maxWeight maximum weight the player can carry
     * @param maxLife maximum life the player has
     * @param maxDamage maximum damage the player can do
     */
    public Player(String name, Room currentRoom, int maxWeight, int maxLife, int maxDamage) {
        super(name, maxLife, maxDamage);
        this.currentRoom = currentRoom;
        this.maxInventoryWeight = maxWeight;
        roomHistory = new Stack<>();
    }

    /**
     * getter for the stack containing previous visited rooms
     * @return stack with previous visited rooms
     */
    public Stack<Room> getRoomHistory() {
        return roomHistory;
    }

    /**
     * getter for the current stage/level the player is in
     * @return stage/level of the game
     */
    public Stages getCurrentStage(){return currentStage;}

    /**
     * method to go to the next stage
     * */
    public void nextStage(){
        if(currentStage == Stages.STAGE1) currentStage = Stages.STAGE2;
        else if(currentStage == Stages.STAGE2)  currentStage = Stages.STAGE3;
        else currentStage = Stages.STAGE4;
    }

    /**
     * increase the maximum weight the player can carry with the amount of extraWeight
     * @param extraWeight the amount of weight that the player will be able to carry upon the normal maxInventoryWeight
     */
    public void increaseMaxInventoryWeight(int extraWeight) {
        this.maxInventoryWeight += extraWeight;
    }

    /**
     * getter for the current room
     * @return room where the player currently is
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * setter to change the current room of the player
     * @param currentRoom room that the player will go to
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * getter for the total weight that the player is carrying
     * @return total weight of the items in the inventory
     */
    public int getCurrentWeight(){
        int totalWeight = 0;
        for (Item item :getInventory().keySet()) {
            totalWeight += item.getWeight()* getInventory().get(item);
        }
        return totalWeight;
    }

    /**
     * check if item isn't too heavy to pick up, calculated upon the total inventory weight
     * @param item item to be checked
     * @param amount number of times the item will be taken up if possible
     * @return true if player can pick it up otherwise false
     */
    private boolean canTakeItem(Item item, int amount){
        return (maxInventoryWeight >= getCurrentWeight() + (item.getWeight()*amount));
    }

    /**
     * pick up a specific item
     * @param item item to be taken
     * @param amount amount of the item that will be taken
     */
    public void take(Item item, int amount) {
        if(canTakeItem(item,amount)) {
            HashMap<Item, Integer> temp = new HashMap<>();
            temp.put(item, amount);
            super.take(temp);
            getCurrentRoom().getItems().remove(item);
        }
        else System.out.println("The " + item + " is too heavy.\n");
    }

    /**
     * pick up total amount of specific item in the room
     * @param itemName item to be taken
     */
    public boolean takeAll(String itemName){
        for (Item item: Item.values()) {
            if(getCurrentRoom().hasItem(itemName)) {
                take(getCurrentRoom().getItem(itemName), getCurrentRoom().getNumberOfItem(itemName));

                return true;
            }
        }
        System.out.println("There is no item with that name");
        return false;
    }

    /**
     * show inventory
     */
    public void showInventory() {
        String desc = "There are ";
        if (getInventory().isEmpty()) {
            desc += "currently no items in your inventory";
        } else {
            desc += "following items in your inventory: ";
            for (Item item : getInventory().keySet()) {
                desc += "\n   " + item + " {" + getInventory().get(item) + "}" + item.getLongDescription();
            }
        }
        System.out.println(desc);
        System.out.println();
    }

    /**
     * show Inventory but without info about items
     * @return string with all items and their amount from within the inventory
     */
    public String getShortItemDescription() {
        String returnString = "There are ";
        if (getInventory().isEmpty()) return returnString + "currently no items in your inventory";
        else{
            returnString += "following items in your inventory: ";
            for (Item item : getInventory().keySet()) {
                returnString += "\n   " + item + " {" + getInventory().get(item) + "}";
            }
            return returnString;
        }
    }

}
