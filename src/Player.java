import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player extends Fighter{
    private Room currentRoom;

    private int maxInventoryWeight;

    private int maxLife;
    private int life;
    private int maxDamage;

    public Player(String name, Room currentRoom,int maxWeight, int maxLife, int maxDamage) {
        super(name, maxLife, maxDamage);
        this.currentRoom = currentRoom;
        this.maxInventoryWeight = maxWeight;
        this.maxLife = maxLife;
        this.maxDamage = maxDamage;
        this.life = this.maxLife;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int currentWeight(){
        int totalWeight = 0;
        for (Item item :getInventory().keySet()) {
            totalWeight += item.getWeight()* getInventory().get(item);
        }
        return totalWeight;
    }

    public boolean canTakeItem(Item item, int amount){
        return (maxInventoryWeight >= currentWeight() + (item.getWeight()*amount));
    }

    public boolean tradeItem(Item itemToGet, int amountToGet, Item itemToGive, int amountToGive ) {
        boolean removeItem = false;
        for (Item item: getInventory().keySet()) {
            if(getInventory().containsKey(itemToGive) && getInventory().get(itemToGive) >= amountToGive)
            {
                if(getInventory().get(itemToGive) > amountToGive) getInventory().put(itemToGive, getInventory().get(itemToGive) - amountToGive);
                else getInventory().remove(itemToGive);

                if(getInventory().containsKey(itemToGet)) getInventory().put(itemToGet, getInventory().get(itemToGet) + amountToGet);
                else getInventory().put(itemToGet, amountToGet);
                return true;
            }
        }
        return false;
    }


    public HashMap<Item, Integer> giveItemToOther(Item item) {
        HashMap<Item, Integer> temp = new HashMap<>();
        if(getInventory().containsKey(item))
        {
            temp.put(item, getInventory().get(item));
            getInventory().remove(item); //do not remove a key while in a hashmap iteration, it will result in error when hashmap has multiple keys
            return temp;
        }

        return null;
    }
    public boolean checkInventory(Item item, int amount){
        for (Item itemInInventory: getInventory().keySet()) {
            if(itemInInventory.getName().equals(item.getName()) && getInventory().get(itemInInventory) >= amount) return true;
        }
        return false;
    }

    public String getLongDescription() {
        String desc = "There are ";
        if (getInventory().isEmpty()) {
            desc += "currently no items in your inventory";
        } else {
            desc += "following items in your inventory: ";
            for (Item item : getInventory().keySet()) {
                desc += "\n   " + item.getLongDescription();
            }
        }
        return desc;
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
