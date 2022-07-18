package com.testcase.verifyerror;

import com.testcase.verifyerror.test.IntegrationTestSupport;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import java.util.Map;
import org.junit.jupiter.api.TestInstance;

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ApiTestSupport extends IntegrationTestSupport implements TestPropertyProvider {

  @Inject private EmbeddedServer server;

  @Inject
  @Client("/")
  private HttpClient apiClient;

  @Override
  public Map<String, String> getProperties() {
    final Map<String, String> configMap = super.getProperties();
    configMap.put("micronaut.http.services./.read-timeout", "30s");
    return configMap;
  }

  public EmbeddedServer getServer() {
    return this.server;
  }

  public HttpClient getApiClient() {
    return this.apiClient;
  }

  // TODO: Uncommenting this makes it work. How????
//  @Override
//  protected boolean isDatastoreIndexCheckEnabled() {
//    return true;
//  }

}
