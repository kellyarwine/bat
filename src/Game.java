import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {
  public Io io;
  public static final Map<Enum, String> MESSAGES = createMessagesMap();

  public Game(Io io) {
    this.io = io;
  }

  public void start() {
    Enum welcome = MessageType.WELCOME;
    io.out(MESSAGES.get(welcome));
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
