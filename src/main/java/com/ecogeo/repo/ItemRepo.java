package com.ecogeo.repo;

import com.ecogeo.model.Item;
import com.ecogeo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepo extends JpaRepository<Item, String> {

  Item findOneBySpeciesAndRealName(String species,String RealName);
}
