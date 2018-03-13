package com.ecogeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpeciesController {

  @GetMapping("/species")
  public String viewSpecies(HttpServletRequest request, Model model) {
    model.addAttribute("location", "species");
    return "/species";
  }

}
