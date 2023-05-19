public class GOcommand extends Command{
    public GOcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = getSecondWord();


        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if(player.getCurrentRoom().containsEnemies())
            System.out.println("You can't leave with an enemy on your tail.");
        else if (nextRoom == null) {System.out.println("There is no door!");}
        else {
            player.roomHistory.add(player.getCurrentRoom()); //store the current room in stack
            player.setCurrentRoom(nextRoom);
            System.out.println("You're " + player.getCurrentRoom().getDescription());
            System.out.println();
            player.getCurrentRoom().printEnemyInfo();
        }
        return false;
    }
}
