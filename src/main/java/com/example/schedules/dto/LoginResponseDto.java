package com.example.schedules.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

  private Long id;
  private String email;

  public LoginResponseDto(Long id, String email) {
    this.id = id;
    this.email = email;
  }

}
