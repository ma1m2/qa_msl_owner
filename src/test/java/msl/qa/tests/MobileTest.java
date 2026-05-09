package msl.qa.tests;

import msl.qa.config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MobileTest {

  @Test
  public void testMobile() {
    MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    assertThat(config.getDeviceName()).isEqualTo("iPhone 17 Pro Max");
    assertThat(config.getPlatformName()).isEqualTo("iOS");
    assertThat(config.getPlatformVersion()).isEqualTo("17");
  }

  @Test
  public void testMobileWithSystemOverride() {
    System.setProperty("platform.version", "15");

    MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    assertThat(config.getPlatformVersion()).isEqualTo("15");
  }

  @Test
  public void testMobileWithAndroid() {
    System.setProperty("device", "google-pixel");
    MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    assertThat(config.getDeviceName()).isEqualTo("Google Pixel 10");
    assertThat(config.getPlatformName()).isEqualTo("Android");
    assertThat(config.getPlatformVersion()).isEqualTo("15");
  }

  @Test
  public void testMobileWithIPhone() {
    System.setProperty("device", "iphone-16");
    MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    assertThat(config.getDeviceName()).isEqualTo("iPhone 16 Pro");
    assertThat(config.getPlatformName()).isEqualTo("iOS");
    assertThat(config.getPlatformVersion()).isEqualTo("16");
  }
}
