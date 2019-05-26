package com.ecogeo.service;

import com.ecogeo.model.*;
import com.ecogeo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

  @Autowired PlantItemRepo plantItemRepo;

  @Autowired MamItemRepo mamItemRepo;

  @Autowired AmpItemRepo ampItemRepo;

  @Autowired BenItemRepo benItemRepo;

  @Autowired BirdItemRepo birdItemRepo;

  @Autowired FishItemRepo fishItemRepo;

  @Autowired InsectItemRepo insectItemRepo;

  @Autowired RepItemRepo repItemRepo;

  public Map<String,List<PlantItem>> selectPlantItem(String items) {
    Map<String, List<PlantItem>> map = new HashMap<>();
    List<PlantItem> have = new LinkedList<>();
    List<PlantItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        PlantItem i = plantItemRepo.findOneByRealName(wo);
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

  public Map<String,List<MamItem>> selectMamItem(String items) {
    Map<String, List<MamItem>> map = new HashMap<>();
    List<MamItem> have = new LinkedList<>();
    List<MamItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        MamItem i = mamItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new MamItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<AmpItem>> selectAmpItem(String items) {
    Map<String, List<AmpItem>> map = new HashMap<>();
    List<AmpItem> have = new LinkedList<>();
    List<AmpItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        AmpItem i = ampItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new AmpItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<BenItem>> selectBenItem(String items) {
    Map<String, List<BenItem>> map = new HashMap<>();
    List<BenItem> have = new LinkedList<>();
    List<BenItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        BenItem i = benItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new BenItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<BirdItem>> selectBirdItem(String items) {
    Map<String, List<BirdItem>> map = new HashMap<>();
    List<BirdItem> have = new LinkedList<>();
    List<BirdItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        BirdItem i = birdItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new BirdItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<FishItem>> selectFishItem(String items) {
    Map<String, List<FishItem>> map = new HashMap<>();
    List<FishItem> have = new LinkedList<>();
    List<FishItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        FishItem i = fishItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new FishItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<InsectItem>> selectInsectItem(String items) {
    Map<String, List<InsectItem>> map = new HashMap<>();
    List<InsectItem> have = new LinkedList<>();
    List<InsectItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        List<InsectItem> list = insectItemRepo.findOneByRealName(wo);
        if(list.size() > 0) {
          if(list.size() > 1)
            System.out.println("중복되는 이름이 있음 : " + list.get(0).getRealName());

          for(int k = 0; k < list.size(); k++) {
            InsectItem i = list.get(k);
            have.add(i);
          }
        } else {
          none.add(new InsectItem(wo));
        }
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }

  public Map<String,List<RepItem>> selectRepItem(String items) {
    Map<String, List<RepItem>> map = new HashMap<>();
    List<RepItem> have = new LinkedList<>();
    List<RepItem> none = new LinkedList<>();

    String[] words = items.split("\r\n");
    Set<String> wordSet = new HashSet<>();
    Collections.addAll(wordSet,words);

    for(String wo : wordSet) {
      if(!wo.equals("")) {
        RepItem i = repItemRepo.findOneByRealName(wo);
        if (i != null)
          have.add(i);
        else
          none.add(new RepItem(wo));
      }
    }

    map.put("have",have);
    map.put("none",none);

    return map;

  }
}
