package router;

import java.util.ArrayList;
import java.util.Random;

public class LoseMessageDatabase {
  public ArrayList loseMessages;
  private Random randomGenerator;

  public LoseMessageDatabase() {
    randomGenerator = new Random();
    loseMessages = new ArrayList<String>();
    loseMessages.add("I don’t know what your problem is, but I’ll bet it’s hard to pronounce.");
    loseMessages.add("I think it's time you update your MySpace page.");
    loseMessages.add("You make Jar Jar Binks look cool.");
    loseMessages.add("I see you’ve set aside this special time to humiliate yourself.");
    loseMessages.add("You! Out of the gene pool!");
    loseMessages.add("You lose. If I promise to miss you, will you go away?");
    loseMessages.add("By the power vested in me, I now pronouce you \"unfollowed.\"");
    loseMessages.add("You are cruelly depriving a village somewhere of an idiot.");
    loseMessages.add("Even Eric Smith did a better job than that.");
    loseMessages.add("#FAIL");
    loseMessages.add("#whatever");
  }

  public String getRandomLoseMessage() {
    int index = randomGenerator.nextInt(loseMessages.size());
    return (String) loseMessages.get(index);
  }
}
