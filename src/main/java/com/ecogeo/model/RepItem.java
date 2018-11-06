package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 파충류
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class RepItem extends Item {

  public RepItem() {

  }

  public RepItem(String name) {
    this.realName = name;
  }

  @Override
  public String getETC() {
    String etc = "";
    if(propCrisis != null && !propCrisis.isEmpty()) etc += propCrisis;
    if((propCrisis != null && !propCrisis.isEmpty()) && (propMonument != null && !propMonument.isEmpty())) etc += ", ";
    if(propMonument != null && !propMonument.isEmpty()) etc += "천";
    if((propEcosystem != null && !propEcosystem.isEmpty())) etc += "교";
    return etc;
  }

  public String getCrisisValue() {
    if(!propCrisis.isEmpty()) {
      return propCrisis.substring(1).trim();
    } else
      return "";
  }

}
