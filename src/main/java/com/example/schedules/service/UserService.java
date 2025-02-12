package com.example.schedules.service;

import com.example.schedules.config.PasswordEncoder;
import com.example.schedules.dto.LoginResponseDto;
import com.example.schedules.dto.SignUpResponseDto;
import com.example.schedules.dto.UserResponseDto;
import com.example.schedules.entity.User;
import com.example.schedules.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // 유저 생성
  public SignUpResponseDto signUp(String username, String password, String email) {
    // 비밀번호 암호화
    String encodePassword = passwordEncoder.encode(password);
    User user = new User(username, encodePassword, email);
    User savedUser = userRepository.save(user);
    return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());

  }

  // 유저 조회
  public UserResponseDto findById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    // NPE 방지
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }
    User findUser = optionalUser.get();
    return new UserResponseDto(
        findUser.getUsername(),
        findUser.getEmail(),
        findUser.getCreatedAt(),
        findUser.getModifiedAt()
    );
  }

  // 유저 전체 조회
  public List<UserResponseDto> findAll() {
    List<User> users = userRepository.findAll();
    List<UserResponseDto> responseDtos = new ArrayList<>();
    for (User user : users) {
      responseDtos.add(UserResponseDto.toDto(user));
    }
    return responseDtos;
  }

  // 유저 비밀 번호 수정
  @Transactional
  public void updatePassword(Long id, String oldPassword, String newPassword) {
    User findUser = userRepository.findByIdOrElseThrow(id);

    if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }

    findUser.updatePassword(passwordEncoder.encode(newPassword));
  }

  // 유저 삭제
  public void delete(Long id) {
    User findUser = userRepository.findByIdOrElseThrow(id);
    userRepository.delete(findUser);
  }

  @Transactional
  // 로그인
  public LoginResponseDto login(String email, String password) {
    // 이메일로 사용자 정보 조회
    User findUser = userRepository.findByEmailOrElseThrow(email);
  // 데이터베이스 조회한 사용자 비밀번호와 사용자가 입력한 비밀번호 비교
    if (passwordEncoder.matches(password, findUser.getPassword())) {
      return new LoginResponseDto(findUser.getId(), findUser.getEmail());
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
  }
    }
