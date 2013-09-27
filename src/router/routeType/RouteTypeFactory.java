package router.routeType;

import java.io.File;

public class RouteTypeFactory {
  public RouteType build(String routeType, File routeFile) {
    if (routeType.equalsIgnoreCase("question"))
      return new Question();
    else if (routeType.equalsIgnoreCase("answer"))
      return new Answer();
    else
      return new Public();
  }
}
