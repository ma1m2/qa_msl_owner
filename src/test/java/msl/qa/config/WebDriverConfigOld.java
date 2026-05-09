package msl.qa.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WebDriverConfigOld {

  public String getBaseUrl(){
    String baseUrl = System.getProperty("baseUrl", "https://github.com");
    return baseUrl;
  }

  public Browser getBrowser(){
    String browser = System.getProperty("browser", "CHROME");
    return Browser.valueOf(browser);
  }

  public URL getRemoteUrl(){
    //read value from settings
    String remoteUrl = System.getProperty("remoteUrl");
    //check default value
    if (Objects.isNull(remoteUrl)){
      remoteUrl = "http://localhost:4444/wd/hub";
    }
    //return a result with the cast type
    try {
      return new URL(remoteUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

}
