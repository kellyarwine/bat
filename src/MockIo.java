import java.util.ArrayList;
import java.util.List;

public class MockIo implements Io {
  private List output;
  private ArrayList<String> input;

  public MockIo(ArrayList<String> input) {
    this.input = input;
    this.output = new ArrayList<String>();
  }

  public void out(String message) {
    output.add(message);
  }

  public List getOutput() {
    return output;
  }

  public String in() {
    String message = input.get(0);
    input.remove(0);
    return message;
  }
}
