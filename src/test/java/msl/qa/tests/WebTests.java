package msl.qa.tests;

import msl.qa.config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTests {
  // One system variable controls run profile: -Dstand=local or -Dstand=remote.
  private WebDriver driver;

  @Test
  public void localWebTest(){
    System.setProperty("stand", System.getProperty("stand", "local"));
    driver = new WebDriverProvider().get();
    String title = driver.getTitle();
    assertEquals("GitHub · Change is constant. GitHub keeps you ahead. · GitHub", title);
  }

  @Test
  public void remoteWebTest(){
    System.setProperty("stand", System.getProperty("stand", "remote"));
    driver = new WebDriverProvider().get();
    String title = driver.getTitle();
    assertEquals("GitHub · Change is constant. GitHub keeps you ahead. · GitHub", title);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
