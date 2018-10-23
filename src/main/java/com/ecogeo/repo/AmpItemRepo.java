package com.ecogeo.repo;

import com.ecogeo.model.AmpItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmpItemRepo extends JpaRepository<AmpItem, String> {

  AmpItem findOneByRealName(String RealName);
}