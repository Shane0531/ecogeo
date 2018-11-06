package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 양서류
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class AmpItem extends Item {

  public AmpItem() {

  }

  public AmpItem(String name) {
    this.realName = name;
  }

  @Override
  public String getETC() {
    String etc = "";
    if(propCrisis != null && !propCrisis.isEmpty()) etc += propCrisis;
    if((propCrisis != null && !propCrisis.isEmpty()) && (propOrigin != null && !propOrigin.isEmpty())) etc += ", ";
    if(propOrigin != null && !propOrigin.isEmpty()) etc += "고";
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
