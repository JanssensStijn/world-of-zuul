/**Executed whenever an unknown command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class UNKNOWNcommand extends Command{

    /**initialising of new unknown command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public UNKNOWNcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for unknown commands given by user
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        System.out.println("We don't know what you mean.");
        return false;
    }
}
