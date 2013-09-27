package router;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class TweetsDatabase {
  public LinkedHashMap tweets;
  private Random randomGenerator;

  public TweetsDatabase() {
    randomGenerator = new Random();
    tweets = new LinkedHashMap<String, Boolean>();
    tweets.put("Packers halftime pep talk: we have to win so convincingly that the refs can't possibly screw us", "Brian");
    tweets.put("Love the feeling when you write a line of code that is exactly 80 characters", "Brian");
    tweets.put("CTA card fail. Ventra card fail. Train speed fail. Escalator fail. Jerks on train fail. OVERALL COMMUTE FAIL.", "Brian");
    tweets.put("Mashable's hilarious idea of tech journalism is embarrassingly bad...", "Brian");
    tweets.put("People who give up good engineering to get something done quick and cheap will not get anything else done cheaply or quickly, now or ever.", "Brian");
    tweets.put("Wherein the definition of open in open-source sorta becomes ironic", "Brian");
    tweets.put("Thanks to @8thLightInc for 3 awesome years.", "Brian");
    tweets.put("Pogs. Beanie Babies. Desperate Housewives. Colin Kaepernick.", "Brian");
    tweets.put("didja know pistachios have the highest incidence of salmonella of any food? cause they're so delicious. bacteria can't resist.", "Brian");
    tweets.put("Coffee at my folks' place is like 5% strength.", "Brian");
    tweets.put("My least favorite ML derivative is HTML", "Brian");
    tweets.put("Wasn't surprised at this morning's sighting of the not-so-rare double bird. Traffic really brings out the worst in people.", "Mike Ebert");
    tweets.put("Ahhh, the communicative power of a good whiteboard-ing session", "Mike Ebert");
    tweets.put("Are hobbits early risers? I ate before 6 and now I'm definiterly ready for 2nd breakfast at 9— which means I'll also be hungry at elevenses…", "Mike Ebert");
    tweets.put("Google Reader shutting down is my dealer closing shop. Sure I could find another- but I should really just lay off the information crackpipe", "Mike Ebert");
    tweets.put("Drafting an angry tweet and deleting it before sending is the new therapy. ‪#ifeelbetternow", "Mike Ebert");
    tweets.put("Tailgating for the first time in my life up at Miller Park. These cheeseheads know how to do it.", "Mike Ebert");
    tweets.put("Just realized I haven't had meat in a week ‪#accidentalvegetarian", "Mike Ebert");
    tweets.put("SO MUCH .NET AT THIS CODE RETREAT, MORE LIKE .WAT", "Ben Voss");
    tweets.put("Going to the store hungry with no list is a recipe to buy some bullshit. Remember your lists people!  I have three bags of snyders pretzels!", "Ben Voss");
    tweets.put("You may have an internet addiction if the first thing you think about in the morning is jacking into cyberspace and downlinking some bits.", "Josh Cheek");
    tweets.put("Not going to lie, my dreams were sitcom gold last night.", "Josh Cheek");
    tweets.put("Is it vain or classy that I'm bothered by my bic lighter not matching my outfit?", "Josh Cheek");
    tweets.put("MS Excel! You are my nemesis!", "Josh Cheek");
    tweets.put("Today my son sat next to me at my desk with a keyboard and pretended to work like me.  Then he started growling at the computer.", "Eric Smith");
    tweets.put("Much of my most poignant growth as a developer was born from the successes that ended my recurring, soul crushing, fear imbuing, failures.", "Josh Cheek");
    tweets.put("It's cloudy and wet in Chi today. Come, twitters, lets put on headphones and go dancing in the rain.", "Josh Cheek");
    tweets.put("I'd like to read a psychological analysis of twitter. Why do I want to tweet messages no one will understand but will be meaningful for me?", "Justin Herrick");
    tweets.put("*Need to get more exercise. *Decide to take a walk. *Immediately trip down stairs.", "Justin Herrick");
    tweets.put("I'm thinking I may attempt to buy all the ergonomic keyboards and make use of their return policies to find the best one.", "Justin Herrick");
    tweets.put("I'm going to start offering social media competency classes, so people and companies can stop embarrassing themselves. ‪#free ‪#classes ‪#stop", "skim");
    tweets.put("wouldn't it be great if honks were replaced with derp? *derp derp* get off the road! *derrrrpppp*", "Jim Suchy");
    tweets.put("_the world according to dick cheney_ wow, just wow. Recommended.", "Jim Suchy");
    tweets.put("Omg it's pancake day!", "Jim Suchy");
    tweets.put("Reminder:  if you want to be the blind melon girl for Halloween you need to order the bumble bee skirt now.", "Paul Pagel");
    tweets.put("Annoying costume idea:  firefly with a ton of bright LEDs.", "Paul Pagel");
    tweets.put("We live in the age of Sushi.", "Paul Pagel");
    tweets.put("The best ideas seem to only work in a vacuum, which is why consulting is more about creating a vacuum than having good ideas.", "Paul Pagel");
    tweets.put("I travel solo in a clown car.", "Paul Pagel");
    tweets.put("OH: Sounds like my new cologne, 'a hint of basement'", "Paul Pagel");
    tweets.put("Downloading an application, then having to update it to the latest version is an anti-pattern.  Just give me the latest release. ‪#msoffice", "Paul Pagel");
    tweets.put("I'm only a little bit narcissistic... Otherwise I wouldn't be so awesome.", "Brian");
    tweets.put("I know this gets said a lot, but seriously, Internet Explorer?", "Brian");
    tweets.put("Always good when you're packing up your stuff at the hotel and find a pair of underwear that isn't yours, right? #nervous", "Brian");
    tweets.put("Typographical errors in code, to me, are extremely unprofessional.", "Brian");
    tweets.put("So if you're getting sick because of the change of seasons, or other people, or whatever… please, respectfully, #staythefuckhome", "Mike Ebert");
    tweets.put("stop calling things things that don't mean anything! AGH", "Brian");
    tweets.put("If you're right, but you're also kind of an asshole about it, you may as well just be wrong.", "Brian");
    tweets.put("Hope you're happy for screwing me outta $10, parking garage, you won't get another cent from this pissed off customer", "Brian");
    tweets.put("So much complexity comes from trying to make one thing do two things or three things or (head asplode)", "Brian");
    tweets.put("Jim Harbaugh is a fugn crybaby and I hope his QB gets his ass kicked this year.", "Brian");
    tweets.put("Coffee at my folks' place is like 5% strength.", "Brian");
    tweets.put("new Twitter display is just about as confusing-looking as it is useful. Missing the ability to collapse stuff.", "Brian");
    tweets.put("The correct answer to any Star Wars trivia is... Why bother, George Lucas is going to change it just like he changed everything else.", "Brian");
    tweets.put("OH MY GOD IS THAT MY DAD ON FACEBOOK OH GOD", "Brian");
    tweets.put("Telling people I don't like AngularJS is like telling people I hate kittens. (I don't hate kittens)", "Brian");
    tweets.put("Someone's skill at social media is inversely related to my ability to tolerate an actual conversation with said someone", "Brian");
    tweets.put("English pro-tip: The number of question marks / exclamation points after a sentence correspond directly with how annoying the speaker is.", "Brian");
    tweets.put("Travel -> Speaking -> Coding -> Adrenaline crash", "Brian");
    tweets.put("If I could go the rest of my life without using WebEx or GotoMeeting, I would be so happy.", "Brian");
    tweets.put("Got my table-tennis paddle shipment confirmation ... man am I ever uncool", "Brian");
    tweets.put("I type faster than most IDEs can even figure out autocomplete... so, that's why I <3 vim", "Brian");
    tweets.put("IDEs have so many features they forgot to make them text-editing-friendly", "Brian");
    tweets.put("Time since last ragetweet = ((x, y) -> x + y) where x is time since opening Outlook Web Access and y is time it takes to compose said tweet", "Brian");
    tweets.put("Publicly admitting @mjansen401 is better at throwing bottle caps than me.", "Brian");
    tweets.put("Welp, neighbors drunkenly singing got old fast.", "Brian");
    tweets.put("Using iPad debugging with Mobile Safari, so, unfollow me if you don't like Angry Tweets™", "Brian");
    tweets.put("Proposed 28th Amendment: Persons who fail to air-drum the solo to Phil Collins' In The Air Tonight will have their citizenship rejected.", "Brian");
    tweets.put("If someone ever makes a song that claims to be better than Good Vibrations, by the Beach Boys, they're a liar or an alien.", "Brian");
    tweets.put("That moment where you realize the entire ecosystem is pretty much a race condition.", "Brian");
    tweets.put("When brew install outputs a giant list of caveats, you're gonna have a bad time.", "Brian");
    tweets.put("I hate all Ruby environment managers.", "Brian");
    tweets.put("Lets just say between the crazy drunk guy and the crazy lady on the train I see hired security in a positive light tonight.", "Brian");
    tweets.put("GMail lets me select 6503 emails in a split-second. Outlook Web Access lets me select like 50 at a time.", "Brian");
    tweets.put("Just, go ahead and break everything, and we'll just figure it out from there?", "Brian");
    tweets.put("actually, the best part of mayonnaise is how every restaurant assumes you want WAY TOO MUCH MAYONNAISE on everything", "Brian");
    tweets.put("Google: We can do free Wi-Fi from blimps.. but 64-bit browser on Mac? Meh.", "Brian");
    tweets.put("The entire language of JavaScript is like a cargo cult of clever idioms.", "Brian");
    tweets.put("HOLY SENSORY OVERLOAD.", "Brian");
    tweets.put("You know what would be cool? If I could do a Google Hangout without having to sign over my first-born in the process. #justLetMeCallSomeone", "Brian");
    tweets.put("Constitution? More like Con-Shit-Tution! - Supreme Court", "Brian");
    tweets.put("OH: Working effectively with legacy Stack Overflow posts", "Brian");
    tweets.put("I dunno what the pattern is for what I just did, so I'm just going to call it the flippin' sweet pattern.", "Brian");
    tweets.put("Oh god.. Should I be wearing..  Shorts?", "Brian");
    tweets.put("Supreme Court to Americans the last 8 Years: eff you", "Brian");
    tweets.put("", "Brian");
    tweets.put("is the Supreme Court okay? Has anyone checked on them? Their recent decisions are kinda schizo.", "Brian");
    tweets.put("This wine goes great with Cheez-Its.", "Brian");
    tweets.put("Posting onion articles to Facebook to see if any of your friends mistake it for real news as a service", "Brian");
    tweets.put("I'll have you know I drove past a bunch of Bears fans on their way to the stadium, and only felt a sudden urge to heckle them 17 times.", "Brian");
    tweets.put("Enter a room.  Say Mahna mahana.  If nobody say doo doo do do do then leave. They ain't worth it.", "Eric Smith");
  }

  public String getRandomTweet() {
    int index = randomGenerator.nextInt(tweets.size());
    ArrayList<String> tweetList = new ArrayList<String>(tweets.keySet());
    return tweetList.get(index);
  }

  public String getTweeter(String tweet) {
    return (String)tweets.get(tweet);
  }
}
