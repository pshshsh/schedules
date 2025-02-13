package com.example.schedules.repository;

import com.example.schedules.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
  }

  // username을 통해 유저 찾기
  Optional<User> findUserByUsername(String username);

  default User findUserByUsernameOrElseThrow(String username) {
    return findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
  }

  // email을 통해 유저 찾기
  Optional<User> findByEmail(String email);

  default User findByEmailOrElseThrow(String email) {
    return findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email = " + email));
  }
}