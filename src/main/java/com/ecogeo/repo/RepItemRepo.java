package com.ecogeo.repo;

import com.ecogeo.model.RepItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepItemRepo extends JpaRepository<RepItem, String> {

  RepItem findOneByRealName(String RealName);

  RepItem findOneByScientificName(String ScientificName);
}