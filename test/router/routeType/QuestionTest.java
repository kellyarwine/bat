package router.routeType;

import org.junit.Before;
import org.junit.Test;
import router.TweetsDatabase;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class QuestionTest {
  File workingDirectory;
  File publicDirectory;
  Question question;
  File routeFile;

  @Before
  public void setUp() {
    workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "batRouter");
    publicDirectory = new File(workingDirectory, "public/");
    question = new Question();
    routeFile = new File(publicDirectory, "question.html");
  }

  @Test
  public void questionOne() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/question", "0");
    question.buildResponse(routeFile, request);
    String queryString = (String)question.request.get("queryString");
    String actualResult = getTweet(queryString);

    HashMap tweets = new TweetsDatabase().tweets;
    assertTrue(tweets.get(actualResult) != null);

    int questionNumber = getQuestionNumber(queryString);
    assertEquals(0, questionNumber);
  }

  @Test
  public void questionTwo() throws IOException, ParseException {
    HashMap request = getRequestWithUrl("/question", "1");
    question.buildResponse(routeFile, request);
    String queryString = (String)question.request.get("queryString");
    String tweet = getTweet(queryString);

    HashMap tweets = new TweetsDatabase().tweets;
    assertTrue(tweets.get(tweet) != null);

    int questionNumber = getQuestionNumber(queryString);
    assertEquals(1, questionNumber);
  }

  @Test
  public void buildFrom() throws IOException, ParseException {
    File routeFile = new File(publicDirectory, "question.html");
    HashMap request = getRequestWithUrl("/question", "1");
    String actualResult = new String(question.buildResponse(routeFile, request));
//    assertEquals(expectedBuildFromBody(), actualResult);
    assertTrue(actualResult.length() > 0);
  }

  @Test
  public void updateRouteFileIfLastQuestion() {
    HashMap request = getRequestForLastTurn();
    question.setRequest(request);
    question.setQueryString((String)request.get("queryString"));

    File expectedRouteFile = new File(publicDirectory, "/results");
    File actualRouteFile = question.handleLastQuestion(new File(publicDirectory, "/question"));
    assertEquals(expectedRouteFile, actualRouteFile);

    String actualResultTweet = getResultTweet(question.getQueryString());
    assertTrue(actualResultTweet != "");

  }

  @Test
  public void doNotUpdateRouteFileIfNotLastQuestion() {
    HashMap request = getRequestWithUrl("/question", "1");
    question.setRequest(request);
    question.setQueryString((String)request.get("queryString"));

    File expectedRouteFile = new File(publicDirectory, "/question");
    File actualRouteFile = question.handleLastQuestion(new File(publicDirectory, "/question"));
    assertEquals(expectedRouteFile, actualRouteFile);

    String actualResultTweet = getResultTweet(question.getQueryString());
    assertEquals("", actualResultTweet);
  }

  private HashMap getRequestWithUrl(String url, String questionNumber) {
    HashMap request = new HashMap();
    request.put("httpMethod", "GET");
    request.put("url", url);
    request.put("httpProtocol", "HTTP/1.1");
    request.put("Host", "localhost:5000");
    request.put("Connection", "keep-alive");
    request.put("Content-Length", "15");
    request.put("Cache-Control", "max-age=0");
    request.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    request.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
    request.put("Accept-Encoding", "gzip,deflate,sdch");
    request.put("Accept-Language", "en-US,en;q=0.8");
    request.put("Cookie", "textwrapon=false; wysiwyg=textarea");
    request.put("queryString", "question=" + questionNumber + "&score=0");
    return request;
  }

  private HashMap getRequestForLastTurn() {
    HashMap request = new HashMap();
    request.put("httpMethod", "GET");
    request.put("url", "/question");
    request.put("httpProtocol", "HTTP/1.1");
    request.put("Host", "localhost:5000");
    request.put("Connection", "keep-alive");
    request.put("Content-Length", "15");
    request.put("Cache-Control", "max-age=0");
    request.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    request.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
    request.put("Accept-Encoding", "gzip,deflate,sdch");
    request.put("Accept-Language", "en-US,en;q=0.8");
    request.put("Cookie", "textwrapon=false; wysiwyg=textarea");
    request.put("queryString", "question=3&score=0&user_guess=true");
    return request;
  }

  private String getTweet(String queryString) {
    String[] keyValuePairs = queryString.split("&");
    for(int i=0; i<keyValuePairs.length; i++) {
      String keyValuePairString = keyValuePairs[i];
      String[] keyValuePair = keyValuePairString.split("=");
      if (keyValuePair[0].equals("tweet"))
        return keyValuePair[1];
    }
    return "";
  }

  private String getResultTweet(String queryString) {
    String[] keyValuePairs = queryString.split("&");
    for(int i=0; i<keyValuePairs.length; i++) {
      String keyValuePairString = keyValuePairs[i];
      String[] keyValuePair = keyValuePairString.split("=");
      if (keyValuePair[0].equals("result_tweet"))
        return keyValuePair[1];
    }
    return "";
  }

  private int getQuestionNumber(String queryString) {
    int questionNumberPosition = queryString.indexOf("question=") + 9;
    String remainingString = queryString.substring(questionNumberPosition);
    //regular expression finds any group of one or more digits
    String[] splitRemainingStringOnNumbers = remainingString.split("[^\\d]+");
    int questionNumber = Integer.parseInt(splitRemainingStringOnNumbers[0]);
    return questionNumber;
  }
}
