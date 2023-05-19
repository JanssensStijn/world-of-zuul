public class EATcommand extends Command{

    public EATcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat what?");
            return false;
        }

        String itemName = getSecondWord();
        if (Item.BROWNIE.toString().equals(itemName) && player.checkInventory(Item.BROWNIE)) {
            player.getInventory().remove(Item.BROWNIE);
            System.out.println(player.getName() + " ate the brownie.");
            System.out.println(player.getName() + " begins to feel stronger.");
            System.out.println();
            player.increaseMaxInventoryWeight(10);
        }
        else System.out.println("You can't eat that");
        return false;
    }
}
