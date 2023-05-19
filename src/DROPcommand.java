import java.util.HashMap;

public class DROPcommand extends Command{
    public DROPcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return false;
        }

        String itemName = getSecondWord();
        for (Item item: player.getInventory().keySet()) {
            if(item.toString().equals(itemName)){
                player.getCurrentRoom().addItem(item, player.getInventory().get(item));
                player.getInventory().remove(item);
                System.out.println("You dropped all " + itemName);
            }
            else System.out.println("You don't have such item.");
        }

        return false;
    }
}
