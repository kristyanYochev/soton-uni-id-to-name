package uk.ac.southamton.kristyanYochev;

public class CannotConnectToServer extends Exception {

  public CannotConnectToServer(Throwable cause) {
    super("Cannot connect to server", cause);
  }
}
