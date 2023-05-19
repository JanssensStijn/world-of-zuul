public class BUYcommand extends Command{
    public BUYcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if((player.getCurrentRoom().getName().equals("blacksmith") || player.getCurrentRoom().getName().equals("pub")) && player.getCurrentStage() == Stages.STAGE3)
        {
            if (!hasSecondWord()) {
                // if there is no second word, we don't know what to take...
                System.out.println("Buy what?");
                return false;
            }
            String itemName = getSecondWord();
            if (player.getCurrentRoom().getName().equals("blacksmith") && !player.getCurrentRoom().getCharacter().checkInventory(Item.SWORD, 1))
                System.out.println("You are out of luck, everything is sold out.\n");
            else if (player.getCurrentRoom().getName().equals("blacksmith") && Item.SWORD.toString().equals(itemName)) {
                if (player.checkInventory(Item.COIN, 5)){
                    player.getCurrentRoom().getCharacter().take(player.drop(Item.COIN,5));
                    player.take(player.getCurrentRoom().getCharacter().drop(Item.SWORD, 1));
                    System.out.println("You bought a sword.\n");
                }
                else System.out.println("You don't have enough coin.\n");
            }
            else if (player.getCurrentRoom().getName().equals("pub") && !player.getCurrentRoom().getCharacter().checkInventory(Item.BEER, 1))
                System.out.println("You are out of luck, everything is sold out.\n");
            else  if (player.getCurrentRoom().getName().equals("pub") && Item.BEER.toString().equals(itemName)) {//Item.BEER.toString().equals(itemName)
                if (player.checkInventory(Item.COIN, 2)){
                    player.getCurrentRoom().getCharacter().take(player.drop(Item.COIN,2));
                    player.take(player.getCurrentRoom().getCharacter().drop(Item.BEER, 1));
                    System.out.println("You bought a beer.\n");
                }
                else System.out.println("You don't have enough coin.\n");
            } else System.out.println("We don't sell that\n");
        }
        else return new TALKcommand(CommandWord.TALK, getSecondWord()).execute(player);
        return false;
    }
}
