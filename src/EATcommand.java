/**Executed whenever an eat command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class EATcommand extends Command{

    /**initialising of new eat command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public EATcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for eating something
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat what?");
            return false;
        }

        String itemName = getSecondWord();
        if (Item.BROWNIE.toString().equals(itemName) && player.checkInventory(Item.BROWNIE)) {
            player.getInventory().remove(Item.BROWNIE);
            System.out.println(player.getName() + " ate the brownie.");
            System.out.println(player.getName() + " begins to feel stronger.");
            System.out.println();
            player.increaseMaxInventoryWeight(10);
        }
        else System.out.println("You can't eat that");
        return false;
    }
}
