import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {
  Game game;
  MockIo io;
  List expectedResult;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    input.add("true");
    expectedResult = new ArrayList();
    io = new MockIo(input);
    game = new Game(io);
  }

  @Test
  public void start() {
    game.start();
    expectedResult.add(game.MESSAGES.get(Game.MessageType.WELCOME));
    assertEquals(expectedResult, io.getOutput());
  }

  @Test
  public void play() {
    game.play();
    expectedResult.add(game.MESSAGES.get(Game.MessageType.ASK_QUESTION));
    assertEquals(expectedResult, io.getOutput());
    assertEquals("true", io.getOutput());
  }
}