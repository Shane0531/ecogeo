package com.ecogeo.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class FullItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Integer idx;

  protected String realName;

  //계
  protected String species;

  //목
  protected String orderName;

  protected String orderEnName;

  //과
  protected String familyName;

  protected String familyEnName;

  //학명
  protected String scientificName;

  //종구분
  protected String propJong;

  //귀화종
  protected String propNatural;

  //생태계교란식물
  protected String propEcosystem;

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

  //천연기념물
  protected String propMonument;

  //보호종
  protected String propProtected;

  //집단번식종
  protected String propGrp;

  //한국고유종
  protected String propOrigin;

  //포획금지야생생물
  protected String propDntw;

  //국외반출승인대상생물자원
  protected String propAoea;

  //외래
  protected String propAlien;

  public FullItem() {

  }

  public FullItem(String name) {
    this.realName = name;
  }

  public String getETC() {
    String etc = "";
    if(propCrisis != null && !propCrisis.isEmpty()) etc += propCrisis;
    if((propCrisis != null && !propCrisis.isEmpty()) && (propMonument != null && !propMonument.isEmpty())) etc += ", ";
    if(propMonument != null && !propMonument.isEmpty()) etc += "천";
    return etc;
  }
}
