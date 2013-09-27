package router;

import http.server.Templater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class RouterTest {
  private File workingDirectory;
  private File publicDirectory;
  private Router router;

  @Before
  public void setUp() throws IOException {
    workingDirectory = new File(new File(System.getProperty("user.dir")).getParent(), "batRouter");
    publicDirectory = new File(workingDirectory, "public/");
    router = new Router();
    router.getRouterMap(getServerConfig(workingDirectory));
    new Templater().copyTemplatesToDisk("/builder/templates/templates.zip", publicDirectory);
  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectory, "/templates"));
  }

  @Test
  public void getRouterMap() throws IOException {
    router.getRouterMap(getServerConfig(workingDirectory));
    assertThat(router.routerMap, instanceOf(DefaultHashMap.class));
    assertTrue(!router.routerMap.isEmpty());
  }

  @Test
  public void getResponse() throws IOException, ParseException {
    byte[] response = router.getResponse(getServerConfig(workingDirectory));
    assertTrue(response.length > 0);
  }

  @Test
  public void getRoute() throws IOException, ParseException {
    String route = router.getRoute(getServerConfig(workingDirectory));
    assertTrue(route != null);
  }

  public void deleteDirectory(File directory) {
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