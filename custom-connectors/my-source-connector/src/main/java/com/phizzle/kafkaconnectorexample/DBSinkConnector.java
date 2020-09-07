package com.phizzle.kafkaconnectorexample;

import java.util.List;
import java.util.Map;

import com.github.jcustenborder.kafka.connect.utils.config.TaskConfigs;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBSinkConnector extends SinkConnector {
  private static Logger log = LoggerFactory.getLogger(DBSinkConnector.class);
  private DBSinkConnectorConfig config;
  private Map<String, String> settings;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> settings) {
    config = new DBSinkConnectorConfig(settings);
    this.settings = settings;
  }

  @Override
  public Class<? extends Task> taskClass() {
    return DBSinkTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    return TaskConfigs.multiple(this.settings, maxTasks);
  }

  @Override
  public void stop() {
    try {
      log.info("..........................In Connector Stop........................");
      log.info("..........................");
    } catch (Exception e) {
      log.error("Error {}", e.getMessage());
      log.error("Exiting connect container");
      System.exit(1);
    }
  }

  @Override
  public ConfigDef config() {
    return DBSinkConnectorConfig.conf();
  }
}
