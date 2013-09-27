package router.routeType;

import router.LoseMessageDatabase;
import router.WinMessageDatabase;

public class Results {
  public String queryString;

  public String getResultTweetForQueryString(String queryString) {
    this.queryString = queryString;
    String resultTweet;
    if (getScore()/Question.QUESTION_LIMIT >= .75)
      resultTweet = new WinMessageDatabase().getRandomWinMessage();
    else
      resultTweet = new LoseMessageDatabase().getRandomLoseMessage();

    return "&result_tweet=" + resultTweet;
  }

  private int getScore() {
    int scorePosition = queryString.indexOf("score=") + 6;
    String remainingString = queryString.substring(scorePosition);
    //regular expression finds any group of one or more digits
    String[] splitRemainingStringOnNumbers = remainingString.split("[^\\d]+");
    return Integer.parseInt(splitRemainingStringOnNumbers[0]);
  }
}