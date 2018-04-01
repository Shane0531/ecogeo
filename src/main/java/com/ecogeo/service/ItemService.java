package com.ecogeo.service;

import com.ecogeo.model.Item;
import com.ecogeo.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

  @Autowired
  ItemRepo itemRepo;

  public Map<String,List<Item>> selectItem(String items,String filter) {
    Map<String, List<Item>> map = new HashMap<>();
    List<Item> have = new LinkedList<>();
    List<Item> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        Item i = itemRepo.findOneBySpeciesAndRealName(filter,wo);
        if (i != null)
          have.add(i);
        else
          none.add(new Item(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }
}
