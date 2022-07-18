package com.testcase.verifyerror;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

// this test class always works, presumably because it is Java
class AdminControllerJavaTest extends ApiTestSupport {

  @Test
  void testThatShouldRunAndFail() {
    assertThat(true, is(false));
  }

  @Test
  void testThatShouldRunAndPass() {
    assertThat(true, is(true));
  }
}
