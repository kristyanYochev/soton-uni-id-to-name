package uk.ac.southamton.kristyanYochev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static uk.ac.southamton.kristyanYochev.App.idToName;

import org.junit.jupiter.api.Test;

public class AppTest {
  @Test
  public void idToNameReturnsCorrectName() throws CannotConnectToServer, InvalidId {
    assertEquals("Dr Denis Nicole", idToName("dan1"));
    assertEquals("Dr David Millard", idToName("dem"));
  }

  @Test
  public void idToNameThrowsInvalidIdForNonexistentIds() {
    String invalidId = "someRandomIdThatDefinitelyDoesNotExist";

    Exception exception = assertThrows(InvalidId.class,
        () -> idToName(invalidId)
    );

    assertEquals("Invalid id: " + invalidId, exception.getMessage());
  }

  @Test
  public void idToNameBreaksForMaliciousIds() {
    String maliciousId = "../"; // Payload to access the home page

    Exception exception = assertThrows(InvalidId.class,
        () -> idToName(maliciousId)
    );

    assertEquals("Invalid id: " + maliciousId, exception.getMessage());
  }
}
