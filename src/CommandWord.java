/**Enumeration of all the possible commands
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public enum CommandWord {
    ATTACK("attack"),
    BACK("back"),
    BUY("buy"),
    DROP("drop"),
    EAT("eat"),
    GO("go"),
    HELP("help"),
    LOOK("look"),
    SEARCH("search"),
    TAKE("take"),
    TALK("talk"),
    UNKNOWN("?"),
    QUIT("quit");

    private String commandString;

    /**getter for the command string
     *
     * @param commandString string representation of the command
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }
}
