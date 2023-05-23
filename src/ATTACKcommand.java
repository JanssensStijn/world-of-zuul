/**Executed whenever an attack command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class ATTACKcommand extends Command{

    /**initialising of new attack commands using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public ATTACKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for attacking something
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        if (player.isAlive()) {
            if (!hasSecondWord()) {
                // if there is no second word, we don't know what to drop...
                System.out.println("Attack what?");
                return false;
            }
            String enemyName = getSecondWord();
            int attack;
            boolean enemyFound = false;

            for (Fighter enemy : player.getCurrentRoom().getEnemies().keySet()) {

                if (enemy.getName().equals(enemyName) && enemy.isAlive()) {
                    enemyFound = true;
                    //player attacks
                    attack = player.attack();
                    enemy.takeDamage(attack);
                    if (attack == 0) System.out.println("You missed");
                    else System.out.println("You attacked  " + enemy.getName() + " with " + attack + " damage.");

                    //enemy attack automatically
                    attack = enemy.attack();
                    player.takeDamage(attack);
                    if (attack == 0) System.out.println(enemy.getName() + " missed");
                    else System.out.println(enemy.getName() + " attacked you with " + attack + " damage");
                    System.out.println();
                    //print life-points left
                    System.out.println("You have " + player.getLife() + " life-points left.");
                    System.out.println(enemy.getName() + " has " + enemy.getLife() + " life-points left.");
                    System.out.println();
                    if(!player.isAlive()) {System.out.println("\nYou died!"); return true; }
                    player.getCurrentRoom().checkDeadEnemies();
                }
            }
            if (!enemyFound) System.out.println("There is no enemy with the name " + enemyName);
        }
        else System.out.println("You can't attack when you're dead.");
        return false;
    }
}
