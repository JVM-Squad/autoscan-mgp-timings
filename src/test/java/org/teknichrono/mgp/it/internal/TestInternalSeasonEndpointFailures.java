package org.teknichrono.mgp.it.internal;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.teknichrono.mgp.business.service.SeasonService;
import org.teknichrono.mgp.it.WireMockExtensions;

import java.util.Optional;

import static io.restassured.RestAssured.given;

@Tag("integration")
@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
class TestInternalSeasonEndpointFailures {

  @InjectMock
  SeasonService seasonService;

  @BeforeEach
  public void setup() {
    Mockito.when(seasonService.getCurrentTestSeason()).thenReturn(Optional.empty());
    Mockito.when(seasonService.getCurrentSeason()).thenReturn(Optional.empty());
  }


  @Test
  public void getCurrentSeasonThatDoesNotExist() {
    given()
        .when().get(" /api/internal/season/current")
        .then()
        .statusCode(404);
  }

  @Test
  public void getCurrentTestThatDoesNotExist() {
    given()
        .when().get(" /api/internal/season/current/test")
        .then()
        .statusCode(404);
  }

}