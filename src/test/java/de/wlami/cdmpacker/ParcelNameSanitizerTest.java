package de.wlami.cdmpacker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParcelNameSanitizerTest {

  ParcelNameSanitizer sanitizer = new ParcelNameSanitizer();

  @Test
  public void shouldReturnUppercase() {
    assertEquals("MYNAME", sanitizer.sanitzieParcelName("myname", null));
  }

  @Test
  public void shouldReplaceDashesWithUnderscores() {
    assertEquals("MY_NAME", sanitizer.sanitzieParcelName("my-name", null));
  }

  @Test
  public void shouldIgnoreNumber() {
    assertEquals("MY_NAME12456", sanitizer.sanitzieParcelName("my-name12456", null));
  }
}