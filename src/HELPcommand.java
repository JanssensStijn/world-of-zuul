import java.util.Arrays;

public class HELPcommand extends Command{

    private Parser parser;

    public HELPcommand(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
        parser = new Parser();
    }

    @Override
    public boolean execute(Player player) {
        System.out.println(player.getName() + " is lost and alone, and wanders around in a dark world.");
        System.out.println();
        System.out.println("Possible command words are:");
        Arrays.stream(CommandWord.values()).filter(keyword -> !keyword.equals(CommandWord.UNKNOWN)).forEach(keyword -> System.out.print(keyword + " ")); //commands nog uitlezen van enum
        System.out.println("\n");

        return false;
    }
}
