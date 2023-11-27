package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * Consumption
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption {
  @NotEmpty(message = "Date must not be empty.")
  private String date;

  //@Size(value = 1, message = "Cold water must not be empty.")
  @Min(value = 1, message = "Cold water must be over 0.")
  private float coldWater;

  //@NotEmpty(message = "Hot water must be not empty.")
  @Min(value = 1, message = "Hot water must be over 0.")
  private float hotWater;

  //@NotEmpty(message = "Day energy must not be empty.")
  @Min(value = 1, message = "Day energy must be over 0.")
  private float dayEnergy;

  //@NotEmpty(message = "Night energy must not be empty.")
  @Min(value = 1, message = "Night energy must be over 0.")
  private float nightEnergy;

  public void setDate(String date){
    this.date = date;
  }

  public String getDate(){
    return date;
  }

  public void setColdWater(float coldWater){
    this.coldWater = coldWater;
  }

  public float getColdWater(){
    return coldWater;
  }

  public void setHotWater(float hotWater){
    this.hotWater = hotWater;
  }

  public float getHotWater(){
    return hotWater;
  }

  public void setDayEnergy(float dayEnergy){
    this.dayEnergy = dayEnergy;
  }

  public float getDayEnergy(){
    return dayEnergy;
  }

  public void setNightEnergy(float nightEnergy){
    this.nightEnergy = nightEnergy;
  }

  public float getNightEnergy(){
    return nightEnergy;
  }
}
