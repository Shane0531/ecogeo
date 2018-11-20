package com.ecogeo.service;

import com.ecogeo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageService {

  @Autowired
  PlantItemRepo plantItemRepo;

  @Autowired
  MamItemRepo mamItemRepo;

  @Autowired
  AmpItemRepo ampItemRepo;

  @Autowired
  BenItemRepo benItemRepo;

  @Autowired
  BirdItemRepo birdItemRepo;

  @Autowired FishItemRepo fishItemRepo;

  @Autowired InsectItemRepo insectItemRepo;

  @Autowired RepItemRepo repItemRepo;

  public Object getItem(String filter, String name) {
    Object object;
    if(filter.equals("관속식물"))
      object = plantItemRepo.findOneByScientificName(name);
    else if(filter.equals("저서동물"))
      object = benItemRepo.findOneByScientificName(name);
    else if(filter.equals("포유류"))
      object = mamItemRepo.findOneByScientificName(name);
    else if(filter.equals("조류"))
      object = birdItemRepo.findOneByScientificName(name);
    else if(filter.equals("양서강"))
      object = ampItemRepo.findOneByScientificName(name);
    else if(filter.equals("파충강"))
      object = repItemRepo.findOneByScientificName(name);
    else if(filter.equals("곤충"))
      object = insectItemRepo.findOneByScientificName(name);
    else
      object = fishItemRepo.findOneByScientificName(name);

    return object;

  }

}
