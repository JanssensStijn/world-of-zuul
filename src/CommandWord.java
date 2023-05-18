public enum CommandWord {
    GO("go"), LOOK("look"), SEARCH("search"), TAKE("take"), DROP("drop"), QUIT("quit"),
    HELP("help"), UNKNOWN("?"), ATTACK("attack"), TALK("talk"), BUY("buy");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
