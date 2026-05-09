package msl.qa.tests;

import msl.qa.config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

  @Test
  public void testAuthClasspath() {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    assertThat(config.username()).isEqualTo("qamsl");
    assertThat(config.password()).isEqualTo("1234");
  }

  @Test
  public void testAuthWithSecretFile() throws IOException {
    String content = "username=secret-user\npassword=secret-pass";
    Path secret = Paths.get("/tmp/secret.properties");

    Files.write(secret, content.getBytes(StandardCharsets.UTF_8));
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    assertThat(config.username()).isEqualTo("secret-user");
    assertThat(config.password()).isEqualTo("secret-pass");

    //Files.delete(secret);
  }
}
