package router;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WinMessageDatabaseTest {
  WinMessageDatabase winMessageDatabase;

  @Before
  public void setUp() {
    winMessageDatabase = new WinMessageDatabase();
  }

  @Test
  public void questions() {
    assertEquals(9, winMessageDatabase.winMessages.size());
  }

  @Test
  public void getRandomWinMessage() {
    String actualResult  = winMessageDatabase.getRandomWinMessage();
    String actualResult2 = winMessageDatabase.getRandomWinMessage();
    assertThat(actualResult, not(actualResult2));
    assertTrue(winMessageDatabase.winMessages.indexOf(actualResult) >= 0);
    assertTrue(winMessageDatabase.winMessages.indexOf(actualResult2) >= 0);
  }
}
