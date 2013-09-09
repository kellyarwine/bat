import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class IoTest {
  Io io;

  @Before
  public void setUp() {
    ArrayList input = new ArrayList();
    input.add("bye");
    input.add("bye again");
    io = new Io(input);
  }

  @Test
  public void out() {
    io.out("hi");
    assertEquals("hi", io.getOutput().get(0));
  }

  @Test
  public void in() {
    assertEquals("bye", io.in());
  }

  @Test
  public void subsequentIn() {
    assertEquals("bye", io.in());
    assertEquals("bye again", io.in());
  }
}
