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

}
