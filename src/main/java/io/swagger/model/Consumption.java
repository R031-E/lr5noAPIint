package io.swagger.model;

import javax.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * Consumption
 */

@Entity
@Table(name = "consumptions")
public class Consumption {

  @Id
  @Basic
  @Column(name = "date")
  @NotEmpty(message = "Date must not be empty.")
  private String date;

  //@Size(value = 1, message = "Cold water must not be empty.")
  @Basic
  @Column(name = "cold_water")
  @Min(value = 1, message = "Cold water must be over 0.")
  private float coldWater;

  //@NotEmpty(message = "Hot water must be not empty.")
  @Basic
  @Column(name = "hot_water")
  @Min(value = 1, message = "Hot water must be over 0.")
  private float hotWater;

  //@NotEmpty(message = "Day energy must not be empty.")
  @Basic
  @Column(name = "day_energy")
  @Min(value = 1, message = "Day energy must be over 0.")
  private float dayEnergy;

  //@NotEmpty(message = "Night energy must not be empty.")
  @Basic
  @Column(name = "night_energy")
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
