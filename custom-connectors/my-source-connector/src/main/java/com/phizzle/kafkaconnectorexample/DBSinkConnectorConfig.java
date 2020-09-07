package com.phizzle.kafkaconnectorexample;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.util.Map;


public class DBSinkConnectorConfig extends AbstractConfig {

  public static final String DB_HOST = "db.host";
  private static final String DB_HOST_DOC = "This is the database host";

  public static final String DB_NAME = "db.name";
  private static final String DB_NAME_DOC = "This is the name of the database";

  public static final String DB_USER = "db.user";
  private static final String DB_USER_DOC = "This is the name of the database user";

  public static final String DB_PASSWORD = "db.password";
  private static final String DB_PASSWORD_DOC = "This is the database password";

  public static final String DB_PORT = "db.port";
  private static final String DB_PORT_DOC = "This is the database port";

  public DBSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public DBSinkConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
            .define(DB_HOST, Type.STRING, Importance.HIGH, DB_HOST_DOC)
            .define(DB_NAME, Type.STRING, Importance.HIGH, DB_NAME_DOC)
            .define(DB_USER, Type.STRING, Importance.HIGH, DB_USER_DOC)
            .define(DB_PASSWORD, Type.STRING, Importance.HIGH, DB_PASSWORD_DOC)
            .define(DB_PORT, Type.STRING, Importance.HIGH, DB_PORT_DOC);
  }
}
