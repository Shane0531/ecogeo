package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 담수어류
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class FishItem extends FullItem {

  public FishItem() {

  }

  public FishItem(String name) {
    this.realName = name;
  }

}
