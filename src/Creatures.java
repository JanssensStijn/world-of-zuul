import java.util.HashMap;

public class Creatures {
    private String name;

    private HashMap<Item, Integer> inventory;

    public Creatures(String name) {
        this.name = name;
    }

    public String getName(){return name;}

    public HashMap<Item, Integer> getInventory(){return inventory;}

    private boolean hasItem(Item itemToHave) {
        for (Item item : getInventory().keySet()) {
            if (item.equals(itemToHave)) return true;
        }
        return false;
    }
    private boolean hasItems(Item itemToHave, int amount) {
        if(hasItem(itemToHave) && inventory.get(itemToHave) >= amount) return true;
        else return false;
    }

    public Item getItem(String itemName){
        for (Item item: getInventory().keySet()) {
            if(item.getName().equals(itemName)) return item;

        }
        return null;
    }

    public void addItem(Item itemToAdd, int amount) {
        if(hasItem(itemToAdd)){
            getInventory().put(itemToAdd, getInventory().get(itemToAdd.getName()) + amount);
        }
        else getInventory().put(itemToAdd, amount);
    }
    /*public void dropItem(Item item, int amount) {
            if(hasItems(item, amount){

                getInventory().put(item, getInventory().get(item.getName()) + itemSet.get(item.getName()));
            }
            else getInventory().put(item, itemSet.get(item.getName()));
    }*/

}
