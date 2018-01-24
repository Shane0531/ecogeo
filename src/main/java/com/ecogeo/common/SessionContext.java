package com.ecogeo.common;

import com.ecogeo.model.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
final public class SessionContext implements Serializable {
  private static final long serialVersionUID = 2041945177996932140L;

  int idx;

  String name;

  String id;

  /**
   * 생성자
   * @param user 관리자 정보
   */
  public SessionContext(User user) {
    idx = user.getIdx();
    name = user.getName();
    id = user.getId();
  }

  /**
   * 로그인 여부 체크
   * @return 로그인 여부 (true : 로그인 상태, false : 로그아웃 상태)
   */
  public boolean getIsLogin() {
    if( idx > 0 && !id.isEmpty() ) {
      return true;
    } else {
      return false;
    }
  }
}
