import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MockIoTest {
  MockIo mockIo;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    input.add("bye");
    input.add("bye again");
    mockIo = new MockIo(input);
  }

  @Test
  public void out() {
    mockIo.out("hi");
    assertEquals("hi", mockIo.getOutput().get(0));
  }

  @Test
  public void in() {
    assertEquals("bye", mockIo.in());
  }

  @Test
  public void subsequentIn() {
    assertEquals("bye", mockIo.in());
    assertEquals("bye again", mockIo.in());
  }
}
