package com.example.schedules.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
  // 유저 이름과 비밀번호는 필수입력
  @NotNull
  private final String username;

  @NotNull
  private final String password;
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(net|com)$")
  private final String email;


  public SignUpRequestDto(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}