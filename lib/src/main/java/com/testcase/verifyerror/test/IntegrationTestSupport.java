package com.testcase.verifyerror.test;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class IntegrationTestSupport implements TestPropertyProvider {

  @Inject private Collection<TestServer> testServers;
  @Inject @Nullable private SomeServerMock someServerMock;

  @NonNull
  @Override
  public Map<String, String> getProperties() {
    final Map<String, String> configMap = new HashMap<>();
    SomeServerMock.addConfig(configMap);
    return configMap;
  }

  @BeforeEach
  public void initializeTestServers() {
    testServers.forEach(TestServer::initialize);
  }

  @AfterEach
  public void resetTestServers() {
    try {
      testServers.forEach(TestServer::verify);
    } finally {
      testServers.forEach(TestServer::reset);
    }
  }

  @AfterAll
  public void stopTestServers() {
    testServers.forEach(TestServer::stop);
  }

  // TODO: removing this also make it succeed
  protected boolean isDatastoreIndexCheckEnabled() {
    return true;
  }
}
