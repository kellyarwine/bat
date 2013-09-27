package router;

import java.util.ArrayList;
import java.util.Random;

public class IncorrectMessageDatabase {
  public ArrayList incorrectMessages;
  private Random randomGenerator;

  public IncorrectMessageDatabase() {
    randomGenerator = new Random();
    incorrectMessages = new ArrayList<String>();
    incorrectMessages.add("Negative.");
    incorrectMessages.add("That is incorrect.");
    incorrectMessages.add("Boom.  You do NOT got 'em.");
    incorrectMessages.add("Wrong.");
    incorrectMessages.add("Incorrect-o.");
    incorrectMessages.add("Incorrectamundo.");
    incorrectMessages.add("Nope.");
    incorrectMessages.add("Nuh-uh.");
    incorrectMessages.add("Not it.");
  }

  public String getRandomIncorrectMessage() {
    int index = randomGenerator.nextInt(incorrectMessages.size());
    return (String) incorrectMessages.get(index);
  }
}
