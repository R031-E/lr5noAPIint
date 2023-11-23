package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Consumption
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-17T23:14:48.983476182Z[GMT]")


public class Consumption   {
  @JsonProperty("date")
  private String date = null;

  @JsonProperty("coldWater")
  private Float coldWater = null;

  @JsonProperty("hotWater")
  private Float hotWater = null;

  @JsonProperty("dayEnergy")
  private Float dayEnergy = null;

  @JsonProperty("nightEnergy")
  private Float nightEnergy = null;

  public Consumption date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Дата расхода, также является уникальным идентификатором каждого объекта consumption.
   * @return date
   **/
  @Schema(required = true, description = "Дата расхода, также является уникальным идентификатором каждого объекта consumption.")
  @NotNull

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Consumption coldWater(Float coldWater) {
    this.coldWater = coldWater;
    return this;
  }

  /**
   * Расход холодной воды.
   * @return coldWater
   **/
  @Schema(required = true, description = "Расход холодной воды.")
  @NotNull

  public Float getColdWater() {
    return coldWater;
  }

  public void setColdWater(Float coldWater) {
    this.coldWater = coldWater;
  }

  public Consumption hotWater(Float hotWater) {
    this.hotWater = hotWater;
    return this;
  }

  /**
   * Расход горячей воды.
   * @return hotWater
   **/
  @Schema(required = true, description = "Расход горячей воды.")
  @NotNull

  public Float getHotWater() {
    return hotWater;
  }

  public void setHotWater(Float hotWater) {
    this.hotWater = hotWater;
  }

  public Consumption dayEnergy(Float dayEnergy) {
    this.dayEnergy = dayEnergy;
    return this;
  }

  /**
   * Дневной расход энергии.
   * @return dayEnergy
   **/
  @Schema(required = true, description = "Дневной расход энергии.")
  @NotNull

  public Float getDayEnergy() {
    return dayEnergy;
  }

  public void setDayEnergy(Float dayEnergy) {
    this.dayEnergy = dayEnergy;
  }

  public Consumption nightEnergy(Float nightEnergy) {
    this.nightEnergy = nightEnergy;
    return this;
  }

  /**
   * Ночной расход энергии.
   * @return nightEnergy
   **/
  @Schema(required = true, description = "Ночной расход энергии.")
  @NotNull

  public Float getNightEnergy() {
    return nightEnergy;
  }

  public void setNightEnergy(Float nightEnergy) {
    this.nightEnergy = nightEnergy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Consumption consumption = (Consumption) o;
    return Objects.equals(this.date, consumption.date) &&
            Objects.equals(this.coldWater, consumption.coldWater) &&
            Objects.equals(this.hotWater, consumption.hotWater) &&
            Objects.equals(this.dayEnergy, consumption.dayEnergy) &&
            Objects.equals(this.nightEnergy, consumption.nightEnergy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, coldWater, hotWater, dayEnergy, nightEnergy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Consumption {\n");

    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    coldWater: ").append(toIndentedString(coldWater)).append("\n");
    sb.append("    hotWater: ").append(toIndentedString(hotWater)).append("\n");
    sb.append("    dayEnergy: ").append(toIndentedString(dayEnergy)).append("\n");
    sb.append("    nightEnergy: ").append(toIndentedString(nightEnergy)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
