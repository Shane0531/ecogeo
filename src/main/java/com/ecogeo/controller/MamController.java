package com.ecogeo.controller;

import com.ecogeo.model.MamItem;
import com.ecogeo.model.PlantItem;
import com.ecogeo.service.ItemService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MamController {

  @Autowired
  ItemService itemService;

  @PostMapping("/species_result_mam.ajax")
  public String resultThree(@RequestParam String filter, @RequestParam List<String> group_name, @RequestParam List<String> item_group
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

    Map<String,List<MamItem>> selectItem = itemService.selectMamItem(items);
    List<MamItem> have = selectItem.get("have");
    String none = "";
    int noneCount = 0;

    for(MamItem s : selectItem.get("none")) {
      none += s.getRealName() + "\r\n";
      noneCount++;
    }

    //차트에 그릴 카운트들
    Map<String, Integer> chenyeonCount = new HashMap<>();
    Map<String, Integer> myeljongCount = new HashMap<>();
    Map<String, Integer> mokCount = new HashMap<>();
    Map<String, Integer> guaCount = new HashMap<>();
    myeljongCount.put("기타",0);
    myeljongCount.put("total",0);
    chenyeonCount.put("기타",0);
    chenyeonCount.put("total",0);
    mokCount.put("total",0);
    guaCount.put("total",0);

    for(MamItem s : have) {
      if(guaCount.containsKey(s.getFamilyName())) {
        guaCount.put(s.getFamilyName(),guaCount.get(s.getFamilyName()) + 1);
      } else {
        guaCount.put(s.getFamilyName(),1);
      }
      guaCount.put("total",guaCount.get("total")+1);

      if(mokCount.containsKey(s.getOrderName())) {
        mokCount.put(s.getOrderName(),mokCount.get(s.getOrderName()) + 1);
      } else {
        mokCount.put(s.getOrderName(),1);
      }
      mokCount.put("total",mokCount.get("total")+1);

      if(!s.getPropCrisis().isEmpty() && s.getPropCrisis() != null) {
        if(myeljongCount.containsKey(s.getPropCrisis())) {
          myeljongCount.put(s.getPropCrisis(),myeljongCount.get(s.getPropCrisis()) + 1);
        } else {
          myeljongCount.put(s.getPropCrisis(),1);
        }
      } else {
        myeljongCount.put("기타",myeljongCount.get("기타")+1);
      }
      myeljongCount.put("total",myeljongCount.get("total")+1);

      if(!s.getPropMonument().isEmpty() && s.getPropMonument() != null) {
        if(chenyeonCount.containsKey(s.getPropMonument())) {
          chenyeonCount.put(s.getPropMonument(),chenyeonCount.get(s.getPropMonument()) + 1);
        } else {
          chenyeonCount.put(s.getPropMonument(),1);
        }
      } else {
        chenyeonCount.put("기타",chenyeonCount.get("기타")+1);
      }
      chenyeonCount.put("total",chenyeonCount.get("total")+1);

    }

    Map<UpperItem,Map<UpperItem,List<ItemDTO>>> result = new HashMap<>();

    Map<String,List<MamItem>> order = new HashMap<>();

    //먼저 OrderName으로 분류
    for(MamItem i : have) {
      if(order.containsKey(i.getOrderName())) {
        order.get(i.getOrderName()).add(i);
      } else {
        List<MamItem> itemList = new ArrayList<>();
        itemList.add(i);
        order.put(i.getOrderName(), itemList);
      }
    }

    for(String key : order.keySet()) {
      Map<UpperItem,List<ItemDTO>> family = new HashMap<>();
      List<MamItem> orderList = order.get(key);
      for(MamItem i : orderList) {
        ItemDTO dto = new ItemDTO();
        dto.pack(i);

        List<String> groupNames = new ArrayList<>();
        for(int v = 0; v < item_group.size(); v++) {
          if(item_group.get(v).replace("\r\n","").equalsIgnoreCase(dto.getRealName()) && !group_name.get(v).equals("")) {
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
    model.addAttribute("none", none);
    model.addAttribute("noneCount", noneCount);
    model.addAttribute("result", result);
    model.addAttribute("totalMap", totalMap);
    model.addAttribute("filter",filter);
    model.addAttribute("myeljongCount",myeljongCount);
    model.addAttribute("chenyeonCount",chenyeonCount);
    model.addAttribute("mokCount",mokCount);
    model.addAttribute("guaCount",guaCount);
    return "/species_result_ajax_three";
  }

  @Data
  public static class ItemDTO extends MamItem {
    List<String> group;

    public void pack(MamItem i) {
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
    }


    public String[] getScientificNameArray() {
      return scientificName.split(" ");
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
