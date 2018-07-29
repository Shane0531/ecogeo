package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class BirdItem extends Item {

  //도래현황
  protected String currentStatus;

  public BirdItem() {

  }

  public BirdItem(String name) {
    this.realName = name;
  }
}
