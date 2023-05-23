import java.util.HashMap;

public class Character {
    private String name;

    private HashMap<Item, Integer> inventory;

    public Character(String name) {

        this.name = name;
        inventory = new HashMap<>();
    }

    public String getName(){return name;}

    public HashMap<Item, Integer> getInventory(){return inventory;}

    public boolean checkInventory(Item item){return checkInventory(item,1);}
    public boolean checkInventory(Item item, int amount){
        for (Item itemInInventory: getInventory().keySet()) {
            if(itemInInventory.toString().equals(item.toString()) && getInventory().get(itemInInventory) >= amount) return true;
        }
        return false;
    }

    public void take(HashMap<Item, Integer> itemsHashMap){
        for (Item item: itemsHashMap.keySet()) {
            if(checkInventory(item)) {getInventory().put(item, getInventory().get(item) + itemsHashMap.get(item));}
            else getInventory().put(item, itemsHashMap.get(item));
            System.out.println(getName() + " took: " + item + " {" + itemsHashMap.get(item) + "}");
            System.out.println();
        }
    }
    private void take(Item item){
        HashMap<Item, Integer> temp = new HashMap<>();
        temp.put(item, 1);
        take(temp);
    }
    public void take(Item item, int amount) {
            HashMap<Item, Integer> temp = new HashMap<>();
            temp.put(item, amount);
            take(temp);
    }

    private Item getKeyFromValue(int value){
        for (Item item: getInventory().keySet()) {
            if (getInventory().get(item).equals(value)) return item;
        }
        return null;
    }

    public HashMap<Item, Integer> drop(Item itemToDrop, int amount ) {
        if(checkInventory(itemToDrop, amount)){
            getInventory().put(itemToDrop, getInventory().get(itemToDrop) - amount);
            System.out.println(getName() + " dropped: " + itemToDrop + " {" + amount + "}");
            System.out.println();

            HashMap<Item, Integer> temp = new HashMap<>();
            temp.put(itemToDrop,amount);

            if(getInventory().get(itemToDrop) == 0) getInventory().remove(itemToDrop);
            return temp;
        }
        else{
            System.out.println("You do not have enough " + itemToDrop);
            System.out.println();
            return null;
        }
    }
    public HashMap<Item, Integer> drop(String itemName){
        for (Item item : getInventory().keySet()) {
            if(item.equals(itemName)) return drop(item);
        }
        return null;
    }
    public HashMap<Item, Integer> drop(Item itemToDrop){return drop(itemToDrop, 1);}

}
