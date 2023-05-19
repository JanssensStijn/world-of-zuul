public class SEARCHcommand extends Command{
    public SEARCHcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println(player.getCurrentRoom().getShortItemDescription());
        System.out.println();
        return false;
    }
}
