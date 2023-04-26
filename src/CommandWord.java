public enum CommandWord {
    GO("go"), LOOK("look"), SEARCH("search"), TAKE("take"), DROP("drop"), INVENTORY("inventory"), QUIT("quit"),
    HELP("help"), UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
