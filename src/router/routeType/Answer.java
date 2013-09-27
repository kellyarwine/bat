package router.routeType;

import builder.httpMethod.HttpMethod;
import builder.httpMethod.HttpMethodMapBuilder;
import router.CorrectMessageDatabase;
import router.IncorrectMessageDatabase;
import router.TweetsDatabase;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Answer implements RouteType {
  private HashMap httpMethodMap;
  public HashMap request;
  private String queryString;

  public Answer() {
    httpMethodMap = new HttpMethodMapBuilder().build();
  }

  public byte[] buildResponse(File routeFile, HashMap request) throws IOException, ParseException {
    this.request = request;
    this.queryString = (String)request.get("queryString");
    updateRequestWithAnswerData();
    HttpMethod httpMethod = (HttpMethod)httpMethodMap.get(this.request.get("httpMethod"));
    return httpMethod.get(routeFile, this.request);
  }

  private void updateRequestWithAnswerData() {
    updateScore();
    addAnswer();
    addTweeter();
    addImage();
    incrementQuestionNumber();
    request.put("queryString", queryString);
  }

  private void updateScore() {
    int oldScore = getScore();
    if (isUserCorrect())
      queryString = queryString.replace("&score=" + oldScore, "&score=" + (oldScore + 1));
  }

  private void addAnswer() {
    String answer;
    if (isUserCorrect())
      answer = new CorrectMessageDatabase().getRandomCorrectMessage();
    else
      answer = new IncorrectMessageDatabase().getRandomIncorrectMessage();

    queryString += "&answer=" + answer + "";
  }

  private void addTweeter() {
    String tweeter = new TweetsDatabase().getTweeter(getTweet());
    if (!tweeter.equals("Brian"))
      queryString += "&tweeter=This was a tweet made by " + tweeter + ".";
    else
      queryString += "&tweeter=";
  }

  private void addImage() {
    queryString += "&image=brian_pissed.jpg";
  }

  private void incrementQuestionNumber() {
    int questionNumber = getQuestionNumber();
    int nextQuestionNumber = questionNumber + 1;
    queryString = queryString.replace("question=" + questionNumber, "question=" + nextQuestionNumber);
  }

  private int getQuestionNumber() {
    int questionNumberPosition = queryString.indexOf("question=") + 9;
    String remainingString = queryString.substring(questionNumberPosition);
    //regular expression finds any group of one or more digits
    String[] splitRemainingStringOnNumbers = remainingString.split("[^\\d]+");
    return Integer.parseInt(splitRemainingStringOnNumbers[0]);
  }

  private boolean getUserGuess() {
    int userGuessPosition = queryString.indexOf("user_guess=") + 11;
    String remainingString = queryString.substring(userGuessPosition);
    return Boolean.parseBoolean(remainingString);
  }

  private int getScore() {
    int scorePosition = queryString.indexOf("score=") + 6;
    String remainingString = queryString.substring(scorePosition);
    //regular expression finds any group of one or more digits
    String[] splitRemainingStringOnNumbers = remainingString.split("[^\\d]+");
    return Integer.parseInt(splitRemainingStringOnNumbers[0]);
  }

  public String getTweet() {
    String[] keyValuePairs = queryString.split("&");
    for(int i=0; i<keyValuePairs.length; i++) {
      String keyValuePairString = keyValuePairs[i];
      String[] keyValuePair = keyValuePairString.split("=");
      if (keyValuePair[0].equals("tweet")) {
        return fromHexToString(keyValuePair[1]);
      }
    }
    return "";
  }

  private boolean isUserCorrect() {
    boolean userGuess = getUserGuess();
    String tweeter = new TweetsDatabase().getTweeter(getTweet());
    if (tweeter.equals("Brian") && userGuess)
      return true;
    else if (!tweeter.equals("Brian") && !userGuess)
      return true;
    else
      return false;
  }

  private String fromHexToString(String queryString) {
    Pattern pattern = Pattern.compile("\\%..");
    Matcher m = pattern.matcher(queryString);

    while (m.find()) {
      String hex = m.group().substring(1);
      int chr = Integer.parseInt(hex, 16);
      queryString = queryString.replace("%" + hex, "" + (char) chr);
    }

    queryString = queryString.replace("+", " ");
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }
}