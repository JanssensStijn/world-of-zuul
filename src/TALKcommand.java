import java.util.concurrent.TimeUnit; //used to create waiting time to simulate conversation

/**Executed whenever a talk command is given
 * child class of 'Command'
 *
 *  @author Stijn Janssens
 *  @version 2023/05/23
 */
public class TALKcommand extends Command{

    /**initialising of new talk command using the constructor of the parent class
     *
     * @param firstWord is the first typed word of the command
     * @param secondWord is the second typed word of the command
     */
    public TALKcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    /** method for talking
     *
     * @param player
     * @return true or false, game is finished = true otherwise false
     */
    @Override
    public boolean execute(Player player) {
        interactions(player);
        return false;
    }

    /** method that contains all interactions with characters
     *
     * @param player
     */
    private void interactions(Player player){
        switch(player.getCurrentRoom().getName()) {
            case "entrance":
                switch (player.getCurrentStage()) {
                    case STAGE1:
                        System.out.println("Stranger: Hey you there! You look like you can take on some monsters.");sleep();
                        System.out.println("Stranger: You should go talk to the wizard.");sleep();
                        System.out.println("Stranger: You can find him in the court, just north of the western plaza\n");sleep();
                        break;
                }
                break;
            case "court":
                switch (player.getCurrentStage()){
                    case STAGE1: System.out.println("Wizard: Welcome great adventurer. Thank the force you're finally here.");sleep();
                        System.out.println("Wizard: I have heard many things about you. Not all things were good but who cares right?!");sleep();
                        System.out.println("Wizard: Perhaps you can help me with my quest to safe our little town.");sleep();
                        System.out.println("Wizard: I need glintstones to perform a protective spell on the town but it seems I lost them.");sleep();
                        System.out.println("Wizard: My memory isn't what it once was. All I still know is that I haven't left the town for awhile.");sleep();
                        System.out.println("Wizard: You need to find at least 10 glintstones and bring them back to me.\n");
                        player.nextStage();
                        break;
                    case STAGE2: System.out.println("Wizard: Did you collect the glintstones yet?");sleep();
                        if(player.checkInventory(Item.GLINTSTONE, 10)){
                            player.getCurrentRoom().getCharacter().take(player.drop(Item.GLINTSTONE,10));
                            System.out.println("Wizard: Thanks for bringing me the glintstone");sleep();
                            System.out.println("Wizard: All I need now is my staff. Where is it?");sleep();
                            System.out.println("Wizard: Oh no, I forgot it at home");sleep();
                            System.out.println("Wizard: Can you get it for me? It's in the lonely cottage behind the forest to the west.");sleep();
                            System.out.println("Wizard: Do you have a sword yet? You better get one, I heard that enemies were spotted in the forest\n");
                            player.nextStage();
                        }
                        else{
                            System.out.println("Wizard: Time is running out, the enemy is almost on our doorstep.");sleep();
                            System.out.println("Wizard: Come back when you got enough glintstones\n");
                        }
                        break;
                    case STAGE3: System.out.println("Wizard: Have you found the staff?");sleep();
                        if(player.checkInventory(Item.STAFF, 1)){
                            player.getCurrentRoom().getCharacter().take(player.drop(Item.STAFF));
                            System.out.println("Wizard: Thanks for bringing me my staff");sleep();
                            System.out.println("Wizard: I didn't really need it for the spell");sleep();
                            System.out.println("Wizard: Most of the time I just use it as a walking stick.");sleep();
                            System.out.println("Wizard: Nonetheless you helped me greatly by defeating the enemies within the towns domain.");sleep();
                            System.out.println("Wizard: Let's celebrate your victory at the pub, shall we?.\n");
                            player.nextStage();
                        }
                        else{
                            System.out.println("Wizard: Why would you come to me when you don't have my staff yet?");sleep();
                            System.out.println("Wizard: Come back when you got it.\n");
                        }
                        break;
                }
                break;
            case "blacksmith":
                switch (player.getCurrentStage()) {
                    case STAGE3:
                        System.out.println("Smithy: All I have right now are swords, if you want one it will cost you 5 coins.\n");
                        break;
                    default:  System.out.println("Smithy: We're closed right now, come back later.\n");
                }
                break;
            case "pub":
                switch (player.getCurrentStage()) {
                    case STAGE3: System.out.println("Bartender: Do You want a beer? It's only 2 coins.\n");
                        break;
                    default: System.out.println("Bartender: We're closed right now, come back later.\n");
                }
                break;
            default:
                System.out.println("There's no one to talk to.\n");
        }
    }
    private void sleep(){
        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { throw new RuntimeException(e);}
    }
}
