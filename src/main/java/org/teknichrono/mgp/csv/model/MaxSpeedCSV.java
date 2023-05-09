package org.teknichrono.mgp.csv.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.teknichrono.mgp.api.model.MaxSpeed;

public class MaxSpeedCSV {


  @CsvBindByName(column = "NUMBER")
  @CsvBindByPosition(position = 0)
  public Integer number;

  @CsvBindByName(column = "RIDER")
  @CsvBindByPosition(position = 1)
  public String rider;

  @CsvBindByName(column = "NATION")
  @CsvBindByPosition(position = 2)
  public String nation;

  @CsvBindByName(column = "TEAM")
  @CsvBindByPosition(position = 3)
  public String team;

  @CsvBindByName(column = "MOTORCYCLE")
  @CsvBindByPosition(position = 4)
  public String motorcycle;

  @CsvBindByName(column = "SPEED")
  @CsvBindByPosition(position = 5)
  public Float speed;


  public static MaxSpeedCSV from(MaxSpeed maxSpeed) {
    MaxSpeedCSV csv = new MaxSpeedCSV();
    csv.number = maxSpeed.number;
    csv.rider = maxSpeed.rider;
    csv.nation = maxSpeed.nation;
    csv.team = maxSpeed.team;
    csv.motorcycle = maxSpeed.motorcycle;
    csv.speed = maxSpeed.speed;
    return csv;
  }
}
