package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 포유류
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class MamItem extends Item {


  public MamItem() {

  }

  public MamItem(String name) {
    this.realName = name;
  }


}
