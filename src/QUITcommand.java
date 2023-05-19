public class QUITcommand extends Command{
    public QUITcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }
    /**
     * "Quit" was entered. Quit the game.
     * @param player
     * @return true, this command quits the game
     */
    @Override
    public boolean execute(Player player) {
        return true;
    }
}
