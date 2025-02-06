package com.example.schedules.service;

import com.example.schedules.dto.SignUpResponseDto;
import com.example.schedules.dto.UserResponseDto;
import com.example.schedules.entity.User;
import com.example.schedules.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  // 유저 생성
  public SignUpResponseDto signUp(String username, String password, String email) {
    User user = new User(username, password, email);
    User savedUser = userRepository.save(user);
    return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());

  }

  // 유저 조회
  public UserResponseDto findByID(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    // NPE 방지
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }
    User findUser = optionalUser.get();
    return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
  }

  // 유저 전체 조회
  public List<UserResponseDto> findAll() {
    return userRepository.findAll().stream()
        .map(UserResponseDto::toDto)
        .collect(Collectors.toList());

  }
  // 유저 비밀 번호 수정
  @Transactional
  public void updatePassword(Long id, String oldPassword, String newPassword) {
    User findUser = userRepository.findByIdOrElseThrow(id);

    if (!findUser.getPassword().equals(oldPassword)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }

    findUser.updatePassword(newPassword);
  }

  // 유저 삭제
  public void delete(Long id) {
    User findUser = userRepository.findByIdOrElseThrow(id);
    userRepository.delete(findUser);
  }

}