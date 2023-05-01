import java.util.concurrent.TimeUnit;
/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling, David J. Barnes  and Stijn Janssens
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Player player;
    private Room cottage, forest, court, westPlaza, entrance, blacksmith, eastPlaza, watchTower, lookOut, pub, cellar;
    private Item coin, sword, staff, shield, armor, glintstone, beer;

    private NPC troll, smithy, bartender, wizard, stranger;
    private int stage;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        createItems();
        createCharacters();
        parser = new Parser();
        stage = 1;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms
        cottage = new Room("cottage", "inside the lonely cottage behind the forest");
        forest = new Room("forest","in the forest");
        court = new Room("court", "in the court");
        westPlaza = new Room("westPlaza", "on the western half of the plaza");
        entrance = new Room("entrance", "at the entrance");
        blacksmith = new Room("blacksmith", "in the blacksmith's shop");
        eastPlaza = new Room("eastPlaza","on the eastern half of the plaza");
        watchTower = new Room("watchtower", "in the watchtower");
        lookOut = new Room("lookout","in the look-out");
        pub = new Room("pub","in the pub");
        cellar = new Room("cellar","in the pub's cellar");

        cottage.setExit(Direction.EAST, forest);

        forest.setExit(Direction.WEST, cottage);
        forest.setExit(Direction.EAST, westPlaza);

        westPlaza.setExit(Direction.WEST, forest);
        westPlaza.setExit(Direction.NORTH, court);
        westPlaza.setExit(Direction.EAST, eastPlaza);
        westPlaza.setExit(Direction.SOUTH, entrance);

        court.setExit(Direction.SOUTH, westPlaza);

        entrance.setExit(Direction.NORTH, westPlaza);

        eastPlaza.setExit(Direction.EAST, pub);
        eastPlaza.setExit(Direction.NORTH, blacksmith);
        eastPlaza.setExit(Direction.WEST, westPlaza);
        eastPlaza.setExit(Direction.SOUTH, watchTower);

        blacksmith.setExit(Direction.SOUTH, eastPlaza);

        pub.setExit(Direction.WEST, eastPlaza);
        pub.setExit(Direction.DOWN, cellar);

        cellar.setExit(Direction.UP, pub);

        watchTower.setExit(Direction.NORTH, eastPlaza);
        watchTower.setExit(Direction.UP, lookOut);

        lookOut.setExit(Direction.DOWN, watchTower);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createItems() {

        // create the items
        sword = new Item("sword", "A pointy and sharp thing", 2.3);
        coin = new Item("coin", "Something you can pay with", 0.01);
        staff = new Item("staff", "a staff that wields great power", 1.2);
        shield = new Item("shield", "a square shield", 2.4);
        armor = new Item("armor", "the armor of a black knight", 15.2);
        glintstone = new Item("glintstone", "To you it's just a shine blue rock", 0.3);
        beer = new Item("beer", "an alcoholic drink", 0.3);

        //add items to rooms or characters
        cottage.addItem(staff, 1);
        watchTower.addItem(shield, 1);
        lookOut.addItem(armor, 1);
        eastPlaza.addItem(coin, 2);
        cellar.addItem(coin, 5);

        entrance.addItem(glintstone, 1);
        pub.addItem(glintstone, 2);
        lookOut.addItem(glintstone, 5);
        westPlaza.addItem(glintstone, 1);
        eastPlaza.addItem(glintstone, 1);

    }

    /**
     * Create all the characters including the player.
     */
    private void createCharacters() {

        this.player = new Player("Human", entrance,10, 20, 5);
        this.troll = new NPC("troll", 20, 5);
        this.wizard = new NPC("wizard");
        this.smithy = new NPC("smithy");
        this.bartender = new NPC("bartender");
        this.stranger = new NPC("stranger");
        entrance.addNPC(stranger, 1);
        court.addNPC(wizard,1);
        blacksmith.addNPC(smithy,1);
        pub.addNPC(bartender,1);
        forest.addNPC(troll, 2);
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished && player.isAlive && stage != 4) {
            Command command = parser.getCommandWord();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a text base adventure game.");
        System.out.println("Type \'" + CommandWord.HELP.toString() + "\' if you need help.");

        System.out.println();

        System.out.println(player.getLongDescription());
        System.out.println();
        printLocationInfo();
    }

    private void printLocationInfo() {
        System.out.println("You're " + player.getCurrentRoom().getDescription());
        System.out.println();
    }


    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case LOOK:
                look();
                break;
            case SEARCH:
                search();
                break;
            case INVENTORY:
                showInventory();
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case ATTACK:
                attack(command);
                break;
            case GO:
                if (!player.getCurrentRoom().containsEnemy()) goRoom(command);
                else System.out.println("You can't leave with an enemy on your tail");
                break;
            case TALK:
                talk();
                break;
            case BUY:
                buy(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }

        return wantToQuit;
    }

    private void buy(Command command) {
        if((player.getCurrentRoom() == blacksmith || player.getCurrentRoom() == pub) && stage == 3)
        {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know what to take...
                System.out.println("Buy what?");
                return;
            }
            String itemName = command.getSecondWord();
            if (player.getCurrentRoom() == blacksmith && itemName.equals(sword.getName())) {
                if (player.tradeItem(sword, 1, coin, 5)) System.out.println("You bought a sword.\n");
                else System.out.println("You don't have enough coin.\n");
            } else if (player.getCurrentRoom() == pub && itemName.equals(beer.getName())) {
                if (player.tradeItem(beer, 1, coin, 2)) System.out.println("You bought a beer.\n");
                else System.out.println("You don't have enough coin.\n");
            } else System.out.println("There's nothing being sold with that name.\n");
        }
        else System.out.println("You can't buy anything here (yet).\n");
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Player " + player.getName() + " is lost and alone, and wanders");
        System.out.println("around in a dark world.");
        System.out.println();
        System.out.println("Possible command words are:\n" + parser.showCommands());
        System.out.println();
    }

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println("\nFollowing characters are present in the room: ");
        if(player.getCurrentRoom().containsfriendly()){
            for ( NPC npc: player.getCurrentRoom().getNPCs().keySet()) {
                System.out.println(npc.getName() + "\n");
            }
        }
    }

    private void search() {
        System.out.println(player.getCurrentRoom().getShortItemDescription());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
            printEnemyInfo();
        }
    }
    private void talk(){
        interactions();
    }
    private void interactions(){
        switch(player.getCurrentRoom().getName()) {
            case "entrance":
                switch (stage) {
                    case 1:
                        System.out.println("Stranger: Hey you there! You look like you can take on some monsters.");sleep();
                        System.out.println("Stranger: You should go talk to the wizard.");sleep();
                        System.out.println("Stranger: You can find him in the court, just north of the western plaza\n");sleep();
                    break;
                }
            break;
            case "court":
                switch (stage){
                    case 1: System.out.println("Wizard: Welcome great adventurer. Thank the force you're finally here.");sleep();
                        System.out.println("Wizard: I have heard many things about you. Not all things were good but who cares right?!");sleep();
                            System.out.println("Wizard: Perhaps you can help me with my quest to safe our little town.");sleep();
                            System.out.println("Wizard: I need glintstones to perform a protective spell on the town but it seems I lost them.");sleep();
                            System.out.println("Wizard: My memory isn't what it once was. All I still know is that I haven't left the town for awhile.");sleep();
                            System.out.println("Wizard: You need to find at least 10 glintstones and bring them back to me.\n");
                            nextStage();
                    break;
                    case 2: System.out.println("Wizard: Did you collect the glintstones yet?");sleep();
                        if(player.checkInventory(glintstone, 10)){
                            wizard.receiveItem(player.giveItemToOther(glintstone));
                            System.out.println("Wizard: Thanks for bringing me the glintstone");sleep();
                            System.out.println("Wizard: All I need now is my staff. Where is it?");sleep();
                            System.out.println("Wizard: Oh no, I forgot it at home");sleep();
                            System.out.println("Wizard: Can you get it for me? It's in the lonely cottage behind the forest to the west.");sleep();
                            System.out.println("Wizard: Do you have a sword yet? You better get one, I heard that enemies were spotted in the forest\n");
                            nextStage();
                        }
                        else{
                            System.out.println("Wizard: Time is running out, the enemy is almost on our doorstep.");sleep();
                            System.out.println("Wizard: Come back when you got enough glintstones\n");
                        }
                    break;
                    case 3: System.out.println("Wizard: Have you found the staff?");sleep();
                        if(player.checkInventory(staff, 1)){
                            wizard.receiveItem(player.giveItemToOther(staff));
                            System.out.println("Wizard: Thanks for bringing me my staff");sleep();
                            System.out.println("Wizard: I didn't really need it for the spell");sleep();
                            System.out.println("Wizard: Most of the time I just use it as a walking stick.");sleep();
                            System.out.println("Wizard: Nonetheless you helped me greatly by defeating the enemies within the towns domain.");sleep();
                            System.out.println("Wizard: Let's celebrate your victory at the pub, shall we?.\n");
                            nextStage();
                        }
                        else{
                            System.out.println("Wizard: Why would you come to me when you don't have my staff yet?");sleep();
                            System.out.println("Wizard: Come back when you got it.\n");
                        }
                        break;
                }
                break;
            case "blacksmith":
                switch (stage) {
                    case 3:
                        System.out.println("Smithy: All I have right now are swords, if you want one it will cost you 5 coins.\n");
                        break;
                    default:  System.out.println("Smithy: We're closed right now, come back later.\n");
                }
                break;
            case "pub":
                switch (stage) {
                    case 3: System.out.println("Bartender: Do You want a beer? It's only 2 coins.\n");
                        break;
                    default: System.out.println("Bartender: We're closed right now, come back later.\n");
                }
                break;
            default:
                System.out.println("There's no one to talk.\n");
        }
    }
    private void sleep(){
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { throw new RuntimeException(e);}
    }
    private void nextStage(){stage ++;}
    private void printEnemyInfo() {
        if (player.getCurrentRoom().containsEnemy()) {
            System.out.println("There are enemies in this room:");
            for (NPC enemy : player.getCurrentRoom().getNPCs().keySet()) {
                System.out.println(player.getCurrentRoom().getNPCs().get(enemy) + " " + enemy.getName() + " with " + enemy.getLife() + " life-points each\n");
            }
        }
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();

        if(player.getCurrentRoom().hasItem(itemName))
        {
            if(player.canTakeItem(player.getCurrentRoom().getItem(itemName),player.getCurrentRoom().getNumberOfItem(itemName))) {
                if (player.take(itemName)) {
                    System.out.println("You took: " + itemName + " {" + player.getCurrentRoom().getNumberOfItem(itemName) + "}");
                    player.getCurrentRoom().removeItem(player.getCurrentRoom().getItem(itemName));
                }
            }
            else System.out.println("The item is too heavy to add to your inventory");
        }
        else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.drop(itemName)) {
            System.out.println("You dropped the item: " + itemName);
        } else {
            System.out.println("There is no item here with that name");
        }
    }

    private void showInventory() {
        System.out.println(player.getShortItemDescription());
    }

    private void attack(Command command) {
        if (player.isAlive) {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know what to drop...
                System.out.println("Attack what?");
                return;
            }
            String enemyName = command.getSecondWord();
            int attack = 0;
            boolean enemyFound = false;

            for (NPC enemy : player.getCurrentRoom().getNPCs().keySet()) {

                if (enemy.getName().equals(enemyName) && enemy.isAlive()) {
                    enemyFound = true;
                    //player attacks
                    attack = player.attack();
                    enemy.takeDamage(attack);
                    if (attack == 0) System.out.println("You missed");
                    else System.out.println("You attacked  " + enemy.getName() + " with " + attack + " damage.");

                    //enemy attack automatically
                    attack = enemy.attack();
                    player.takeDamage(attack);
                    if (attack == 0) System.out.println(enemy.getName() + " missed");
                    else System.out.println(enemy.getName() + " attacked you with " + attack + " damage");
                    System.out.println();
                    //print life-points left
                    System.out.println("You have " + player.getLife() + " life-points left.");
                    System.out.println(enemy.getName() + " has " + enemy.getLife() + " life-points left.");
                    System.out.println();
                    player.getCurrentRoom().checkDeadEnemies();
                    if(!player.isAlive) {System.out.println("\nYou died!"); return; }
                }
            }
            if (!enemyFound) System.out.println("There is no enemy with the name " + enemyName);
        }
        else System.out.println("You can't attack when you're dead.");
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
