import java.util.HashMap;
import java.util.Stack;

public class Player extends Fighter{
    private Room currentRoom;

    private Stages currentStage = Stages.STAGE1;

    private int maxInventoryWeight;

    Stack<Room> roomHistory;

    public Player(String name, Room currentRoom, int maxWeight, int maxLife, int maxDamage) {
        super(name, maxLife, maxDamage);
        this.currentRoom = currentRoom;
        this.maxInventoryWeight = maxWeight;
        roomHistory = new Stack<>();
    }
    public Stages getCurrentStage(){return currentStage;}
    public void nextStage(){
        if(currentStage == Stages.STAGE1) currentStage = Stages.STAGE2;
        else if(currentStage == Stages.STAGE2)  currentStage = Stages.STAGE3;
        else currentStage = Stages.STAGE4;
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
            getCurrentRoom().getItems().remove(item);
        }
        else System.out.println("The " + item + " is too heavy.\n");
    }

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
