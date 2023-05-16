import java.util.Random;

public class Fighter extends Creatures{
    private int life;
    private int maxLife;
    private int maxDamage;
    private boolean isAlive = true;

    public Fighter(String name, int maxLife, int maxDamage){
        super(name);
        this.maxLife = maxLife;
        this.life = maxLife;
        this.maxDamage = maxDamage;
    }

    public int getLife(){return life;}
    public void died(){isAlive = false;}

    public boolean isAlive(){
        if(life == 0) died();
        return isAlive;
    }

    public int attack()
    {
        Random randomDamage = new Random();
        int damage = 0;
        int additionalDamage =  0;
        for (Item item: getInventory().keySet()) {
            if (item.getName().equals("sword")) additionalDamage = 5;
        }

        //if(inventory.containsKey("sword")) additionalDamage = 5;
        damage = randomDamage.nextInt(maxDamage) + additionalDamage;
        return damage;
    }

    public int takeDamage(int damage){
        if(damage > life) {life = 0; died();}
        else life -= damage;
        return life;
    }
}
