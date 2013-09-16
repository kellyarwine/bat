import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class QuestionsDatabaseTest {
  @Test
  public void questions() {
    QuestionsDatabase questionsDatabase = new QuestionsDatabase();
    LinkedHashMap expectedResult = new LinkedHashMap<String, Boolean>();
    expectedResult.put("True or False: Did Brian make this tweet?\nPackers halftime pep talk: \"we have to win so convincingly that the refs can't possibly screw us\"", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nLove the feeling when you write a line of code that is exactly 80 characters", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nCTA card fail. Ventra card fail. Train speed fail. Escalator fail. Jerks on train fail. OVERALL COMMUTE FAIL.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nMashable's hilarious idea of tech journalism is embarrassingly bad...", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nPeople who give up good engineering to get something done quick and cheap will not get anything else done cheaply or quickly, now or ever.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nWherein the definition of \"open\" in open-source sorta becomes ironic", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nThanks to @8thLightInc for 3 awesome years.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nPogs. Beanie Babies. Desperate Housewives. Colin Kaepernick.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\ndidja know pistachios have the highest incidence of salmonella of any food? cause they're so delicious. bacteria can't resist.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nCoffee at my folks' place is like 5% strength.", true);
    expectedResult.put("True or False: Did Brian make this tweet?\nMy least favorite ML derivative is HTML", true);
    assertEquals(11, questionsDatabase.questions.size());
    assertEquals(expectedResult, questionsDatabase.questions);
  }

}
