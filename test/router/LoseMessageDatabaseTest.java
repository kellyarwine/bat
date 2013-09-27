package router;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LoseMessageDatabaseTest {
  LoseMessageDatabase loseMessageDatabase;

  @Before
  public void setUp() {
    loseMessageDatabase = new LoseMessageDatabase();
  }

  @Test
  public void questions() {
    assertEquals(11, loseMessageDatabase.loseMessages.size());
  }

  @Test
  public void getRandomLoseMessage() {
    String actualResult  = loseMessageDatabase.getRandomLoseMessage();
    String actualResult2 = loseMessageDatabase.getRandomLoseMessage();
    assertThat(actualResult, not(actualResult2));
    assertTrue(loseMessageDatabase.loseMessages.indexOf(actualResult) >= 0);
    assertTrue(loseMessageDatabase.loseMessages.indexOf(actualResult2) >= 0);
  }
}
