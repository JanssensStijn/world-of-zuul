public class BACKcommand extends Command{
    public BACKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if(player.getCurrentRoom().containsEnemies())
            System.out.println("You can't leave with an enemy on your tail.");
        else if (player.roomHistory.empty()) {
            System.out.println("You can't go back anymore.");
        } else {
            Room previousRoom = player.roomHistory.pop();
            player.setCurrentRoom(previousRoom);
            System.out.println("You're " + player.getCurrentRoom().getDescription());
            System.out.println();
            player.getCurrentRoom().printEnemyInfo();
        }
        return false;
    }
}
