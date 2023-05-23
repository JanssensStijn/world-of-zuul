/**Executed whenever a search command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class SEARCHcommand extends Command{

    /**initialising of new search command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public SEARCHcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method used when searching for items in the room
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        System.out.println(player.getCurrentRoom().getShortItemDescription());
        System.out.println();
        return false;
    }
}
