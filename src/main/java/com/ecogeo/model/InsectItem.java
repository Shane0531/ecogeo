package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 곤충
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class InsectItem extends FullItem {

  public InsectItem() {

  }

  public InsectItem(String name) {
    this.realName = name;
  }

}
