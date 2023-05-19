public class CommandFactory {
    public Command getCommand(CommandWord command, String secondWord)
    {
        switch(command){
            case ATTACK: return new ATTACKcommand(command, secondWord);
            case BACK: return new BACKcommand(command, secondWord);
            case BUY: return new BUYcommand(command, secondWord);
            case DROP: return new DROPcommand(command, secondWord);
            case EAT: return new EATcommand(command, secondWord);
            case GO: return new GOcommand(command, secondWord);
            case HELP: return new HELPcommand(command, secondWord);
            case LOOK: return new LOOKcommand(command, secondWord);
            case SEARCH: return new SEARCHcommand(command, secondWord);
            case TAKE: return new TAKEcommand(command, secondWord);
            case TALK: return new TALKcommand(command, secondWord);
            case QUIT: return new QUITcommand(command, secondWord);
        }
        return null;
    }
}
