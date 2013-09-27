import http.io.Io;
import http.io.SystemIo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    Io io = new SystemIo();
    new Menu().display(io);
  }
}