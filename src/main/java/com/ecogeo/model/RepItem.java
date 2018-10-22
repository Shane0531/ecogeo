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

}
