import java.util.Random;

/**Class used for every character that is able to fight within the game
 * child class of 'Character'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class Fighter extends Character {
    private int life;
    private int maxLife;
    private int maxDamage;
    private boolean isAlive = true;

    /**initialising a new fighter using the constructor of the parent class
     *
     * @param name name of the fighter
     * @param maxLife maximum life the fighter has
     * @param maxDamage maximum damage the fighter can do
     */
    public Fighter(String name, int maxLife, int maxDamage){
        super(name);
        this.maxLife = maxLife;
        this.life = maxLife;
        this.maxDamage = maxDamage;
    }

    /**getter for the fighters life points
     *
     * @return life amount of life points that the fighters has left
     */
    public int getLife(){return life;}

    /**
     * resetting life after the death
     * used when there are multiple enemies of the same kind, for example 3 trolls, in the same room
     */
    public void resetLife(){life = maxLife;isAlive = true;}

    /**
     * check if fighter is still alive
     * @return true if alive otherwise false
     */
    public boolean isAlive(){
        if(life == 0) isAlive = false;
        return isAlive;
    }

    /**
     * calculating the damage by the attack
     * @return damage dealt with the attack
     */
    public int attack() {
        Random randomDamage = new Random();
        int damage = 0;
        int additionalDamage =  0;

        if(getInventory().containsKey(Item.SWORD))additionalDamage = 5;

        damage = randomDamage.nextInt(maxDamage) + additionalDamage;
        return damage;
    }

    /**
     * decrease life points with the amount of damage
     * @param damage
     */
    public void takeDamage(int damage){
        if(damage > life) {life = 0; isAlive();}
        else life -= damage;
    }
}
