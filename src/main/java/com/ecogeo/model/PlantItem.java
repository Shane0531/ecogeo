package com.ecogeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 관속식물
 */

@Data
@Entity
public class PlantItem {
  protected String realName;

  //계
  protected String species;

  //문
  protected String phylumName;

  protected String phylumEnName;

  //강
  protected String className;

  protected String classEnName;

  //목
  protected String orderName;

  protected String orderEnName;

  //과
  protected String familyName;

  protected String familyEnName;

  //학명
  @Id
  protected String scientificName;

  //생활형
  protected String lifeType;

  //종구분
  protected String propJong;

  //귀화종
  protected String propNatural;

  //멸종위기
  protected String propCrisis;

  //희귀종
  protected String propRare;

  //한국특산식물
  protected String propSpecialty;

  //수생식물
  protected String propAquatic;

  //구계종
  protected String propGugyejong;

  public PlantItem() {

  }

  public PlantItem(String name) {
    this.realName = name;
  }

  public String getETC() {
    String etc = "";
    if(!propCrisis.isEmpty()) etc += propCrisis;
    if(!propCrisis.isEmpty() && (!propGugyejong.isEmpty()|| !propRare.isEmpty())) etc += ", ";
    if(!propGugyejong.isEmpty()) etc += propGugyejong;
    if((!propGugyejong.isEmpty() || !propCrisis.isEmpty()) && !propSpecialty.isEmpty()) etc += ", ";
    if(!propSpecialty.isEmpty()) etc += propSpecialty;
    if(!propGugyejong.isEmpty() && !propRare.isEmpty()) etc += ", ";
    if(!propRare.isEmpty()) etc += propRare;
    return etc;
  }

}
