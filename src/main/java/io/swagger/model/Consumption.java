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


public class Consumption {
  private String date;
  private float coldWater;
  private float hotWater;
  private float dayEnergy;
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
