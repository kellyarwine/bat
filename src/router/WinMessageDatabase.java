package router;

import java.util.ArrayList;
import java.util.Random;

public class WinMessageDatabase {
  public ArrayList winMessages;
  private Random randomGenerator;

  public WinMessageDatabase() {
    randomGenerator = new Random();
    winMessages = new ArrayList<String>();
    winMessages.add("Good Job.  Now get a life.");
    winMessages.add("Good job.  Now if could only learn to write code as well as you play this game.");
    winMessages.add("Congratulation, you win.  Now if only you had spent that time doing something productive.");
    winMessages.add("Nice going.  Now good luck getting that 5 minutes of you life back.");
    winMessages.add("You win...but that's not saying much.");
    winMessages.add("Huh.  If you were a Star Wars movie, your title would be \"Star Wars Episode 1: A New Dope\"");
    winMessages.add("Theory confirmed.  It really is possible to be a winner and a loser at the same time.");
    winMessages.add("Huh. Stalker, much?");
    winMessages.add("#SHOWOFF");
  }

  public String getRandomWinMessage() {
    int index = randomGenerator.nextInt(winMessages.size());
    return (String)winMessages.get(index);
  }
}
