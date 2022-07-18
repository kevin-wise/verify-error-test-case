package com.testcase.verifyerror.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.micronaut.core.io.socket.SocketUtils;
import jakarta.annotation.PreDestroy;
import java.util.Map;

public abstract class WireMockTestServer implements TestServer {

  public static final String CONFIG_PREFIX = "test.mocks";

  protected final WireMockServer mockServer;

  public WireMockTestServer(int port) {
    this(port, true);
  }

  public WireMockTestServer(int port, boolean useHttps) {
    final WireMockConfiguration config = WireMockConfiguration.options();

    if (useHttps) {
      config.httpDisabled(true).httpsPort(port);
    } else {
      config.httpDisabled(false).port(port).dynamicHttpsPort();
    }

    this.mockServer = new WireMockServer(config);
    mockServer.start();
  }

  protected static void addConfigForClient(Map<String, String> configMap, String clientId) {
    addConfigForClient(configMap, clientId, true);
  }

  protected static void addConfigForClient(
      Map<String, String> configMap, String clientId, boolean useHttps) {
    final int port = SocketUtils.findAvailableTcpPort();
    final String url = (useHttps ? "https" : "http") + "://localhost:" + port;
    configMap.put(CONFIG_PREFIX + "." + clientId + ".port", Integer.toString(port));
    configMap.put(CONFIG_PREFIX + "." + clientId + ".https-enabled", Boolean.toString(useHttps));

    // The self-signed certs fail otherwise
    configMap.put("micronaut.http.client.ssl.insecureTrustAllCertificates", "true");
  }

  @Override
  public void reset() {
    mockServer.resetAll();
  }

  @Override
  @PreDestroy
  public void stop() {
    mockServer.stop();
  }
}
