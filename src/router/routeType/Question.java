package router.routeType;

import router.TweetsDatabase;
import builder.httpMethod.HttpMethod;
import builder.httpMethod.HttpMethodMapBuilder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Question implements RouteType {
  private HashMap httpMethodMap;
  public HashMap request;
  public String queryString;
  public static int QUESTION_LIMIT = 3;

  public Question() {
    httpMethodMap = new HttpMethodMapBuilder().build();
  }

  public byte[] buildResponse(File routeFile, HashMap request) throws IOException, ParseException {
    this.request = request;
    this.queryString = (String)request.get("queryString");
    updateRequestWithQuestionData();
    File updatedRouteFile = handleLastQuestion(routeFile);
    HttpMethod httpMethod = (HttpMethod)httpMethodMap.get(this.request.get("httpMethod"));
    return httpMethod.get(updatedRouteFile, this.request);
  }

  private void updateRequestWithQuestionData() {
    addTweet();
    request.put("queryString", queryString);
  }

  public File handleLastQuestion(File routeFile) {
    if (isLastQuestion()) {
      addResultTweet();
      return updateRouteFile(routeFile);
    }
    else
      return routeFile;
  }

  private void addResultTweet() {
    queryString +=  new Results().getResultTweetForQueryString(queryString);
    request.put("queryString", queryString);
  }

  private File updateRouteFile(File routeFile) {
      String updatedRouteFileString = routeFile.toString().replace("question", "results");
      File updatedRouteFile = new File(updatedRouteFileString);
      return updatedRouteFile;
  }

  private void addTweet() {
    queryString += "&tweet=" + getRandomTweet();
  }

  private String getRandomTweet() {
    return new TweetsDatabase().getRandomTweet();
  }

  private int getQuestionNumber() {
    int questionNumberPosition = queryString.indexOf("question=") + 9;
    String remainingString = queryString.substring(questionNumberPosition);
    //regular expression finds any group of one or more digits
    String[] splitRemainingStringOnNumbers = remainingString.split("[^\\d]+");
    int questionNumber = Integer.parseInt(splitRemainingStringOnNumbers[0]);
    return questionNumber;
  }

  private boolean isLastQuestion() {
    return getQuestionNumber() == QUESTION_LIMIT;
  }

  public void setQUESTION_LIMIT(int QUESTION_LIMIT) {
    this.QUESTION_LIMIT = QUESTION_LIMIT;
  }

  public void setRequest(HashMap request) {
    this.request = request;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public String getQueryString() {
    return this.queryString;
  }
}