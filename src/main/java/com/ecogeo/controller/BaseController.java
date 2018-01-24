package com.ecogeo.controller;

import com.ecogeo.common.SessionContext;
import com.ecogeo.model.User;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

  protected SessionContext getSessionContext(HttpServletRequest request) {
    SessionContext sessionContext = (SessionContext)request.getSession().getAttribute("sessionContext");
    if( sessionContext == null ) {
      sessionContext = new SessionContext(new User());
    }
    return sessionContext;
  }

}
