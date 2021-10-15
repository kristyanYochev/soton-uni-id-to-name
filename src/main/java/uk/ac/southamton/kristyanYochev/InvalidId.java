package uk.ac.southamton.kristyanYochev;

public class InvalidId extends Exception {
  private static String generateMessage(String id) {
    return "Invalid id: " + id;
  }

  public InvalidId(String id, Throwable cause) {
    super(generateMessage(id), cause);
  }

  public InvalidId(String id) {
    super(generateMessage(id));
  }
}
