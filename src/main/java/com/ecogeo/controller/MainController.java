package com.ecogeo.controller;

import com.ecogeo.common.SessionContext;
import com.ecogeo.model.User;
import com.ecogeo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController extends BaseController {

  @Autowired
  UserRepo userRepo;

  @GetMapping("/login")
  public String viewLogin(HttpServletRequest request, Model model) {
    SessionContext sessionContext = getSessionContext(request);
    model.addAttribute("sessionContext",sessionContext);
    if (sessionContext.getIsLogin()) {
      return "redirect:/species";
    }
    request.getSession().removeAttribute("tmpSessionContext");
    return "/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String loginCheck(HttpServletRequest request, Model model, @RequestParam String id, @RequestParam String password) {
    User user = userRepo.selectLogin(id, password);

    if( user != null) {
      // Set Session
      request.getSession().setAttribute("sessionContext", new SessionContext(user));
      return "redirect:/species";
    } else {
      model.addAttribute("show_error", true);
      return "/login";
    }
  }

  // 로그아웃
  @RequestMapping(value="/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/login";
  }

  @GetMapping("/")
  public String redirectIndex(HttpServletRequest request, Model model) {
    return "redirect:/species";
  }

  @GetMapping("/chart")
  public String viewChart(HttpServletRequest request, Model model) {
    model.addAttribute("location", "chart");
    return "/chart";
  }

  @GetMapping("/form")
  public String viewForm(HttpServletRequest request, Model model) {
    model.addAttribute("location", "form");
    return "/form";
  }
}
