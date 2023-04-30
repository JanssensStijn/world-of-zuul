import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    private String name;
    private Room currentRoom;
    private HashMap<Item, Integer> inventory;

    private int maxInventoryWeight;

    private int maxLife;
    private int life;

    boolean isAlive = true;
    private int maxDamage;

    public Player(String name, Room currentRoom,int maxWeight, int maxLife, int maxDamage) {
        this.name = name;
        this.currentRoom = currentRoom;
        inventory = new HashMap<>();
        this.maxInventoryWeight = maxWeight;
        this.maxLife = maxLife;
        this.maxDamage = maxDamage;
        this.life = this.maxLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private boolean hasItem(String name) {
        for (Item item : inventory.keySet()) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    private Item getItem(String itemName){
        for (Item item: inventory.keySet()) {
            if(item.getName().equals(itemName)) return item;

        }
        return null;
    }

    public int currentWeight(){
        int totalWeight = 0;
        for (Item item :inventory.keySet()) {
            totalWeight += item.getWeight()* inventory.get(item);
        }
        return totalWeight;
    }

    public boolean canTakeItem(Item item, int amount)
    {
        return (maxInventoryWeight >= currentWeight() + (item.getWeight()*amount));
    }

    public boolean take(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            if(hasItem(itemName)){
                inventory.put(currentRoom.getItem(itemName), inventory.get(currentRoom.getItem(itemName)) + currentRoom.getNumberOfItem(itemName));
            }
            else inventory.put(currentRoom.getItem(itemName), currentRoom.getNumberOfItem(itemName));

            return true;
        }
        return false;
    }

    public boolean drop(String itemName) {
        if(inventory.containsKey(getItem(itemName)))
        {
            currentRoom.addItem(getItem(itemName), inventory.get(getItem(itemName)));
            inventory.remove(getItem(itemName)); //do not remove a key while in a hashmap iteration, it will result in error when hashmap has multiple keys
            return true;
        }

        return false;
    }

    public String getLongDescription() {
        String desc = "There are ";
        if (inventory.isEmpty()) {
            desc += "currently no items in your inventory";
        } else {
            desc += "following items in your inventory: ";
            for (Item item : inventory.keySet()) {
                desc += "\n   " + item.getLongDescription();
            }
        }
        return desc;
    }

    public String getShortItemDescription() {
        String returnString = "There are ";
        if (inventory.isEmpty()) return returnString + "currently no items in your inventory";
        else{
            returnString += "following items in your inventory: ";
            for (Item item : inventory.keySet()) {
                returnString += "\n   " + item.getName() + " {" + inventory.get(item) + "}";
            }
            return returnString;
        }
    }

    public int attack()
    {
        Random randomDamage = new Random();
        int damage = 0;
        int additionalDamage =  0;
        for (Item item: inventory.keySet()) {
            if (item.getName().equals("sword")) additionalDamage = 5;
        }

        //if(inventory.containsKey("sword")) additionalDamage = 5;
        damage = randomDamage.nextInt(maxDamage) + additionalDamage;
        return damage;
    }

    public int takeDamage(int damage){
        if(damage > life) {life = 0; isAlive = false;}
        else life -= damage;
        return life;
    }

    public boolean isAlive(){
        if(life == 0) isAlive = false;
        else isAlive = true;
        return isAlive;
    }
    public int getLife(){return life;}
}
