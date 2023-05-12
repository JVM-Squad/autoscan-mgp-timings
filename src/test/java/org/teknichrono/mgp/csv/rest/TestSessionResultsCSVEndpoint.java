package org.teknichrono.mgp.csv.rest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.teknichrono.mgp.it.WireMockExtensions;

import static io.restassured.RestAssured.given;

@Tag("integration")
@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
class TestSessionResultsCSVEndpoint {

  @Test
  void getSessionClassification() {
    String rootOutput = given()
        .when().get("/api/csv/2021/QAT/motogp/fp3")
        .then()
        .statusCode(200)
        .contentType("text/csv")
        .extract().asString();
    /*
    assertThat(classification.classifications.size()).isEqualTo(22);
    assertThat(classification.classifications.get(0).rider.full_name).containsIgnoringCase("Franco");
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(17);
    assertThat(classification.sessionFiles.classification).isNotNull();
    assertThat(classification.records.stream().filter(r -> r.type.equalsIgnoreCase("bestLap")).findFirst().get().rider.full_name).containsIgnoringCase("Lorenzo");

    assertThat(classification.classifications.size()).isEqualTo(22);
    assertThat(classification.classifications.get(0).position).isEqualTo(1);
    assertThat(classification.classifications.get(0).rider.number).isEqualTo(21);
    assertThat(classification.classifications.get(0).rider.full_name).containsIgnoringCase("MORBIDELLI");
    assertThat(classification.classifications.get(0).rider.country.iso).isEqualTo("ITA");
    assertThat(classification.classifications.get(0).team).containsIgnoringCase("Yamaha");
    assertThat(classification.classifications.get(0).constructor).containsIgnoringCase("Yamaha");
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(17);
    assertThat(classification.classifications.get(0).gapToFirst).isEqualTo(0f);
    assertThat(classification.classifications.get(0).bestLapTime).isEqualTo("1'54.676");
    assertThat(classification.classifications.get(0).bestLapNumber).isEqualTo(14);
    assertThat(classification.classifications.get(0).topSpeed).isEqualTo(343.9f);

    assertThat(classification.classifications.get(1).constructor).isEqualTo("Aprilia");
    assertThat(classification.classifications.get(1).gapToPrevious).isEqualTo(0.165f);
    assertThat(classification.classifications.get(1).rider.country.iso).isEqualTo("SPA");

    assertTrue(classification.classifications.stream().allMatch(d -> d.position > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.number > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.full_name != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.country.iso.length() == 3));
    assertTrue(classification.classifications.stream().allMatch(d -> d.team != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.constructor != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.totalLaps > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.bestLapNumber > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.topSpeed > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.bestLapTime != null));

    float current = 0f;
    for (SessionClassificationOutput d : classification.classifications) {
      assertThat(d.gapToFirst).isGreaterThanOrEqualTo(current);
      current = d.gapToFirst;
    }*/
  }

  @Test
  void getSessionClassificationFailsBecauseOfSession() {
    given()
        .when().get("/api/csv/2021/QAT/motogp/fp9")
        .then()
        .statusCode(404);
  }

  @Test
  void getSessionClassificationFailsBecauseOfEvent() {
    given()
        .when().get("/api/csv/2021/NOP/motogp/fp3")
        .then()
        .statusCode(404);
  }

  @Test
  void getSessionClassificationFailsBecauseOfCategory() {
    given()
        .when().get("/api/csv/2021/QAT/MOTO9/fp3")
        .then()
        .statusCode(404);
  }

  @Test
  void getTestClassificationDetailsError() {
    given()
        .when().get("/api/csv/2022/MY1/GP/FP1")
        .then()
        .statusCode(500);
  }

  @Test
  void getTestSessionClassification() {
    String rootOutput = given()
        .when().get("/api/csv/2022/JE1/GP/FP2")
        .then()
        .statusCode(200)
        .contentType("text/csv")
        .extract().asString();
    /*
    assertThat(classification.classifications.size()).isEqualTo(29);
    assertThat(classification.classifications.get(0).rider.full_name).containsIgnoringCase("Francesco");
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(42);
    assertThat(classification.sessionFiles).isNotNull();

    assertThat(classification.classifications.size()).isEqualTo(29);
    assertThat(classification.classifications.get(0).position).isEqualTo(1);
    assertThat(classification.classifications.get(0).rider.number).isEqualTo(63);
    assertThat(classification.classifications.get(0).rider.full_name).containsIgnoringCase("BAGNAIA");
    assertThat(classification.classifications.get(0).rider.country.iso).isEqualTo("ITA");
    assertThat(classification.classifications.get(0).team).containsIgnoringCase("Ducati");
    assertThat(classification.classifications.get(0).constructor).containsIgnoringCase("Ducati");
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(42);
    assertThat(classification.classifications.get(0).gapToFirst).isEqualTo(0f);
    assertThat(classification.classifications.get(0).bestLapTime).isEqualTo("1'36.872");
    assertThat(classification.classifications.get(0).bestLapNumber).isEqualTo(12);
    assertThat(classification.classifications.get(0).topSpeed).isEqualTo(295.8f);

    assertThat(classification.classifications.get(1).constructor).isEqualTo("Yamaha");
    assertThat(classification.classifications.get(1).gapToPrevious).isEqualTo(0.452f);
    assertThat(classification.classifications.get(1).rider.country.iso).isEqualTo("FRA");

    assertTrue(classification.classifications.stream().allMatch(d -> d.position != null ? d.position > 0 : true));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.number > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.full_name != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.rider.country.iso.length() == 3));
    assertTrue(classification.classifications.stream().allMatch(d -> d.team != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.constructor != null));
    assertTrue(classification.classifications.stream().allMatch(d -> d.totalLaps == null || d.totalLaps > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.bestLapNumber == null || d.bestLapNumber > 0));
    assertTrue(classification.classifications.stream().allMatch(d -> d.topSpeed == null || d.topSpeed > 0));

    float current = 0f;
    for (SessionClassificationOutput d : classification.classifications) {
      if (d.position != null) {
        assertThat(d.gapToFirst).isGreaterThanOrEqualTo(current);
        current = d.gapToFirst;
      } else {
        assertThat(d.gapToFirst).isNull();
        current = Float.MAX_VALUE;
      }
    }
    */
  }


  @Test
  void getRaceClassification() {
    String rootOutput = given()
        .when().get("/api/csv/2021/QAT/motogp/rac")
        .then()
        .statusCode(200)
        .contentType("text/csv")
        .extract().asString();

    /*
    assertThat(classification.classifications.size()).isEqualTo(22);
    assertThat(classification.classifications.get(0).position).isEqualTo(1);
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(22);
    assertThat(classification.sessionFiles.classification).isNotNull();
    assertThat(classification.records.stream().filter(r -> r.type.equalsIgnoreCase("poleLap")).findFirst().get().rider.full_name).containsIgnoringCase("Bagnaia");

    assertThat(classification.classifications.get(0).averageSpeed).isEqualTo(167.1f);
    assertThat(classification.classifications.get(0).position).isEqualTo(1);
    assertThat(classification.classifications.get(0).points).isEqualTo(25);
    assertThat(classification.classifications.get(0).rider.country.iso).isEqualTo("SPA");
    assertThat(classification.classifications.get(0).totalLaps).isEqualTo(22);
    assertThat(classification.classifications.get(0).totalTime).isEqualTo("42'28.663");

    assertThat(classification.classifications.get(1).constructor).containsIgnoringCase("Ducati");
    assertThat(classification.classifications.get(1).rider.full_name).containsIgnoringCase("Zarco");

    Float currentGapToFirst = 0f;
    Integer currentPoints = Integer.MAX_VALUE;
    for (SessionClassificationOutput d : classification.classifications) {
      assertThat(d.gapToFirst == null || currentGapToFirst == null || d.gapToFirst.floatValue() >= currentGapToFirst).isTrue();
      if (currentGapToFirst == null) {
        assertThat(d.gapToFirst).isNull();
      } else {
        currentGapToFirst = d.gapToFirst;
      }
      assertThat(d.points == 0 || d.points.intValue() < currentPoints).isTrue();
      if (currentPoints == null) {
        assertThat(d.points).isEqualTo(0);
      } else {
        currentPoints = d.points;
      }
    }

    assertTrue(classification.classifications.stream().anyMatch(d -> d.position > 0));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.rider.number > 0));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.rider.full_name != null));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.rider.country.iso.length() == 3));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.team != null));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.constructor != null));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.totalLaps > 0));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.averageSpeed > 0));
    assertTrue(classification.classifications.stream().anyMatch(d -> d.totalTime != null));

     */
  }
}