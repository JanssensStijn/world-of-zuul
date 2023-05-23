import java.util.HashMap;

/**
 * This class used to recognise commands as they are typed in.
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class CommandWords {
    private HashMap<String, CommandWord> validCommands;

    /*** Constructor - initialise the command words.*/
    public CommandWords() {
        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
        // if we get here, the string was not found in the commands
    }

    /**
     * get command corresponding with string given by user
     *
     * @param aString string given by user
     * @return valid command when string corresponds with a command, otherwise return the UNKNOWN-command
     */
    public CommandWord getCommand(String aString) {
        if (validCommands.containsKey(aString)) return validCommands.get(aString);
        return CommandWord.UNKNOWN;
    }
}
