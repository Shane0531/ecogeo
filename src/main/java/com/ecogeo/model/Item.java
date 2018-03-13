package com.ecogeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Item {
  @Id
  @GeneratedValue
  int idx;

  //계
  String species;

  //세부분류군명
  String detailSpecies;

  //학명
  String scientificName;

  String scientificKorName;

  String lifeType;

  //문
  String phylumName;

  //목
  String orderName;

  //과
  String familyName;

  //명명자
  String speciesSimpleName;

  String realName;

  String subSpeciese;

  String variety;

  String formaCheck;

  String formaName;

  //멸종위기
  Integer propCrisis;

  //희귀
  String propRare;

  //특산
  Boolean propSpecialty;

  //귀화
  Boolean propNatural;

  //생태계교란
  Boolean propDerange;

  //도래유형
  String propAdvent;

  //천연기념물
  Boolean propMonument;

  //고유종
  Boolean propOrigin;

  //외래종
  Boolean propAlien;

  //국외반출승인종
  Boolean propAoea;

}
