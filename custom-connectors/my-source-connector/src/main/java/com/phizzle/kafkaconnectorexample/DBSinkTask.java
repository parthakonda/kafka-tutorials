package com.phizzle.kafkaconnectorexample;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import com.phizzle.kafkaconnectorexample.DBConnector;

public class DBSinkTask extends SinkTask {
  private static Logger log = LoggerFactory.getLogger(DBSinkTask.class);
  private DBConnector connector = new DBConnector();

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    try {
      log.info("..........................In Start........................");
      connector.connect();
      log.info("..........................");
    } catch (Exception e) {
      log.error("Error {}", e.getMessage());
      log.error("Exiting connect container");
      System.exit(1);
    }
  }

  @Override
  public void put(Collection<SinkRecord> collection) {
    try {
      log.info("..........................In put........................");
      log.info("..........................");
    } catch (Exception e) {
      log.error("Error {}", e.getMessage());
      log.error("Exiting connect container");
      System.exit(1);
    }
    for (SinkRecord record : collection) {
      try {
        connector.addUser(record.value().toString());
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      log.info("put() - Processing record {} ", record.value());
    }
  }

  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> map) {
    try {
      log.info("..........................In flush........................");
      log.info("..........................");
    } catch (Exception e) {
      log.error("Error {}", e.getMessage());
      log.error("Exiting connect container");
      System.exit(1);
    }
  }

  @Override
  public void stop() {
    try {
      log.trace("..........................In stop........................");
      log.trace("..........................");
    } catch (Exception e) {
      log.error("Error {}", e.getMessage());
      log.error("Exiting connect container");
      System.exit(1);
    }
  }

}
