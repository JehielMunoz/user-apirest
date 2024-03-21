package com.nttdata.users.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nttdata.users.dom.User;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByMail(String mail);

  User findUserByUserId(Long id);

  @Query("SELECT u FROM User u JOIN FETCH u.phones where u.mail = :userMail")
  User findUserByMail(@Param("userMail") String mail);

  @Query("SELECT u FROM User u JOIN FETCH u.phones WHERE u.isActive = true")
  List<User> getAllUsers();

  @Query("UPDATE User u SET u.isActive = false WHERE u.mail = :userMail")
  String deactivateUserByMail(@Param("userMail") String mail);
}
