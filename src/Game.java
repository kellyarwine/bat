import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {
  public Io io;
  public ArrayList<String> turnHistory;
  public static final Map<Enum, String> MESSAGES = createMessagesMap();
  public static final int TURN_LIMIT = 6;

  public Game(Io io) {
    this.io = io;
    turnHistory = new ArrayList<String>();
  }

  public void start() {
    displayWelcomeMessage();
  }

  public void play() {
    while (turnHistory.size() < TURN_LIMIT) {
      displayQuestion();
      String response = getUserResponse();
      storeResponse(response);
  //    deliver feedback
    }
  }

  public void displayWelcomeMessage() {
    Enum welcome = MessageType.WELCOME;
    io.out(MESSAGES.get(welcome));
  }

  public void displayQuestion() {
    Enum askQuestion = MessageType.ASK_QUESTION;
    io.out(MESSAGES.get(askQuestion));
  }

  private String getUserResponse() {
    return io.in();
  }

  private void storeResponse(String response) {
    turnHistory.add(response);
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
