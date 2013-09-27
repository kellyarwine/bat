package router.routeType;

import org.junit.Test;
import router.LoseMessageDatabase;
import router.WinMessageDatabase;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

public class ResultsTest {
  @Test
  public void getWinningResultTweet() {
    Results results = new Results();
    new Question().setQUESTION_LIMIT(3);
    String result_message = results.getResultTweetForQueryString("score=3").substring(14);
    ArrayList winMessages = new WinMessageDatabase().winMessages;
    ArrayList loseMessages = new LoseMessageDatabase().loseMessages;
    assertTrue(winMessages.indexOf(result_message) >= 0);
    assertTrue(loseMessages.indexOf(result_message) == -1);
  }

  @Test
  public void getLosingResultTweet() {
    Results results = new Results();
    new Question().setQUESTION_LIMIT(3);
    String result_message = results.getResultTweetForQueryString("score=1").substring(14);
    ArrayList winMessages = new WinMessageDatabase().winMessages;
    ArrayList loseMessages = new LoseMessageDatabase().loseMessages;
    assertTrue(winMessages.indexOf(result_message) == -1);
    assertTrue(loseMessages.indexOf(result_message) >= 0);
  }

}
