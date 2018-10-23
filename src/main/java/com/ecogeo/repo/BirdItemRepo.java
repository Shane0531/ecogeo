package com.ecogeo.repo;

import com.ecogeo.model.BirdItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdItemRepo extends JpaRepository<BirdItem, String> {

  BirdItem findOneByRealName(String RealName);
}