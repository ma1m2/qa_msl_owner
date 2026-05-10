package msl.qa.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:${stand}.properties"
})
public interface WebDriverConfig extends Config {

  @Key("runType")
  @DefaultValue("LOCAL")
  RunType getRunType();

  @Key("baseUrl")
  @DefaultValue("https://github.com")
  String getBaseUrl();

  @Key("browserName")
  @DefaultValue("CHROME")
  Browser getBrowser();

  @Key("remoteUrl")
  @DefaultValue("http://localhost:4444/wd/hub")
  URL getRemoteUrl();

  @Key("browserSize")
  @DefaultValue("1920x1080")
  String getBrowserSize();

  @Key("browserVersion")
  @DefaultValue("")
  String getBrowserVersion();

  @Key("selenoid.enableVNC")
  @DefaultValue("false")
  boolean isSelenoidVncEnabled();

  @Key("selenoid.enableLog")
  @DefaultValue("false")
  boolean isSelenoidLogEnabled();

  @Key("selenoid.enableVideo")
  @DefaultValue("false")
  boolean isSelenoidVideoEnabled();
}
