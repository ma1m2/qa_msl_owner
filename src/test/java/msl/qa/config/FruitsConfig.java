package msl.qa.config;

import org.aeonbits.owner.Config;

import java.util.List;

public interface FruitsConfig extends Config {

  @Key("array")
  String[] getFruitsArray();

  @Key("array")
  @DefaultValue("orange,apple")
  String[] getFruitsArrayWithDefaultValues();

  @Key("list")
  List<String> getFruitsList();

  @Key("list")
  @Separator(";")
  @DefaultValue("orange;apple")
  List<String> getFruitsListWithSeparator();

}
