/**Command factory class to determine which class needs to be initialised depending on the given command
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class CommandFactory {
    /** factory method to initialise the correct command class
     *
     * @param command
     * @param secondWord
     * @return Command a specific command will be returned depending on the given command words
     */
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
