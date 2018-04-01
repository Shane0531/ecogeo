package com.ecogeo.controller;

import com.ecogeo.model.Item;
import com.ecogeo.service.ItemService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SpeciesController {

  @Autowired
  ItemService itemService;

  @GetMapping("/species")
  public String viewSpecies(HttpServletRequest request, Model model) {
    model.addAttribute("location", "species");
    return "/species";
  }

  @PostMapping("/species_result.ajax")
  public String result(@RequestParam List<String> group_name, @RequestParam List<String> item_group
          , Model model) {
    Map<String,Total> totalMap = new HashMap<>();
    for(String name : group_name) {
      if(!name.equals(""))
        totalMap.put(name,new Total());
    }
    totalMap.put("totalKSH",new Total());
    String items = "";
    for(String item : item_group) {
      items += item + "\r\n";
    }

    Map<String,List<Item>> selectItem = itemService.selectItem(items);
    List<Item> have = selectItem.get("have");
    String none = "";
    int noneCount = 0;

    for(Item s : selectItem.get("none")) {
      none += s.getRealName() + "\r\n";
      noneCount++;
    }

    Map<UpperItem,Map<UpperItem,List<ItemDTO>>> result = new HashMap<>();

    Map<String,List<Item>> order = new HashMap<>();

    //먼저 OrderName으로 분류
    for(Item i : have) {
      if(order.containsKey(i.getOrderName())) {
        order.get(i.getOrderName()).add(i);
      } else {
        List<Item> itemList = new ArrayList<>();
        itemList.add(i);
        order.put(i.getOrderName(), itemList);
      }
    }

    for(String key : order.keySet()) {
      Map<UpperItem,List<ItemDTO>> family = new HashMap<>();
      List<Item> orderList = order.get(key);
      for(Item i : orderList) {
        ItemDTO dto = new ItemDTO();
        dto.pack(i);

        List<String> groupNames = new ArrayList<>();
        for(int v = 0; v < item_group.size(); v++) {
          if(item_group.get(v).contains(dto.getRealName()) && !group_name.get(v).equals("")) {
            groupNames.add(group_name.get(v));
            Total total = totalMap.get(group_name.get(v));
            total.getMok().add(dto.getOrderName());
            total.getGua().add(dto.getFamilyName());
            total.getJong().add(dto.getRealName());
            totalMap.put(group_name.get(v),total);
          }
        }
        Total total = totalMap.get("totalKSH");
        total.getMok().add(dto.getOrderName());
        total.getGua().add(dto.getFamilyName());
        total.getJong().add(dto.getRealName());
        totalMap.put("totalKSH",total);
        dto.setGroup(groupNames);

        if(family.containsKey(new UpperItem(i.getFamilyEnName(),i.getFamilyName()))) {
          family.get(new UpperItem(i.getFamilyEnName(),i.getFamilyName())).add(dto);
        } else {
          List<ItemDTO> itemList = new ArrayList<>();
          itemList.add(dto);
          family.put(new UpperItem(i.getFamilyEnName(),i.getFamilyName()), itemList);
        }
      }
      result.put(new UpperItem(orderList.get(0).getOrderEnName(),orderList.get(0).getOrderName()),family);
    }







    model.addAttribute("group_name", group_name);
    model.addAttribute("result", result);
    model.addAttribute("none", none);
    model.addAttribute("noneCount", noneCount);
    model.addAttribute("result", result);
    model.addAttribute("totalMap", totalMap);
    return "/species_result_ajax";
  }

  @Data
  public static class ItemDTO extends Item {
    List<String> group;

    public void pack(Item i) {
      realName = i.getRealName();
      species = i.getSpecies();
      detailSpecies = i.getDetailSpecies();
      scientificName = i.getScientificName();
      lifeType = i.getLifeType();
      phylumName = i.getPhylumName();
      phylumEnName = i.getPhylumEnName();
      orderName = i.getOrderName();
      orderEnName = i.getOrderEnName();
      familyName = i.getFamilyName();
      familyEnName = i.getFamilyEnName();
      speciesSimpleName = i.getSpeciesSimpleName();
      subSpeciese = i.getSubSpeciese();
      propCrisis = i.getPropCrisis();
      propRare = i.getPropRare();
      propSpecialty = i.getPropSpecialty();
      propNatural = i.getPropNatural();
      propDerange = i.getPropDerange();
      propAdvent = i.getPropAdvent();
      propJong = i.getPropJong();
      propGugyejong = i.getPropGugyejong();
//      propMonument = i.getPropMonument();
//      propOrigin = i.getPropOrigin();
//      propAlien = i.getPropAlien();
//      propAoea = i.getPropAoea();
    }
  }

  @Data
  public static class UpperItem {
    String EnName;
    String KorName;

    public UpperItem(String en,String kor) {
      EnName = en;
      KorName = kor;
    }
  }

  @Data
  public static class Total {
    Set<String> mok = new HashSet<>();
    Set<String> gua = new HashSet<>();
    Set<String> jong = new HashSet<>();

    public String getTotal() {
      return mok.size() + "목 " + gua.size() + "과 " + jong.size() + "종";
    }
  }
}
