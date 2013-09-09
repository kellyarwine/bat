import java.util.ArrayList;
import java.util.List;

public class Io {
  public List output;
  public ArrayList<String> input;

  public Io(ArrayList<String> input) {
    this.input = input;
    this.output = new ArrayList<String>();
  }

  public void out(String message) {
    output.add(message);
  }

  public List getOutput() {
    return output;
  }

  public List getInput() {
    return input;
  }

  public String in() {
    String message = input.get(0);
    input.remove(0);
    return message;
  }
}