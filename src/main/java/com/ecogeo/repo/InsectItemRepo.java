package com.ecogeo.repo;

import com.ecogeo.model.InsectItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsectItemRepo extends JpaRepository<InsectItem, String> {

  List<InsectItem> findOneByRealName(String RealName);

  InsectItem findOneByScientificName(String ScientificName);
}
