package com.ecogeo.repo;

import com.ecogeo.model.MamItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MamItemRepo extends JpaRepository<MamItem, String> {

  MamItem findOneByRealName(String RealName);
}
