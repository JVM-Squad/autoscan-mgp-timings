package org.teknichrono.mgp.it;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.teknichrono.mgp.model.rider.RiderDetails;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

@Tag("integration")
@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
class TestRiderEndpoint {


  @Test
  public void getRiderInfo() {
    RiderDetails riderDetails = given()
        .when().get("/rider/8150")
        .then()
        .statusCode(200)
        .statusCode(200).extract().as(RiderDetails.class);
    assertThat(riderDetails.name).isEqualTo("Alex");
    assertThat(riderDetails.surname).isEqualTo("Rins");
    assertThat(riderDetails.country.iso).isEqualTo("ES");
    assertThat(riderDetails.career.size()).isEqualTo(11);
  }
}