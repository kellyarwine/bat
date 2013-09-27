import http.io.Io;
import http.io.MockIo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MenuTest {
  private String NEW_LINE = "\r\n";
  private File workingDirectoryFullPath;
  private File publicDirectoryFullPath;
  private File logFile;
  private File mockRequestsFile;

  @Before
  public void setUp() throws IOException {
    workingDirectoryFullPath = new File(new File(System.getProperty("user.dir")).getParent(), "batRouter");
    String publicDirectory = "public/";
    publicDirectoryFullPath = new File(workingDirectoryFullPath, publicDirectory);
    logFile = new File(workingDirectoryFullPath, "server.log");
    mockRequestsFile = new File(workingDirectoryFullPath, "mock_requests.tsv");
  }

  @After
  public void tearDown() {
    deleteDirectory(new File(publicDirectoryFullPath, "/templates"));
    logFile.delete();
    mockRequestsFile.delete();
  }

  @Test
  public void startServerWithInvalidEnv() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -e not_production_and_not_test");
    input.add("exit");
    Io mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "Invalid \"env\" setting.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithInvalidWorkingDirectory() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -w not_a_valid_working_directory");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The working directory does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithInvalidPublicDirectory() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -d not_a_valid_public_directory");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The public directory does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithInvalidRoutesFile() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -r not_a_valid_routes_file");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The routes file does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithInvalidHtAccessFile() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -h not_a_valid_htaccess_file");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The .htaccess file does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithInvalidMockRequestsFile() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -m not_a_valid_mock_requests_file");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The mock requests file does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithAnotherInvalidMockRequestsFile() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -e test");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "The mock requests file does not exist.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithCustomConfigs() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -p 4999 -e test -d public/ -r routes.csv -h .htaccess -m mock_requests.tsv -w " + workingDirectoryFullPath);
    input.add("status");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    createMockRequestsTsv();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "Ninja Server is running on port 4999.\n"
            + "Ninja Server has been shut down.\n";

//    assertEquals(expectedResult, readLog());
    assertTrue(readLog().contains("Ninja Server Menu\n"));
    assertTrue(readLog().contains("----------------------\n"));
    assertTrue(readLog().contains("Type \"help\" to see a list of available commands.\n"));
    assertTrue(readLog().contains("Ninja Server is running on port 4999.\n"));
    assertTrue(readLog().contains("Ninja Server has been shut down.\n"));
  }

  @Test
  public void stopServer() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -e test -m mock_requests.tsv");
    input.add("stop");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    createMockRequestsTsv();
    main.display(mockIo);

//    assertEquals(expectedResult, readLog());
    assertTrue(readLog().contains("Ninja Server Menu\n"));
    assertTrue(readLog().contains("----------------------\n"));
    assertTrue(readLog().contains("Type \"help\" to see a list of available commands.\n"));
    assertTrue(readLog().contains("Ninja Server has been shut down.\n"));

  }

  @Test
  public void startServerDisplayStatus() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -p 5002 -e test -m mock_requests.tsv");
    input.add("status");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    createMockRequestsTsv();
    main.display(mockIo);

//    assertEquals(expectedResult, readLog());
    assertTrue(readLog().contains("Ninja Server Menu\n"));
    assertTrue(readLog().contains("----------------------\n"));
    assertTrue(readLog().contains("Type \"help\" to see a list of available commands.\n"));
    assertTrue(readLog().contains("Ninja Server is running on port 5002.\n"));
    assertTrue(readLog().contains("Ninja Server has been shut down.\n"));
  }

  @Test
  public void displayStatus() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("status");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "Ninja Server is not running.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithDefaultConfigurations() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -e test -m mock_requests.tsv");
    input.add("status");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    createMockRequestsTsv();
    main.display(mockIo);

//    assertEquals(expectedResult, readLog());
    assertTrue(readLog().contains("Ninja Server Menu\n"));
    assertTrue(readLog().contains("----------------------\n"));
    assertTrue(readLog().contains("Type \"help\" to see a list of available commands.\n"));
    assertTrue(readLog().contains("Ninja Server is running on port 5000.\n"));
    assertTrue(readLog().contains("Ninja Server has been shut down.\n"));
  }

  @Test
  public void startServerWithInvalidStartCommand() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("this is not how one should start the server");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "\n"
            + "Ninja Server Help Menu for CobSpec\n"
            + "-------------------------\n"
            + "Available Commands:\n"
            + " start           Starts the server with CobSpec configurations.\n"
            + " status          Lists the status of the server.\n"
            + " stop            Stops the server.\n"
            + " exit            Exits the application.\n"
            + " help            Provides instructions and detailed information for each command.\n"
            + "\n"
            + "Starting the Server:\n"
            + " start           Starts the server.  The application takes six optional parameters:\n"
            + "                 an environment setting; \"test\" or \"production\" (denoted by the \"-e\" flag)\n"
            + "                 a port number (denoted by the \"-p\" flag)\n"
            + "                 the absolute path to the working directory (denoted by the \"-w\" flag)\n"
            + "                 the relative path to the public directory (denoted by the \"-d\" flag)\n"
            + "                 the Routes .csv filename; file must exist in the root working directory (denoted by the \"-r\" flag)\n"
            + "                 the .htaccess .csv filename; file must exist in the root working directory (denoted by the \"-h\" flag)\n"
            + "                 the mock request .tsv filename; file must exist in the root working directory (denoted by the \"-m\" flag)\n"
            + "                 can hold one or more mock requests; used for unit-testing purposes\n"
            + "Default Server Configurations:\n"
            + " start           [=<-e production>]\n"
            + "                 [=<-p 5000>]\n"
            + "                 [=<-w " + workingDirectoryFullPath + ">]\n"
            + "                 [=<-d public/>]\n"
            + "                 [=<-r routes.csv>]\n"
            + "                 [=<-h .htaccess>]\n"
            + "                 [=<-m >]\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void displayHelpMenu() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("help");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "\n"
            + "Ninja Server Help Menu for CobSpec\n"
            + "-------------------------\n"
            + "Available Commands:\n"
            + " start           Starts the server with CobSpec configurations.\n"
            + " status          Lists the status of the server.\n"
            + " stop            Stops the server.\n"
            + " exit            Exits the application.\n"
            + " help            Provides instructions and detailed information for each command.\n"
            + "\n"
            + "Starting the Server:\n"
            + " start           Starts the server.  The application takes six optional parameters:\n"
            + "                 an environment setting; \"test\" or \"production\" (denoted by the \"-e\" flag)\n"
            + "                 a port number (denoted by the \"-p\" flag)\n"
            + "                 the absolute path to the working directory (denoted by the \"-w\" flag)\n"
            + "                 the relative path to the public directory (denoted by the \"-d\" flag)\n"
            + "                 the Routes .csv filename; file must exist in the root working directory (denoted by the \"-r\" flag)\n"
            + "                 the .htaccess .csv filename; file must exist in the root working directory (denoted by the \"-h\" flag)\n"
            + "                 the mock request .tsv filename; file must exist in the root working directory (denoted by the \"-m\" flag)\n"
            + "                 can hold one or more mock requests; used for unit-testing purposes\n"
            + "Default Server Configurations:\n"
            + " start           [=<-e production>]\n"
            + "                 [=<-p 5000>]\n"
            + "                 [=<-w " + workingDirectoryFullPath + ">]\n"
            + "                 [=<-d public/>]\n"
            + "                 [=<-r routes.csv>]\n"
            + "                 [=<-h .htaccess>]\n"
            + "                 [=<-m >]\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
  }

  @Test
  public void startServerWithUnavailablePort() throws Exception {
    ServerSocket serverSocket = new ServerSocket(5001);
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -p 5001");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "Port 5001 is already in use.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
    serverSocket.close();
  }

  @Test
  public void startServerWithInvalidPortNumber() throws Exception {
    ArrayList<String> input = new ArrayList<String>();
    input.add("start -p this_is_not_a_number");
    input.add("exit");
    MockIo mockIo = new MockIo(input);
    Menu main = new Menu();
    main.display(mockIo);
    String expectedResult =
        "Ninja Server Menu\n"
            + "----------------------\n"
            + "Type \"help\" to see a list of available commands.\n"
            + "Port this_is_not_a_number is not a valid port.  Please try again.\n"
            + "Ninja Server is not currently running.\n";
    assertEquals(expectedResult, readLog());
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

  private String readLog() throws IOException {
    InputStream inputStream = new FileInputStream(logFile);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int chr;

    while ((chr = inputStream.read()) != -1)
      outputStream.write(chr);

    return outputStream.toString();
  }

  private void createMockRequestsTsv() throws IOException {
    mockRequestsFile.createNewFile();
  }
}