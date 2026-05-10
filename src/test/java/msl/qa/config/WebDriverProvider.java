package msl.qa.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;
import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

  private final WebDriverConfig config;

  public WebDriverProvider() {
    this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
  }

  @Override
  public WebDriver get() {
    WebDriver driver = createDriver();
    driver.get(config.getBaseUrl());
    return driver;
  }

  public WebDriver createDriver() {
    if (config.getRunType() == RunType.REMOTE) {
      return new RemoteWebDriver(config.getRemoteUrl(), buildCapabilities());
    }

    switch (config.getBrowser()) {
      case CHROME: {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(createLocalChromeOptions());
      }
      case FIREFOX: {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(createLocalFirefoxOptions());
      }
      default: {
        throw new RuntimeException("No such driver: " + config.getBrowser());
      }
    }
  }

  private Capabilities buildCapabilities() {
    switch (config.getBrowser()) {
      case CHROME: {
        ChromeOptions options = new ChromeOptions();
        setBrowserVersionIfPresent(options);
        options.addArguments("--window-size=" + config.getBrowserSize().replace("x", ","));
        setSelenoidOptions(options);
        return options;
      }
      case FIREFOX: {
        FirefoxOptions options = new FirefoxOptions();
        setBrowserVersionIfPresent(options);
        setSelenoidOptions(options);
        return options;
      }
      default: {
        throw new RuntimeException("No such driver: " + config.getBrowser());
      }
    }
  }

  private ChromeOptions createLocalChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--window-size=" + config.getBrowserSize().replace("x", ","));
    return options;
  }

  private FirefoxOptions createLocalFirefoxOptions() {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--width=" + parseWidth());
    options.addArguments("--height=" + parseHeight());
    return options;
  }

  private int parseWidth() {
    return Integer.parseInt(config.getBrowserSize().split("x")[0]);
  }

  private int parseHeight() {
    return Integer.parseInt(config.getBrowserSize().split("x")[1]);
  }

  private void setBrowserVersionIfPresent(org.openqa.selenium.MutableCapabilities options) {
    String browserVersion = config.getBrowserVersion();
    if (browserVersion != null && !browserVersion.isBlank()) {
      options.setCapability("browserVersion", browserVersion);
    }
  }

  private void setSelenoidOptions(org.openqa.selenium.MutableCapabilities options) {
    options.setCapability("selenoid:options",
            Map.of(
                    "enableVNC", config.isSelenoidVncEnabled(),
                    "enableLog", config.isSelenoidLogEnabled(),
                    "enableVideo", config.isSelenoidVideoEnabled()
            ));
  }

}
