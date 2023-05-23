import java.util.Random;

public class Fighter extends Character {
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
    public void resetLife(){life = maxLife;isAlive = true;}
    public boolean isAlive(){
        if(life == 0) isAlive = false;
        return isAlive;
    }
    public void died(){isAlive = false;}

    public int attack() {
        Random randomDamage = new Random();
        int damage = 0;
        int additionalDamage =  0;

        if(getInventory().containsKey(Item.SWORD))additionalDamage = 5;

        damage = randomDamage.nextInt(maxDamage) + additionalDamage;
        return damage;
    }

    public void takeDamage(int damage){
        if(damage > life) {life = 0; isAlive();}
        else life -= damage;
    }
}
