import java.util.HashMap;
import java.util.Random;

/**
 * class for NPC's -- Non Playable Characters
 */
public class NPC {
    String name;
    private boolean isEnemy;

    private int maxLife;
    private int life;

    private int maxDamage;

    private HashMap<Item, Integer> inventory;

    public NPC(String name){
        this.name = name;
        this.isEnemy = false;
        this.inventory = new HashMap<>();
        this.maxLife = 100;
        this.life = this.maxLife;
        this.maxDamage = 100;
    }
    public NPC (String name, int maxLife, int maxDamage){
        this.name = name;
        this.isEnemy = true;
        this.inventory = new HashMap<>();
        this.maxLife = maxLife;
        this.life = this.maxLife;
        this.maxDamage = maxDamage;
    }
    public String getName() {
        return name;
    }
    public boolean IsEnemy(){return isEnemy;}

    public int getItemAmount(String itemName){
        for (Item item: inventory.keySet()) {
            if(item.getName().equals(itemName)) return inventory.get(item);
        }
        return 0;
    }

    public boolean hasItems(){return !inventory.isEmpty();}

    public int attack()
    {
        Random randomDamage = new Random();
        int damage = 0;
        int additionalDamage =  0;

        if(inventory.containsKey("sword")) additionalDamage = 5;
        damage = randomDamage.nextInt(maxDamage) + additionalDamage;
        return damage;
    }

    public int takeDamage(int damage){
        if(damage > life) life = 0;
        else life -= damage;
        return life;
    }

    public int getLife(){return life;}

    public boolean isAlive(){
        if(life != 0) return true;
        else return false;
    }
    public void resetLife(){
        this.maxLife = maxLife;
        this.life = this.maxLife;
        this.maxDamage = maxDamage;
    }

    public HashMap<Item, Integer> dropLoot(){
        return inventory;
    }
    public Item sellItem(String Item)
}
