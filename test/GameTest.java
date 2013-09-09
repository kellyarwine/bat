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
    assertEquals("Welcome to the game!", io.getOutput().get(0));
  }
}