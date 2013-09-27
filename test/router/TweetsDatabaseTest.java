package router;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TweetsDatabaseTest {
  TweetsDatabase tweetsDatabase;

  @Before
  public void setUp() {
    tweetsDatabase = new TweetsDatabase();
  }

  @Test
  public void questions() {
    assertEquals(90, tweetsDatabase.tweets.size());
  }

  @Test
  public void getRandomTweet() {
    String actualResult = tweetsDatabase.getRandomTweet();
    assertTrue(tweetsDatabase.tweets.get(actualResult) != null);
  }

  @Test
  public void getTweeter() {
    String actualResult = tweetsDatabase.getTweeter("wouldn't it be great if honks were replaced with derp? *derp derp* get off the road! *derrrrpppp*");
    String expectedResult = "Jim Suchy";
    assertEquals(expectedResult, actualResult);
  }
}
