import java.util.HashMap;

/**Class used for every character within the game
 * parent class of 'fighter'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class Character {
    private String name;
    private HashMap<Item, Integer> inventory;

    /**initialising a new character using the constructor of the parent class
     *
     * @param name name of the character
     */
    public Character(String name) {
        this.name = name;
        inventory = new HashMap<>();
    }

    /**
     * getter for the name of the character
     * @return name of the character
     */
    public String getName(){return name;}

    /**
     * getter for the inventory of the character
     * @return inventory of the character
     */
    public HashMap<Item, Integer> getInventory(){return inventory;}

    /**
     * check if inventory contains specific item
     * @param item item to check
     * @return true if item is present in inventory otherwise false
     */
    public boolean checkInventory(Item item){return checkInventory(item,1);}

    /**
     * check if inventory contains specific item
     * @param item item to check
     * @param amount amount of item that needs to be present in inventory
     * @return true if item is present in inventory otherwise false
     */
    public boolean checkInventory(Item item, int amount){
        for (Item itemInInventory: getInventory().keySet()) {
            if(itemInInventory.toString().equals(item.toString()) && getInventory().get(itemInInventory) >= amount) return true;
        }
        return false;
    }

    /**
     * add item to inventory
     * @param item item to add
     * @param amount amount of item to add
     */
    public void addItem(Item item, int amount){
            if(checkInventory(item)) {getInventory().put(item, getInventory().get(item) + amount);}
            else getInventory().put(item,amount);
    }

    /**
     * take item and put it in inventory
     * @param itemsHashMap hashMap containing items and their amount
     */
    public void take(HashMap<Item, Integer> itemsHashMap){
        for (Item item: itemsHashMap.keySet()) {
            if(checkInventory(item)) {getInventory().put(item, getInventory().get(item) + itemsHashMap.get(item));}
            else getInventory().put(item, itemsHashMap.get(item));
            System.out.println(getName() + " took: " + item + " {" + itemsHashMap.get(item) + "}");
            System.out.println();
        }
    }
    /**
     * take specific item and put it in inventory
     * @param item item to take
     * @param amount amount to take
     */
    public void take(Item item, int amount) {
            HashMap<Item, Integer> temp = new HashMap<>();
            temp.put(item, amount);
            take(temp);
    }

    /**
     * drop specific item and amount from inventory
     * @param itemToDrop item to drop
     * @param amount amount to drop
     * @return HashMap containing items and their amounts
     */
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

    /**
     * drop specific item from inventory
     * @param itemToDrop item to drop
     * @return HashMap containing items and their amounts
     */
    public HashMap<Item, Integer> drop(Item itemToDrop){return drop(itemToDrop, 1);}

}
