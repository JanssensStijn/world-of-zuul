/**Executed whenever a quit command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class QUITcommand extends Command{

    /**initialising of new quit command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public QUITcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }
    /**
     * "Quit" was entered. Quit the game.
     * @param player
     * @return true, this command quits the game
     */
    @Override
    public boolean execute(Player player) {
        return true;
    }
}
