/**Executed whenever a take command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class TAKEcommand extends Command{

    /**initialising of new take command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public TAKEcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for taking items
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return false;
        }

        String itemName = getSecondWord();
        //if(player.checkInventory(player.getInventory().))
        player.takeAll(itemName);

        return false;
    }
}
