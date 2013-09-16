import java.util.*;

public class Game {
  public Io io;
  public static final Map<Enum, String> MESSAGES = createMessagesMap();
  public static final int TURN_LIMIT = 6;
  public LinkedHashMap<String, Boolean> questions;
  public LinkedHashMap<String, Boolean> turnHistory;

  public Game(Io io) {
    this.io = io;
    questions = new QuestionsDatabase().questions;
    turnHistory = new LinkedHashMap<String, Boolean>();
  }

  public void start() {
    displayWelcomeMessage();
  }

  public void play() {
    while (turnHistory.size() < TURN_LIMIT) {
      displayQuestion();
      boolean response = getUserResponse();
      boolean correctness = validateUserResponse(response);
      storeResponse(getQuestion(), correctness);
    }
    displayGameResult();
  }

  private void displayGameResult() {
    Enum game_result = MessageText.GAME_RESULT;
    String gameResultText = MESSAGES.get(game_result);
    io.out(gameResultText + summarizeTurnHistory());
  }

  private boolean validateUserResponse(boolean response) {
    boolean answer = getAnswer();
    return answer == response;
  }

  private int getQuestionNumber() {
    return turnHistory.size();
  }

  private String getQuestion() {
    int questionNumber = getQuestionNumber();
    ArrayList<String> questionList = new ArrayList<String>(questions.keySet());
    return questionList.get(questionNumber);
  }

  private boolean getAnswer() {
    int questionNumber = getQuestionNumber();
    ArrayList<Boolean> answerList = new ArrayList(questions.values());
    return answerList.get(questionNumber);
  }

  public void displayWelcomeMessage() {
    Enum welcome = MessageText.WELCOME;
    io.out(MESSAGES.get(welcome));
  }

  private void displayQuestion() {
    io.out(getQuestion());
  }

  private boolean getUserResponse() {
    String response = io.in();
    return Boolean.parseBoolean(response);
  }

  private void storeResponse(String question, Boolean correctness) {
    turnHistory.put(question, correctness);
  }

  public int summarizeTurnHistory() {
    int trueCount = 0;
    ArrayList<Boolean> correctnessList = new ArrayList<Boolean>(turnHistory.values());
    for(int i=0; i< turnHistory.size(); i++) {
       trueCount += correctnessList.get(i) == true ? 1 : 0;
    }
    return trueCount;
  }

  public static enum MessageText {
    WELCOME, GAME_RESULT
  }

  public static Map<Enum, String> createMessagesMap() {
      Map<Enum, String> result = new HashMap<Enum, String>();
      result.put(MessageText.WELCOME, "Welcome to the game!");
      result.put(MessageText.GAME_RESULT, "Here is your final score: ");
      return Collections.unmodifiableMap(result);
  }
}
