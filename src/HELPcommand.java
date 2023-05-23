import java.util.Arrays;

/**Executed whenever a help command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class HELPcommand extends Command{

    private Parser parser;

    /**initialising of new help command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public HELPcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
        parser = new Parser();
    }

    /** method for asking help
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        System.out.println(player.getName() + " is lost and alone, and wanders around in a dark world.");
        System.out.println();
        System.out.println("Possible command words are:");
        Arrays.stream(CommandWord.values()).filter(keyword -> !keyword.equals(CommandWord.UNKNOWN)).forEach(keyword -> System.out.print(keyword + " ")); //commands nog uitlezen van enum
        System.out.println("\n");

        return false;
    }
}
