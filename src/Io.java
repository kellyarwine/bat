import java.util.ArrayList;
import java.util.List;

public class Io {
  public List output;
  public List input;

  public Io() {
//    this.input = input;
    this.output = new ArrayList<String>();
  }

  public void out(String message) {
    output.add(message);
  }

  public List getOutput() {
    return output;
  }
}
