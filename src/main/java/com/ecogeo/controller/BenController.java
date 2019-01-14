package com.ecogeo.controller;

import com.ecogeo.model.BenItem;
import com.ecogeo.service.ItemService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class BenController {

  @Autowired
  ItemService itemService;

  @PostMapping("/species_result_ben.ajax")
  public String resultBen(@RequestParam String filter, @RequestParam List<String> group_name, @RequestParam List<String> item_group
      , Model model) {
    Map<String,SpeciesController.Total> totalMap = new HashMap<>();
    for(String name : group_name) {
      if(!name.equals(""))
        totalMap.put(name,new SpeciesController.Total());
    }
    totalMap.put("totalKSH",new SpeciesController.Total());
    String items = "";
    for(String item : item_group) {
      items += item + "\r\n";
    }

    Map<String,List<BenItem>> selectItem = itemService.selectBenItem(items);
    List<BenItem> have = selectItem.get("have");
    String none = "";
    int noneCount = 0;

    for(BenItem s : selectItem.get("none")) {
      none += s.getRealName() + "\r\n";
      noneCount++;
    }

    //차트에 그릴 카운트들
    Map<String, Integer> myeljongCount = new HashMap<>();
    Map<String, Integer> moonCount = new HashMap<>();
    Map<String, Integer> gangCount = new HashMap<>();
    Map<String, Integer> mokCount = new HashMap<>();
    Map<String, Integer> guaCount = new HashMap<>();
    myeljongCount.put("기타",0);
    myeljongCount.put("total",0);
    moonCount.put("total",0);
    gangCount.put("total",0);
    mokCount.put("total",0);
    guaCount.put("total",0);

    for(BenItem s : have) {
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

      if(gangCount.containsKey(s.getClassName())) {
        gangCount.put(s.getClassName(),gangCount.get(s.getClassName()) + 1);
      } else {
        gangCount.put(s.getClassName(),1);
      }
      gangCount.put("total",gangCount.get("total")+1);

      if(moonCount.containsKey(s.getPhylumName())) {
        moonCount.put(s.getPhylumName(),moonCount.get(s.getPhylumName()) + 1);
      } else {
        moonCount.put(s.getPhylumName(),1);
      }
      moonCount.put("total",moonCount.get("total")+1);

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

    }

    Map<String,List<BenItem>> phylum = new HashMap<>();

    //먼저 문으로 분류
    for(BenItem i : have) {
      if(phylum.containsKey(i.getPhylumName())) {
        phylum.get(i.getPhylumName()).add(i);
      } else {
        List<BenItem> itemList = new ArrayList<>();
        itemList.add(i);
        phylum.put(i.getPhylumName(), itemList);
      }
    }

    Map<String,Map<String, List<BenItem>>> clas = new HashMap<>();

    //강으로 분류
    for(String key : phylum.keySet()) {
      List<BenItem> clasList = phylum.get(key);
      Map<String,List<BenItem>> temp = new HashMap<>();
      for(BenItem item : clasList) {
        if(temp.containsKey(item.getClassName())) {
          temp.get(item.getClassName()).add(item);
        } else {
          List<BenItem> itemList = new ArrayList<>();
          itemList.add(item);
          temp.put(item.getClassName(), itemList);
        }
      }
      clas.put(key,temp);
    }

    Map<String,Map<String,Map<String, List<BenItem>>>> order = new HashMap<>();

    for(String key : clas.keySet()) {
      Map<String, List<BenItem>> clast = clas.get(key);
      Map<String,Map<String,List<BenItem>>> temp = new HashMap<>();
      for(String key2 : clast.keySet()) {
        List<BenItem> pitems = clast.get(key2);
        Map<String,List<BenItem>> temp2 = new HashMap<>();

        for(BenItem item : pitems) {
          if(temp2.containsKey(item.getOrderName())) {
            temp2.get(item.getOrderName()).add(item);
          } else {
            List<BenItem> itemList = new ArrayList<>();
            itemList.add(item);
            temp2.put(item.getOrderName(), itemList);
          }
        }

        temp.put(key2,temp2);
      }

      order.put(key,temp);

    }


    Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,List<ItemDTO>>>>> result = new HashMap<>();
    String clasname = "";
    String orderName = "";

    for(String key : order.keySet()) {
      Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,List<ItemDTO>>>> d3 = new HashMap<>();
      Map<String,Map<String,List<BenItem>>> fs = order.get(key);
      for(String key2 : fs.keySet()) {
        Map<SpeciesController.UpperItem,Map<SpeciesController.UpperItem,List<ItemDTO>>> d2 = new HashMap<>();
        Map<String,List<BenItem>> sc = fs.get(key2);
        for(String key3 : sc.keySet()) {
          Map<SpeciesController.UpperItem,List<ItemDTO>> d1 = new HashMap<>();
          List<BenItem> plantItemList = sc.get(key3);

          for(BenItem i : plantItemList) {
            ItemDTO dto = new ItemDTO();
            dto.pack(i);
            List<String> groupNames = new ArrayList<>();
            for(int v = 0 ; v < item_group.size(); v++) {
              List<String> group = Arrays.asList(item_group.get(v).split("\r\n"));
              if(group.contains(dto.getRealName()) && !group_name.get(v).equals("")) {
                groupNames.add(group_name.get(v));
                SpeciesController.Total total = totalMap.get(group_name.get(v));
                total.getMok().add(dto.getOrderName());
                total.getGua().add(dto.getFamilyName());
                total.getJong().add(dto.getRealName());
                total.getMoon().add(dto.getPhylumName());
                total.getGang().add(dto.getClassName());
                totalMap.put(group_name.get(v),total);
              }
            }
            SpeciesController.Total total = totalMap.get("totalKSH");
            total.getMok().add(dto.getOrderName());
            total.getGua().add(dto.getFamilyName());
            total.getJong().add(dto.getRealName());
            total.getMoon().add(dto.getPhylumName());
            total.getGang().add(dto.getClassName());
            totalMap.put("totalKSH",total);
            dto.setGroup(groupNames);

            if(d1.containsKey(new SpeciesController.UpperItem(i.getFamilyEnName(),i.getFamilyName()))) {
              d1.get(new SpeciesController.UpperItem(i.getFamilyEnName(),i.getFamilyName())).add(dto);
            } else {
              List<ItemDTO> itemList = new ArrayList<>();
              itemList.add(dto);
              d1.put(new SpeciesController.UpperItem(i.getFamilyEnName(),i.getFamilyName()), itemList);
            }
          }
          d2.put(new SpeciesController.UpperItem(plantItemList.get(0).getOrderEnName(),plantItemList.get(0).getOrderName()),d1);
          orderName = key3;
        }
        d3.put(new SpeciesController.UpperItem(sc.get(orderName).get(0).getClassEnName(),sc.get(orderName).get(0).getClassName()),d2);
        clasname = key2;
      }
      result.put(new SpeciesController.UpperItem(fs.get(clasname).get(orderName).get(0).getPhylumEnName(),fs.get(clasname).get(orderName).get(0).getPhylumName()),d3);
    }



    model.addAttribute("group_name", group_name);
    model.addAttribute("none", none);
    model.addAttribute("noneCount", noneCount);
    model.addAttribute("result", result);
    model.addAttribute("totalMap", totalMap);
    model.addAttribute("filter",filter);
    model.addAttribute("myeljongCount",myeljongCount);
    model.addAttribute("moonCount",moonCount);
    model.addAttribute("gangCount",gangCount);
    model.addAttribute("mokCount",mokCount);
    model.addAttribute("guaCount",guaCount);
    return "/species_result_ajax_five";
  }

  @Data
  public static class ItemDTO extends BenItem {
    List<String> group;

    public void pack(BenItem i) {
      realName = i.getRealName();
      species = i.getSpecies();
      scientificName = i.getScientificName();
      lifeType = i.getLifeType();
      orderName = i.getOrderName();
      orderEnName = i.getOrderEnName();
      familyName = i.getFamilyName();
      familyEnName = i.getFamilyEnName();
      phylumName = i.getPhylumName();
      phylumEnName = i.getPhylumEnName();
      className = i.getClassName();
      classEnName = i.getClassEnName();
      propCrisis = i.getPropCrisis();
      propRare = i.getPropRare();
      propSpecialty = i.getPropSpecialty();
      propJong = i.getPropJong();
      propGugyejong = i.getPropGugyejong();
    }


    public String[] getScientificNameArray() {
      return scientificName.split(" ");
    }
  }
}
