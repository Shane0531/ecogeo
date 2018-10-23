package com.ecogeo.repo;

import com.ecogeo.model.FishItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishItemRepo extends JpaRepository<FishItem, String> {

  FishItem findOneByRealName(String RealName);
}