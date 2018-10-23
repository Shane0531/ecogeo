package com.ecogeo.controller;

import com.ecogeo.model.FishItem;
import com.ecogeo.model.MamItem;
import com.ecogeo.service.ItemService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FishController {

  @Autowired
  ItemService itemService;

  @PostMapping("/species_result_fish.ajax")
  public String resultThree(@RequestParam String filter, @RequestParam List<String> group_name, @RequestParam List<String> item_group
      , Model model) {
    Map<String,MamController.Total> totalMap = new HashMap<>();
    for(String name : group_name) {
      if(!name.equals(""))
        totalMap.put(name,new MamController.Total());
    }
    totalMap.put("totalKSH",new MamController.Total());
    String items = "";
    for(String item : item_group) {
      items += item + "\r\n";
    }

    Map<String,List<FishItem>> selectItem = itemService.selectFishItem(items);
    List<FishItem> have = selectItem.get("have");
    String none = "";
    int noneCount = 0;

    for(FishItem s : selectItem.get("none")) {
      none += s.getRealName() + "\r\n";
      noneCount++;
    }

    Map<MamController.UpperItem,Map<MamController.UpperItem,List<ItemDTO>>> result = new HashMap<>();

    Map<String,List<FishItem>> order = new HashMap<>();

    //먼저 OrderName으로 분류
    for(FishItem i : have) {
      if(order.containsKey(i.getOrderName())) {
        order.get(i.getOrderName()).add(i);
      } else {
        List<FishItem> itemList = new ArrayList<>();
        itemList.add(i);
        order.put(i.getOrderName(), itemList);
      }
    }

    for(String key : order.keySet()) {
      Map<MamController.UpperItem,List<ItemDTO>> family = new HashMap<>();
      List<FishItem> orderList = order.get(key);
      for(FishItem i : orderList) {
        ItemDTO dto = new ItemDTO();
        dto.pack(i);

        List<String> groupNames = new ArrayList<>();
        for(int v = 0; v < item_group.size(); v++) {
          if(item_group.get(v).contains(dto.getRealName()) && !group_name.get(v).equals("")) {
            groupNames.add(group_name.get(v));
            MamController.Total total = totalMap.get(group_name.get(v));
            total.getMok().add(dto.getOrderName());
            total.getGua().add(dto.getFamilyName());
            total.getJong().add(dto.getRealName());
            totalMap.put(group_name.get(v),total);
          }
        }
        MamController.Total total = totalMap.get("totalKSH");
        total.getMok().add(dto.getOrderName());
        total.getGua().add(dto.getFamilyName());
        total.getJong().add(dto.getRealName());
        totalMap.put("totalKSH",total);
        dto.setGroup(groupNames);

        if(family.containsKey(new MamController.UpperItem(i.getFamilyEnName(),i.getFamilyName()))) {
          family.get(new MamController.UpperItem(i.getFamilyEnName(),i.getFamilyName())).add(dto);
        } else {
          List<ItemDTO> itemList = new ArrayList<>();
          itemList.add(dto);
          family.put(new MamController.UpperItem(i.getFamilyEnName(),i.getFamilyName()), itemList);
        }
      }
      result.put(new MamController.UpperItem(orderList.get(0).getOrderEnName(),orderList.get(0).getOrderName()),family);
    }







    model.addAttribute("group_name", group_name);
    model.addAttribute("none", none);
    model.addAttribute("noneCount", noneCount);
    model.addAttribute("result", result);
    model.addAttribute("totalMap", totalMap);
    model.addAttribute("filter",filter);
    return "/species_result_ajax_three";
  }

  @Data
  public static class ItemDTO extends FishItem {
    List<String> group;

    public void pack(FishItem i) {
      realName = i.getRealName();
      species = i.getSpecies();
      scientificName = i.getScientificName();
      orderName = i.getOrderName();
      orderEnName = i.getOrderEnName();
      familyName = i.getFamilyName();
      familyEnName = i.getFamilyEnName();
      propCrisis = i.getPropCrisis();
      propRare = i.getPropRare();
      propSpecialty = i.getPropSpecialty();
      propNatural = i.getPropNatural();
      propJong = i.getPropJong();
      propGugyejong = i.getPropGugyejong();
      propMonument = i.getPropMonument();
      propOrigin = i.getPropOrigin();
      propEcosystem = i.getPropEcosystem();
      propAlien = i.getPropAlien();
    }


    public String[] getScientificNameArray() {
      return scientificName.split(" ");
    }
  }
}
