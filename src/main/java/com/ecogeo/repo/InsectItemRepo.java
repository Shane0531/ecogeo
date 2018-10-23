package com.ecogeo.repo;

import com.ecogeo.model.InsectItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsectItemRepo extends JpaRepository<InsectItem, String> {

  InsectItem findOneByRealName(String RealName);
}
