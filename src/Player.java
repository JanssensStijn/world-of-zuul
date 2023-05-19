import java.util.HashMap;
import java.util.Stack;

public class Player extends Fighter{
    private Room currentRoom;



    private int maxInventoryWeight;

    Stack<Room> roomHistory;

    public Player(String name, Room currentRoom, int maxWeight, int maxLife, int maxDamage) {
        super(name, maxLife, maxDamage);
        this.currentRoom = currentRoom;
        this.maxInventoryWeight = maxWeight;
        roomHistory = new Stack<>();
    }
    public void increaseMaxInventoryWeight(int extraWeight) {
        this.maxInventoryWeight += extraWeight;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    public int getCurrentWeight(){
        int totalWeight = 0;
        for (Item item :getInventory().keySet()) {
            totalWeight += item.getWeight()* getInventory().get(item);
        }
        return totalWeight;
    }
    public boolean canTakeItem(Item item, int amount){
        return (maxInventoryWeight >= getCurrentWeight() + (item.getWeight()*amount));
    }

    public void take(Item item, int amount) {
        if(canTakeItem(item,amount)) {
            HashMap<Item, Integer> temp = new HashMap<>();
            temp.put(item, amount);
            super.take(temp);
        }
        else System.out.println("The " + item.getName() + " is too heavy.");
    }

    private void takeAll(Item item){
        take(item, getCurrentRoom().getNumberOfItem(item.getName()));
    }
    public void takeAll(String itemName){
        if(getCurrentRoom().hasItem(itemName))
            takeAll(getCurrentRoom().getItem(itemName));
        else System.out.println("There is no item with that name");
    }
    public void showInventory() {
        String desc = "There are ";
        if (getInventory().isEmpty()) {
            desc += "currently no items in your inventory";
        } else {
            desc += "following items in your inventory: ";
            for (Item item : getInventory().keySet()) {
                desc += "\n   " + item.getName() + " {" + getInventory().get(item) + "}" + item.getLongDescription();
            }
        }
        System.out.println(desc);
    }
    public String getShortItemDescription() {
        String returnString = "There are ";
        if (getInventory().isEmpty()) return returnString + "currently no items in your inventory";
        else{
            returnString += "following items in your inventory: ";
            for (Item item : getInventory().keySet()) {
                returnString += "\n   " + item.getName() + " {" + getInventory().get(item) + "}";
            }
            return returnString;
        }
    }

}
