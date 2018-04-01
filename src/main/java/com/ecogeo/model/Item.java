package com.ecogeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Item {

  @Id
  protected String realName;

  //계
  protected String species;

  protected String detailSpecies;

  //학명
  protected String scientificName;

  protected String lifeType;

  //문
  protected String phylumName;

  protected String phylumEnName;

  //목
  protected String orderName;

  protected String orderEnName;

  //과
  protected String familyName;

  protected String familyEnName;

  //명명자
  protected String speciesSimpleName;

  protected String subSpeciese;

  //멸종위기
  protected String propCrisis;

  //희귀
  protected String propRare;

  //특산
  protected Boolean propSpecialty;

  //귀화
  protected Boolean propNatural;

  //생태계교란
  protected Boolean propDerange;

  //도래유형
  protected String propAdvent;

  //종구분
  protected String propJong;

  //구계종
  protected String propGugyejong;

//  //천연기념물
//  protected Boolean propMonument;
//
//  //고유종
//  protected Boolean propOrigin;
//
//  //외래종
//  protected Boolean propAlien;
//
//  //국외반출승인종
//  protected Boolean propAoea;

  public Item() {

  }

  public Item(String name) {
    this.realName = name;
  }

  public String getETC() {
    String etc = "";
    if(propCrisis != null) etc += propCrisis;
    if(propCrisis != null && (propGugyejong != null || propRare != null)) etc += ", ";
    if(propGugyejong != null) etc += propGugyejong;
    if(propGugyejong != null && propRare != null) etc += ", ";
    if(propRare != null) etc += propRare;
    return etc;
  }
}
