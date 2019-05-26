package com.ecogeo.common.interceptor;

import com.ecogeo.common.SessionContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인 권한을 체크 하는 인터셉터
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

  /**
   * preHandle 오버라이드
   * @param request 요청 ServletRequest
   * @param response 응답 ServletResponse
   * @param handler Object
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    SessionContext sessionContext = (SessionContext)request.getSession().getAttribute("sessionContext");
    if( sessionContext == null || !sessionContext.getIsLogin() ) {
      String szUri = request.getRequestURI();
      if( szUri.endsWith(".json") ) {
        response.setContentType("application/json;charset=UTF-8");
      } else if( szUri.endsWith(".ajax") ) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("<script>l_loginAuthFail();</script>");
      } else {
        response.sendRedirect("/login");
      }
      return false;
    }
    return true;
  }

}