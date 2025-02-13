package com.example.schedules.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

// 회원가입 요청 시 이름, 비밀번호, 이메일 포함
@Getter
public class SignUpRequestDto {
  // 사용자 이름(필수 입력)
  @NotNull
  private final String username;

  // 비밀번호(필수 입력)
  @NotNull
  private final String password;

  // 이메일 형식('net' 또는 'com' 도메인 포함)
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(net|com)$")
  private final String email;


  public SignUpRequestDto(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}