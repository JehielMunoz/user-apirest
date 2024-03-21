package com.nttdata.users.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nttdata.users.dom.Phone;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface PhoneRepository extends JpaRepository<Phone, Long> {

  @Query("SELECT p FROM Phone p JOIN FETCH p.user where p.user.userId = :userId")
  List<Phone> findAllByUserId(@Param("userId") Long userId);

}
