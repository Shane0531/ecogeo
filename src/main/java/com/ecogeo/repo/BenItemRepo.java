package com.ecogeo.repo;

import com.ecogeo.model.BenItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenItemRepo extends JpaRepository<BenItem, String> {

  BenItem findOneByRealName(String RealName);
}
