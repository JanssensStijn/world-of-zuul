/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a text based adventure game.
 * This main class creates and initialises all the others: it creates all
 * rooms, items, characters and the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Stijn Janssens
 * @version 2023/05/23
 */

public class Game {
    private Parser parser;
    private Player player;
    private Room cottage, forest, court, westPlaza, entrance, blacksmith, eastPlaza, watchTower, lookOut, pub, cellar;
    private Character smithy, bartender, wizard, stranger;
    private Fighter troll;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        createCharacters();
        createItems();
        parser = new Parser();
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        boolean finished = false;
        Command command;
        while (!finished && player.isAlive() && player.getCurrentStage() != Stages.STAGE4) {
            command = parser.getCommand();
            finished = command.execute(player);
        }
        System.out.println("Thank you for playing.  Good bye.");
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
     * Add all items to their corresponding room or character
     */
    private void createItems() {

        //add items to rooms or characters
        cottage.addItem(Item.STAFF, 1);
        watchTower.addItem(Item.MAP, 1);
        lookOut.addItem(Item.ARMOR, 1);
        lookOut.addItem(Item.BROWNIE, 1);
        eastPlaza.addItem(Item.COIN, 2);
        cellar.addItem(Item.COIN, 5);

        entrance.addItem(Item.GLINTSTONE, 1);
        pub.addItem(Item.GLINTSTONE, 2);
        lookOut.addItem(Item.GLINTSTONE, 5);
        westPlaza.addItem(Item.GLINTSTONE, 1);
        eastPlaza.addItem(Item.GLINTSTONE, 1);

        smithy.addItem(Item.SWORD, 1);
        bartender.addItem(Item.BEER, 1);

        //for test purposes only
        /*
        player.take(Item.GLINTSTONE, 10);
        player.nextStage();
        player.take(Item.COIN, 7);
        player.nextStage();
        player.take(Item.SWORD, 1);
        player.take(Item.STAFF, 1);
        */
    }

    /**
     * Create all the characters including the player.
     */
    private void createCharacters() {

        this.player = new Player("Player", entrance,10, 20, 5);
        this.troll = new Fighter("troll", 20, 5);
        this.wizard = new Character("wizard");
        this.smithy = new Character("smithy");
        this.bartender = new Character("bartender");
        this.stranger = new Character("stranger");
        entrance.setCharacter(stranger);
        court.setCharacter(wizard);
        blacksmith.setCharacter(smithy);
        pub.setCharacter(bartender);
        forest.addEnemy(troll, 2);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a text base adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");

        System.out.println();

        player.showInventory();
        System.out.println();
        System.out.println("You're " + player.getCurrentRoom().getDescription());
        System.out.println();
    }

    /**
     * main method
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
