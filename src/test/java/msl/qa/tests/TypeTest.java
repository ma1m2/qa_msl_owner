package msl.qa.tests;

import msl.qa.config.Browser;
import msl.qa.config.TypeConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TypeTest {

  @Test
  public void testInteger(){
    System.setProperty("integer", "10");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getInteger()).isEqualTo(10);
  }

  @Test
  public void testDouble(){
    System.setProperty("double", "10.11");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getDouble()).isEqualTo(10.11);
  }

  @Test
  public void testBoolean(){
    System.setProperty("boolean", "true");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getBoolean()).isEqualTo(true);
  }

  @Test
  public void testEnum(){
    System.setProperty("enum", "CHROME");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getEnum()).isEqualTo(Browser.CHROME);
  }

  @Test
  public void testBytes(){
    System.setProperty("bytes", "98,121,116,101,115");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getBytes()).containsExactly((byte) 98, (byte) 121, (byte) 116, (byte) 101, (byte) 115);
  }

  @Test
  public void testUrl() throws MalformedURLException {
    System.setProperty("url", "https://github.com");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getUrl()).isEqualTo(new URL("https://github.com"));
  }

  @Test
  public void testFile(){
    System.setProperty("file", ".gitignore");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getFile()).isEqualTo(new File(".gitignore"));
  }

  @Test
  public void testPath(){
    System.setProperty("path", ".gitignore");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThatThrownBy(config::getPath)
        .hasMessageContaining("Cannot convert '.gitignore' to java.nio.file.Path");
  }

  @Test
  public void testList(){
    System.setProperty("list", "1, 2, 3, 4");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getList()).containsExactly(1, 2, 3, 4);
  }

  @Test
  public void testListBrowsers(){
    System.setProperty("listBrowsers", "CHROME,FIREFOX");
    TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());

    assertThat(config.getListBrowsers()).containsExactly(Browser.CHROME, Browser.FIREFOX);
  }

}
