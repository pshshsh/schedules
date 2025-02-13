package com.example.schedules.dto;

import lombok.Getter;
// 로그인 시 이메일과 비밀번호가 필요
@Getter
public class LoginRequestDto {
  private String email;
  private String password;

  public LoginRequestDto(String email, String password){
    this.email = email;
    this.password = password;
  }
}