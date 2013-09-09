import java.util.Scanner;

public class SystemIo implements Io {
  public void out(String message) {
    System.out.println(message);
  }

  public String in() {
    Scanner scan = new Scanner(System.in);
    return scan.next();
  }
}
