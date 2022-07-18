package com.testcase.verifyerror

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

import org.junit.jupiter.api.Test

// this test class sometimes works, presumably because it is Groovy
class AdminControllerTest extends ApiTestSupport {

  @Test
  void "test that should run and fail"() {
    assertThat(true, is(false))
  }

  @Test
  void "test that should run and pass"() {
    assertThat(true, is(true))
  }
}
