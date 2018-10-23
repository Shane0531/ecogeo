package com.ecogeo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

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

  public List<String> getETCList() {
    List<String> etc = new ArrayList<>();
    if(!propCrisis.isEmpty()) etc.add("멸"+propCrisis);
    if(!propMonument.isEmpty()) etc.add("천");
    if(!propOrigin.isEmpty()) etc.add("고");
    if(!propAlien.isEmpty()) etc.add("외");
    return etc;
  }

}
