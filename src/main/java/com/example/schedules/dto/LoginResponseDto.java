package com.example.schedules.dto;

import lombok.Getter;
// 사용자가 로그인에 성공했을 때, 사용자 ID와 이메일을 반환
@Getter
public class LoginResponseDto {

  private Long id;
  private String email;

  public LoginResponseDto(Long id, String email) {
    this.id = id;
    this.email = email;
  }

}
