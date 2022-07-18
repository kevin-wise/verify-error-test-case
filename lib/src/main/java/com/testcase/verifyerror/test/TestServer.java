package com.testcase.verifyerror.test;

import jakarta.annotation.PreDestroy;

/**
 * Represents a server for testing, usually an emulator of a third-party service like a database or
 * cache.
 */
public interface TestServer {

  /** Run before each test, to set up the state. */
  default void initialize() {}

  /**
   * Check the state of the server to verify nothing wrong has happened during the test. For
   * example, with Datastore this checks that no indexes were auto-generated.
   */
  default void verify() {}

  /** Reset the state of the server back to the base (usually empty) state. Done after each test. */
  void reset();

  /** Stop the server. */
  @PreDestroy
  default void stop() {}
}
