public class ATTACKcommand extends Command{

    public ATTACKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

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
