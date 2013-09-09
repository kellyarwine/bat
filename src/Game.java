import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {
  public Io io;
  private ArrayList<String> turnHistory;
  public static final Map<Enum, String> MESSAGES = createMessagesMap();

  public Game(Io io) {
    this.io = io;
    turnHistory = new ArrayList<String>();
  }

  public void start() {
    displayWelcomeMessage();
  }

  public void play() {
    displayAskQuestionMessage();
    String response = getUserResponse();

//    deliver feedback
//    check if turn limit reached
  }

  public void displayWelcomeMessage() {
    Enum welcome = MessageType.WELCOME;
    io.out(MESSAGES.get(welcome));
  }

  public void displayAskQuestionMessage() {
    Enum askQuestion = MessageType.ASK_QUESTION;
    io.out(MESSAGES.get(askQuestion));
  }

  private String getUserResponse() {
    return io.in();
  }

  public static enum MessageType {
    WELCOME, ASK_QUESTION
  }

  public static Map<Enum, String> createMessagesMap() {
      Map<Enum, String> result = new HashMap<Enum, String>();
      result.put(MessageType.WELCOME, "Welcome to the game!");
      result.put(MessageType.ASK_QUESTION, "Is this tweet true or false?");
      return Collections.unmodifiableMap(result);
  }
}
