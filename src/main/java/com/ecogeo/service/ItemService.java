package com.ecogeo.service;

import com.ecogeo.model.Item;
import com.ecogeo.model.PlantItem;
import com.ecogeo.repo.ItemRepo;
import com.ecogeo.repo.PlantItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  PlantItemRepo plantItemRepo;

  public Map<String,List<PlantItem>> selectPlantItem(String items,String filter) {
    Map<String, List<PlantItem>> map = new HashMap<>();
    List<PlantItem> have = new LinkedList<>();
    List<PlantItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        PlantItem i = plantItemRepo.findOneBySpeciesAndRealName(filter,wo);
        if (i != null)
          have.add(i);
        else
          none.add(new PlantItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }
}
