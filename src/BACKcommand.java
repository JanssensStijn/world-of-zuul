/**Executed whenever a back command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class BACKcommand extends Command{

    /**initialising of new back commands using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public BACKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for going back to previous visited rooms
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        if(player.getCurrentRoom().containsEnemies())
            System.out.println("You can't leave with an enemy on your tail.");
        else if (player.getRoomHistory().empty()) {
            System.out.println("You can't go back anymore.");
        } else {
            Room previousRoom = player.getRoomHistory().pop();
            player.setCurrentRoom(previousRoom);
            System.out.println("You're " + player.getCurrentRoom().getDescription());
            System.out.println();
            player.getCurrentRoom().printEnemyInfo();
        }
        return false;
    }
}
