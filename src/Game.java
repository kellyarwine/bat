public class Game {
  public Game(Io io) {
    io.out("Welcome to the game!");
  }

  public static void main(String[] args) {
    new Game(new SystemIo());
  }
}
