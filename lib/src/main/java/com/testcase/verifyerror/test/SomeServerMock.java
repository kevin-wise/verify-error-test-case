package com.testcase.verifyerror.test;

import io.micronaut.context.annotation.Requirements;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Map;

@Singleton
@Requirements({
  @Requires(
      property = WireMockTestServer.CONFIG_PREFIX + "." + SomeServerMock.CLIENT_ID + ".enabled",
      notEquals = StringUtils.FALSE,
      defaultValue = StringUtils.TRUE),
  @Requires(env = Environment.TEST),
})
public class SomeServerMock extends WireMockTestServer {

  public static final String CLIENT_ID = "something";

  @Inject
  public SomeServerMock(
      @Value("${" + WireMockTestServer.CONFIG_PREFIX + "." + SomeServerMock.CLIENT_ID + ".port}")
          int port) {
    super(port);
  }

  public static void addConfig(Map<String, String> configMap) {
    WireMockTestServer.addConfigForClient(configMap, SomeServerMock.CLIENT_ID);
  }
}
