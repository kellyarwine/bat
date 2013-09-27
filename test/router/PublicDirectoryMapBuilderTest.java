package batrouter;

import builder.Templater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import router.PublicDirectoryMapBuilder;
import router.routeType.Directory;
import router.routeType.Public;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PublicDirectoryMapBuilderTest {
  File publicDirectory;

  @Before
  public void setUp() throws IOException {
    File workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "Server");
    publicDirectory = new File(workingDirectory, "test/public/");
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectory);  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectory, "/templates"));
  }

  @Test
  public void buildsHashMap() throws IOException {
    PublicDirectoryMapBuilder publicDirectoryMapBuilder = new PublicDirectoryMapBuilder();
    HashMap actualResult = publicDirectoryMapBuilder.get(publicDirectory);
    ArrayList actualRouteInfo1 = (ArrayList)actualResult.get("/images");
    ArrayList actualRouteInfo2 = (ArrayList)actualResult.get("/celebrate.gif");
    ArrayList actualRouteInfo3 = (ArrayList)actualResult.get("/images/404.png");
    ArrayList actualRouteInfo4 = (ArrayList)actualResult.get("/stylesheets/stylesheet.css");
    ArrayList actualRouteInfo5 = (ArrayList)actualResult.get("/templates/404.html");

    HashMap expectedResult = new HashMap();
    ArrayList expectedRouteInfo1 = new ArrayList();
    expectedRouteInfo1.add(new File(publicDirectory, "/templates/file_directory.html"));
    expectedRouteInfo1.add(new Directory(publicDirectory));
    expectedResult.put("/images", expectedRouteInfo1);
    ArrayList expectedRouteInfo2 = new ArrayList();
    expectedRouteInfo2.add(new File(publicDirectory, "/celebrate.gif"));
    expectedRouteInfo2.add(new Public());
    expectedResult.put("/celebrate.gif", expectedRouteInfo2);
    ArrayList expectedRouteInfo3 = new ArrayList();
    expectedRouteInfo3.add(new File(publicDirectory, "/images/404.png"));
    expectedRouteInfo3.add(new Public());
    expectedResult.put("/images/404.png", expectedRouteInfo3);
    ArrayList expectedRouteInfo4 = new ArrayList();
    expectedRouteInfo4.add(new File(publicDirectory, "/stylesheets/stylesheet.css"));
    expectedRouteInfo4.add(new Public());
    expectedResult.put("/stylesheets/stylesheet.css", expectedRouteInfo4);
    ArrayList expectedRouteInfo5 = new ArrayList();
    expectedRouteInfo5.add(new File(publicDirectory, "/templates/404.html"));
    expectedRouteInfo5.add(new Public());
    expectedResult.put("/templates/404.html", expectedRouteInfo5);

    assertEquals(expectedRouteInfo1.get(0), actualRouteInfo1.get(0));
    assertThat(actualRouteInfo1.get(1), instanceOf(Directory.class));
    assertEquals(expectedRouteInfo2.get(0), actualRouteInfo2.get(0));
    assertThat(actualRouteInfo2.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo3.get(0), actualRouteInfo3.get(0));
    assertThat(actualRouteInfo3.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo4.get(0), actualRouteInfo4.get(0));
    assertThat(actualRouteInfo4.get(1), instanceOf(Public.class));
    assertEquals(expectedRouteInfo5.get(0), actualRouteInfo5.get(0));
    assertThat(actualRouteInfo5.get(1), instanceOf(Public.class));
  }

  private void deleteDirectory(File directory) {
    if (directory.isDirectory()) {
      String[] children = directory.list();
      for (int i=0; i<children.length; i++) {
        deleteDirectory(new File(directory, children[i]));
      }
    }
    directory.delete();
  }
}