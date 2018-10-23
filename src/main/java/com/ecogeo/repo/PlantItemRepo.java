package com.ecogeo.repo;

import com.ecogeo.model.PlantItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantItemRepo extends JpaRepository<PlantItem, String> {

  PlantItem findOneByRealName(String RealName);
}