package msl.qa.tests;

import msl.qa.config.FruitsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FruitsTest {

  @Test
  public void testFruitsArray() {
    System.setProperty("array", "orange,apple");
    FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());

    assertThat(config.getFruitsArray()).containsExactly("orange", "apple");
  }

  @Test
  public void testFruitsArrayWithDefaultValues() {
    FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());

    assertThat(config.getFruitsArrayWithDefaultValues()).containsExactly("orange", "apple");
  }

  @Test
  public void testFruitsList() {
    System.setProperty("list", "orange,apple");
    FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());

    assertThat(config.getFruitsList()).containsExactly("orange", "apple");
  }

  @Test
  public void testFruitsListWithSeparator() {
    System.setProperty("list", "orange;banana");
    FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());

    assertThat(config.getFruitsListWithSeparator()).containsExactly("orange", "banana");
  }
}
