package com.ecogeo.controller;

import com.ecogeo.model.*;
import com.ecogeo.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ManageController {

  @Autowired
  ManageService manageService;

  private static final Map<String, String> okResponse = new HashMap<>();
  static{
    okResponse.put("content", "OK");
  }

  @GetMapping("/manage")
  public String managePage(@RequestParam String filter, @RequestParam String name, Model model) {
    Object object = manageService.getItem(filter, name);

    model.addAttribute("location", "manage");
    model.addAttribute("model", object);
    model.addAttribute("filter", filter);
    return "/manage";
  }

  @PostMapping("/manage/edit")
  @ResponseBody
  @Transactional
  public Object editItem(@RequestParam(required = false) String phylumName, @RequestParam(required = false) String phylumEnName,
                         @RequestParam(required = false) String className, @RequestParam(required = false) String classEnName,
                         @RequestParam(required = false) String orderName, @RequestParam(required = false) String orderEnName,
                         @RequestParam(required = false) String familyName, @RequestParam(required = false) String familyEnName,
                         @RequestParam String scientificName, @RequestParam String realName,
                         @RequestParam String propCrisis, @RequestParam String filter, @RequestParam String propRare,
                         @RequestParam(required = false) String propMonument, @RequestParam(required = false) String propAlien,
                         @RequestParam(required = false) String propEcosystem) {
    Object object = manageService.getItem(filter, scientificName);

    if(filter.equals("관속식물")) {
      PlantItem item = (PlantItem) object;
      item.setPhylumName(phylumName);item.setPhylumEnName(phylumEnName);
      item.setClassName(className);item.setClassEnName(classEnName);
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);
    } else if(filter.equals("저서동물")){
      BenItem item = (BenItem) object;
      item.setPhylumName(phylumName);item.setPhylumEnName(phylumEnName);
      item.setClassName(className);item.setClassEnName(classEnName);
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);
    } else if(filter.equals("포유류")) {
      MamItem item = (MamItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    } else if(filter.equals("조류")) {
      BirdItem item = (BirdItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    }
    else if(filter.equals("양서강")) {
      AmpItem item = (AmpItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    } else if(filter.equals("파충강")) {
      RepItem item = (RepItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    } else if(filter.equals("곤충")) {
      InsectItem item = (InsectItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    } else {
      FishItem item = (FishItem) object;
      item.setOrderName(orderName);item.setOrderEnName(orderEnName);
      item.setFamilyName(familyName);item.setFamilyEnName(familyEnName);
      item.setRealName(realName);item.setPropCrisis(propCrisis);
      item.setPropRare(propRare);item.setPropMonument(propMonument);
      item.setPropAlien(propAlien);item.setPropEcosystem(propEcosystem);
    }

    return okResponse;
  }
}
