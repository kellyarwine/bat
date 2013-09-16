import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {
  Game game;
  MockIo io;
  List expectedOutput;
  ArrayList questionsList;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    input.add("true");
    input.add("true");
    input.add("true");
    input.add("true");
    input.add("true");
    input.add("false");
    io = new MockIo(input);
    game = new Game(io);
    LinkedHashMap questions = new QuestionsDatabase().questions;
    questionsList = new ArrayList(questions.keySet());
  }

  @Test
  public void start() {
    game.start();
    expectedOutput = new ArrayList();
    expectedOutput.add(game.MESSAGES.get(Game.MessageText.WELCOME));
    assertEquals(expectedOutput, io.getOutput());
  }

  @Test
  public void play() {
    game.play();
    LinkedHashMap expectedTurnHistory = new LinkedHashMap<String, Boolean>();
    expectedTurnHistory.put(questionsList.get(0), true);
    expectedTurnHistory.put(questionsList.get(1), true);
    expectedTurnHistory.put(questionsList.get(2), true);
    expectedTurnHistory.put(questionsList.get(3), true);
    expectedTurnHistory.put(questionsList.get(4), true);
    expectedTurnHistory.put(questionsList.get(5), false);
    assertEquals(expectedTurnHistory, game.turnHistory);

    expectedOutput = new ArrayList();
    expectedOutput.add(questionsList.get(0));
    expectedOutput.add(questionsList.get(1));
    expectedOutput.add(questionsList.get(2));
    expectedOutput.add(questionsList.get(3));
    expectedOutput.add(questionsList.get(4));
    expectedOutput.add(questionsList.get(5));
    expectedOutput.add(game.MESSAGES.get(Game.MessageText.GAME_RESULT) + 5);
    assertEquals(expectedOutput, io.getOutput());
  }

  @Test
  public void summarizeTurnHistory() {
    LinkedHashMap expectedTurnHistory = new LinkedHashMap<String, Boolean>();
    for(int i=0; i<6; i++) {
      expectedTurnHistory.put("True or false " + i, true);
    }
    game.turnHistory = expectedTurnHistory;
    assertEquals(6, game.summarizeTurnHistory());
  }
}