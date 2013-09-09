public class Game {
  public Io io;

  public Game(Io io) {
    this.io = io;
  }

  public void start() {
    io.out("Welcome to the game!");
  }
}
