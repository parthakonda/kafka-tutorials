package com.phizzle.kafkaconnectorexample;

import org.junit.Test;

public class MySourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.println(DBSinkConnectorConfig.conf().toRst());
  }
}