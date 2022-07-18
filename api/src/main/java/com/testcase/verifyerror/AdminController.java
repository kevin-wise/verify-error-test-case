package com.testcase.verifyerror;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;
import jakarta.inject.Singleton;
import java.util.List;

@Controller("/api/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class AdminController {

  @Post("reindex")
  @Status(HttpStatus.OK)
  public void reindexKinds() {
  }

  @Delete("invalidateCaches")
  @Status(HttpStatus.NO_CONTENT)
  public void invalidateCaches(@QueryValue @Nullable List<String> caches) {
  }
}
