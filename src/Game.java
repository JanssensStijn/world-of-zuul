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
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Player player;
    private Room cottage, forest, court, westPlaza, entrance, blacksmith, eastPlaza, watchTower, lookOut, pub, cellar;
    private Item coin, sword, staff, shield, armor;

    private NPC  troll, smithy, bartender, wizard, guard;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        createItems();
        createCharacters();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms
        cottage = new Room("inside the lonely cottage behind the forest");
        forest = new Room("in the forest");
        court = new Room("in the court");
        westPlaza = new Room("on the western half of the plaza");
        entrance = new Room("at the entrance");
        blacksmith = new Room("in the blacksmith's shop");
        eastPlaza = new Room("on the eastern half of the plaza");
        watchTower = new Room("in the watchtower");
        lookOut = new Room("in the look-out");
        pub = new Room("in the pub");
        cellar = new Room("in the pub's cellar");

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
        armor = new Item("shield", "the armor of a black knight", 2.4);

        //add items to rooms or characters
        cottage.addItem(staff, 1);
        watchTower.addItem(shield, 1);
        watchTower.addItem(armor, 1);
        eastPlaza.addItem(coin, 2);
        cellar.addItem(coin, 5);
        entrance.addItem(sword, 1);

    }

    /**
     * Create all the characters including the player.
     */
    private void createCharacters() {

        this.player = new Player("Human", entrance, 20, 5);
        this.troll = new NPC("troll", 20, 5);
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
        while (!finished) {
            Command command = parser.getCommandWord();
            finished = processCommand(command) || !player.isAlive;
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
        System.out.println("Type \'"+ CommandWord.HELP.toString() + "\' if you need help.");

        System.out.println();

        System.out.println(player.getLongDescription());
        System.out.println();
        printLocationInfo();
        System.out.println();
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
                if(!player.getCurrentRoom().containsEnemy()) goRoom(command);
                else System.out.println("You can't leave with an enemy on your tail");
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Player " + player.getName() + " is lost and alone, and wanders");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Possible command words are:   " + parser.showCommands());
        System.out.println();
    }

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
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

    private void printEnemyInfo() {
        if (player.getCurrentRoom().containsEnemy())
        {
            System.out.println("There are enemies in this room:\n");
            for (NPC enemy: player.getCurrentRoom().getNPCs().keySet()){
                System.out.println(player.getCurrentRoom().getNPCs().get(enemy) + " " + enemy.getName() + " with " + enemy.getLife()  + " lifepoints each\n");
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
        if (player.take(itemName)) {
            System.out.println("You took: " + itemName + " {" + player.getCurrentRoom().getNumberOfItem(itemName)+ "}");
            player.getCurrentRoom().removeItem(player.getCurrentRoom().getItem(itemName));
        } else {
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
            System.out.println("You dropped the item");
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    private void showInventory() {
        System.out.println(player.getShortItemDescription());
    }
    private void attack(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Attack what?");
            return;
        }
        String enemyName = command.getSecondWord();
        int attack = 0;
        boolean enemyFound = false;

        for (NPC enemy : player.getCurrentRoom().getNPCs().keySet()){

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
                //print lifepoints left
                System.out.println("You have " + player.getLife() + " lifepoints left.");
                System.out.println(enemy.getName() + " has " + enemy.getLife() + " lifepoints left.");
                System.out.println();
                player.getCurrentRoom().checkDeadEnemies();
            }


        }
        if(!enemyFound) System.out.println("There is no enemy with the name " + enemyName);
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
