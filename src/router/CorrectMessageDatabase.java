package router;

import java.util.ArrayList;
import java.util.Random;

public class CorrectMessageDatabase {
  public ArrayList correctMessages;
  private Random randomGenerator;

  public CorrectMessageDatabase() {
    randomGenerator = new Random();
    correctMessages = new ArrayList<String>();
    correctMessages.add("Affirmative.");
    correctMessages.add("That is correct.");
    correctMessages.add("Boom. Got 'em.");
    correctMessages.add("Correct.");
    correctMessages.add("Correct-o.");
    correctMessages.add("Correctamundo.");
    correctMessages.add("Yep.");
    correctMessages.add("Uh-huh.");
    correctMessages.add("You gots it.");
  }

  public String getRandomCorrectMessage() {
    int index = randomGenerator.nextInt(correctMessages.size());
    return (String)correctMessages.get(index);
  }
}
