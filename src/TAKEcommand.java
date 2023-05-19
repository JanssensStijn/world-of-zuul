public class TAKEcommand extends Command{
    public TAKEcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return false;
        }

        String itemName = getSecondWord();
        //if(player.checkInventory(player.getInventory().))
        player.takeAll(itemName);

        return false;
    }
}
