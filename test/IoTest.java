import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IoTest {
  @Test
  public void out() {
    Io io = new Io();
    io.out("hi");
    assertEquals("hi", io.getOutput().get(0));
  }
}
