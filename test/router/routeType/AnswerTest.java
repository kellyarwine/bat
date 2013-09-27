package router.routeType;

import org.junit.Before;
import org.junit.Test;
import router.CorrectMessageDatabase;
import router.IncorrectMessageDatabase;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class AnswerTest {
  File workingDirectory;
  File publicDirectory;
  Answer answer;
  File routeFile;
  ArrayList incorrectMessages;
  ArrayList correctMessages;

  @Before
  public void setUp() {
    workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "batRouter");
    publicDirectory = new File(workingDirectory, "public/");
    answer = new Answer();
    routeFile = new File(publicDirectory, "answer.html");
    incorrectMessages = new IncorrectMessageDatabase().incorrectMessages;
    correctMessages = new CorrectMessageDatabase().correctMessages;
  }

  @Test
  public void incorrectAnswer() throws IOException, ParseException {
    HashMap request = getRequestWithUrlAndUserGuess("/answer", "question=1&tweet=wouldn't it be great if honks were replaced with derp? *derp derp* get off the road! *derrrrpppp*&score=0&user_guess=true");
    answer.buildResponse(routeFile, request);
    String queryString = (String)answer.request.get("queryString");
    String answer = getAnswer(queryString);
    assertTrue(incorrectMessages.indexOf(answer) >= 0);
    assertTrue(correctMessages.indexOf(answer) == -1);
  }

  @Test
  public void correctAnswer() throws IOException, ParseException {
    HashMap request = getRequestWithUrlAndUserGuess("/answer", "question=1&tweet=wouldn't it be great if honks were replaced with derp? *derp derp* get off the road! *derrrrpppp*&score=0&user_guess=false");
    answer.buildResponse(routeFile, request);
    String queryString = (String)answer.request.get("queryString");
    String answer = getAnswer(queryString);
    assertTrue(incorrectMessages.indexOf(answer) == -1);
    assertTrue(correctMessages.indexOf(answer) >= 0);
  }

  @Test
  public void anotherCorrectAnswer() throws IOException, ParseException {
    HashMap request = getRequestWithUrlAndUserGuess("/answer", "question=1&tweet=Packers halftime pep talk: we have to win so convincingly that the refs can't possibly screw us&score=0&user_guess=true");
    answer.buildResponse(routeFile, request);
    String queryString = (String)answer.request.get("queryString");
    String answer = getAnswer(queryString);
    assertTrue(incorrectMessages.indexOf(answer) == -1);
    assertTrue(correctMessages.indexOf(answer) >= 0);
  }

  @Test
  public void getTweetReturnsNonHexTweet() throws IOException, ParseException {
    answer.setQueryString("tweet=Going+to+the+store+hungry+with+no+list+is+a+recipe+to+buy+some+bullshit.+Remember+your+lists+people%21++I+have+three+bags+of+snyders+pretzels%21");
    String expectedResult = "Going to the store hungry with no list is a recipe to buy some bullshit. Remember your lists people!  I have three bags of snyders pretzels!";
    String actualResult = answer.getTweet();
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void getTweetReturnsEmptyString() throws IOException, ParseException {
    answer.setQueryString("");
    String expectedResult = "";
    String actualResult = answer.getTweet();
    assertEquals(expectedResult, actualResult);
  }

  private HashMap getRequestWithUrlAndUserGuess(String url, String queryString) {
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
    request.put("queryString", queryString);
    return request;
  }

  private String getAnswer(String queryString) {
    String[] keyValuePairs = queryString.split("&");
    for(int i=0; i<keyValuePairs.length; i++) {
      String keyValuePairString = keyValuePairs[i];
      String[] keyValuePair = keyValuePairString.split("=");
      if (keyValuePair[0].equals("answer"))
        return keyValuePair[1];
    }
    return "";
  }
}