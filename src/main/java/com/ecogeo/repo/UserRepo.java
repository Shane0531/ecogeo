package com.ecogeo.repo;

import com.ecogeo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Integer> {

  @Query("SELECT m FROM User m WHERE m.id = :userid AND m.passwd = PASSWORD(SHA1(:passwd))")
  User selectLogin(@Param("userid") String userid, @Param("passwd") String passwd);

}
