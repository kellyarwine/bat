import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {
  Game game;
  MockIo io;
  List expectedOutput;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    for(int i=0; i<6; i++) {
      input.add("true");
    }
    expectedOutput = new ArrayList();
    io = new MockIo(input);
    game = new Game(io);
  }

  @Test
  public void start() {
    game.start();
    expectedOutput.add(game.MESSAGES.get(Game.MessageType.WELCOME));
    assertEquals(expectedOutput, io.getOutput());
  }

  @Test
  public void play() {
    game.play();
    ArrayList expectedTurnHistory = new ArrayList();
    for(int i=0; i<6; i++) {
      expectedTurnHistory.add("true");
      expectedOutput.add(game.MESSAGES.get(Game.MessageType.ASK_QUESTION));
    }

    assertEquals(expectedOutput, io.getOutput());
    assertEquals(expectedTurnHistory, game.turnHistory);
  }
}