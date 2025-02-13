package com.example.schedules.dto;

import lombok.Getter;

// 회원가입 성공 시 사용자 ID, 사용자 이름, 이메일 반환
@Getter
public class SignUpResponseDto {
  private final Long id;
  private final String username;
  private final String email;

  public SignUpResponseDto(Long id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }


}