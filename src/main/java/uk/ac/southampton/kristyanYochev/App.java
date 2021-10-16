package uk.ac.southampton.kristyanYochev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {

  // Pattern to match the name in the HTML code.
  // The actual name can be acquired from group 1.
  // The name is between `property="name"` and `<`
  private static final Pattern NAME_PATTERN = Pattern.compile(
      "property=[\"']name[\"']>([a-zA-Z ]+)<"
  );

  private static final String BASE_URL = "https://www.ecs.soton.ac.uk/people/";

  /**
   * @param uniId A person's university identifier
   * @return The same person's name
   * @throws InvalidId The id is malformed or non-existent
   * @throws CannotConnectToServer Connection problems
   */
  public static String idToName(String uniId) throws InvalidId, CannotConnectToServer {
    URL url;
    BufferedReader server;

    try {
      url = new URL(BASE_URL + uniId);
      server = new BufferedReader(new InputStreamReader(url.openStream()));
    } catch (MalformedURLException cause) {
      throw new InvalidId(uniId, cause);
    } catch (IOException cause) {
      throw new CannotConnectToServer(cause);
    }

    List<String> matchedNames = server.lines()
        .map(NAME_PATTERN::matcher)
        .filter(Matcher::find)
        .map(matcher -> matcher.group(1))
        .collect(Collectors.toList());

    if (matchedNames.size() == 0) throw new InvalidId(uniId);

    return matchedNames.get(0);
  }

  public static void main(String[] args) {
    try {
      System.out.println(idToName(args[0]));
    } catch (InvalidId | CannotConnectToServer error) {
      System.err.println(error.getMessage());
    }
  }
}
