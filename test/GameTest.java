import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {
  Game game;
  MockIo io;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    io = new MockIo(input);
    game = new Game(io);
  }

  @Test
  public void welcomeMessage() {
    game.start();
    Enum welcome = Game.MessageType.WELCOME;
    String expectedResult = game.MESSAGES.get(welcome);
    String actualResult = (String)io.getOutput().get(0);
    assertEquals(expectedResult, actualResult);
  }
}