import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Michael Kölling, David J. Barnes  and Stijn Janssens
 * @version 2011.07.31
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

    public CommandWord getCommand(String aString) {
        if (validCommands.containsKey(aString)) return validCommands.get(aString);
        return CommandWord.UNKNOWN;
    }

    public String showAll() {
        String show = "";
        for (String command : validCommands.keySet()) {
            show += command + " ";
        }
        return show;
    }
}
