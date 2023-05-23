/**Executed whenever a drop command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class DROPcommand extends Command{

    /**initialising of new drop commands using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public DROPcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for going to another room
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
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
