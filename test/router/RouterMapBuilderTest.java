package batrouter;

import builder.Templater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import router.DefaultHashMap;
import router.RouterMapBuilder;
import router.routeType.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class RouterMapBuilderTest {
  private DefaultHashMap actualResult;
  private File publicDirectoryFullPath;

  @Before
  public void setUp() throws IOException {
    File workingDirectory = new File(System.getProperty("user.dir"));
    publicDirectoryFullPath = new File(workingDirectory, "public/");
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectoryFullPath);
    RouterMapBuilder routerMapBuilder = new RouterMapBuilder();
    actualResult = routerMapBuilder.buildFrom(getServerConfig(workingDirectory));
  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectoryFullPath, "/templates"));
  }

  @Test
  public void directoryClass() {
    String route = "/templates";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "templates/file_directory.html"));
    assertThat(actualArrayList.get(1), instanceOf(Directory.class));
  }

  @Test
  public void publicClass() {
    String route = "/answer.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void subfolder() {
    String route = "/images/angry_bird.png";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void questionClass() {
    String route = "/question.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void answerClass() {
    String route = "/answer.html";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, route));
    assertThat(actualArrayList.get(1), instanceOf(Public.class));
  }

  @Test
  public void countFilesInMap() {
    assertEquals(24, actualResult.size());
  }

  @Test
  public void returnsFourOhFourRoute() throws IOException {
    String route = "/this_url_does_not_exist";
    ArrayList actualArrayList = getRouterMapValue(route);
    assertEquals(actualArrayList.get(0), new File(publicDirectoryFullPath, "/templates/404.html"));
    assertThat(actualArrayList.get(1), instanceOf(FileNotFound.class));
  }

  private ArrayList getRouterMapValue(String route) {
    return (ArrayList)actualResult.get(route);
  }

  public byte[] toBytes(File routeFile) throws IOException {
    InputStream inputStream = new FileInputStream(routeFile);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int chr;

    while ((chr = inputStream.read()) != -1)
      outputStream.write(chr);

    return outputStream.toByteArray();
  }

  public void deleteDirectory(File directory)
  {
    if (directory.isDirectory()) {
      String[] children = directory.list();
      for (int i=0; i<children.length; i++) {
        deleteDirectory(new File(directory, children[i]));
      }
    }
    directory.delete();
  }

  private HashMap getServerConfig(File workingDirectory) {
    HashMap<String, String> serverConfig = new HashMap<String, String>();
    serverConfig.put("port", "5000");
    serverConfig.put("publicDirectoryPath", "public/");
    serverConfig.put("env", "production");
    serverConfig.put("routesFilePath", "routes.csv");
    serverConfig.put("htAccessFilePath", ".htaccess");
    serverConfig.put("workingDirectoryPath", workingDirectory.toString());
    serverConfig.put("mockRequestsFilePath", "");
    return serverConfig;
  }
}