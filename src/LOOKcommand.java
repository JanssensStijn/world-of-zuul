public class LOOKcommand extends Command{

    public LOOKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
            if (getSecondWord()==null) {
                // if there is no second word, we don't know what to take...
                System.out.println("Look where?");
                return false;
            }
            String whereToLook = getSecondWord();
            switch (whereToLook) {
                case "room":
                    System.out.println(player.getCurrentRoom().getLongDescription());
                    if(player.getCurrentRoom().containsFriendly()){
                        System.out.println("\nFollowing character is present in the room: ");
                        System.out.println(player.getCurrentRoom().getCharacter().getName() + "\n");
                    }
                    if(player.getCurrentRoom().containsEnemies()){
                        player.getCurrentRoom().printEnemyInfo();
                    }
                    break;
                case "inventory":
                    player.showInventory();
                    break;
                case "map":
                    if(player.checkInventory(Item.MAP)) showMap();
                    else System.out.println("You don't have a map.");
                    break;
                default: System.out.println("You can't look at that");
            }

        return false;
    }
    private void showMap(){
        System.out.println(
                "                                  ▌▀▀▀▀▀▀▀▐       ▌▀▀▀▀▀▀▀▀▀▀▀▀▐\n"+
                        "                                  ▌ COURT ▐       ▌ BLACKSMITH ▐\n"+
                        "                                  ▌▄▄▄▄▄▄▄▐       ▌▄▄▄▄▄▄▄▄▄▄▄▄▐\n"+
                        "                                      ║                  ║\n" +
                        "▌▀▀▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▀▀▀▐\n" +
                        "▌ COTTAGE ▐═════▌ FOREST ▐═════▌ WEST PLAZA ▐═════▌ EAST PLAZA ▐═════▌ PUB ▼ ▐═════▌ ▲ CELLAR ▐\n" +
                        "▌▄▄▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▄▄▄▐\n" +
                        "                                                         ║\n"+
                        "                                                 ▌▀▀▀▀▀▀▀▀▀▀▀▀▀▀▐     ▌▀▀▀▀▀▀▀▀▀▀▀▀▐\n" +
                        "                                                 ▌ WATCHTOWER ▲ ▐═════▌ ▼ LOOK-OUT ▐\n" +
                        "                                                 ▌▄▄▄▄▄▄▄▄▄▄▄▄▄▄▐     ▌▄▄▄▄▄▄▄▄▄▄▄▄▐"
        );
    }//print an ascii version of the map
}
