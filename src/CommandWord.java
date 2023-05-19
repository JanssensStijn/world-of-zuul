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

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
