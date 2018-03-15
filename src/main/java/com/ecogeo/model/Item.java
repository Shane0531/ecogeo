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

  //세부분류군명
  protected String detailSpecies;

  //학명
  protected String scientificName;

  protected String scientificKorName;

  protected String lifeType;

  //문
  protected String phylumName;

  //목
  protected String orderName;

  //과
  protected String familyName;

  //명명자
  protected String speciesSimpleName;

  protected String subSpeciese;

  protected String variety;

  protected String formaCheck;

  protected String formaName;

  //멸종위기
  protected Integer propCrisis;

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

  //천연기념물
  protected Boolean propMonument;

  //고유종
  protected Boolean propOrigin;

  //외래종
  protected Boolean propAlien;

  //국외반출승인종
  protected Boolean propAoea;

  public Item() {

  }

  public Item(String name) {
    this.realName = name;
  }
}
