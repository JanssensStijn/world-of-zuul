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

    /**
     * getter for the command string
     * @param commandString string representation of the command
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * the toString method need to be overwritten otherwise the enums will correspond to numbers instead of the commandString causing problems when comparing to the string given by the user
     * @return commandString
     */
    @Override
    public String toString() { //need to be present otherwise the enums will correspond to numbers instead of the commandString
        return commandString;
    }
}
