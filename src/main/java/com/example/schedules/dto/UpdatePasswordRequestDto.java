package com.example.schedules.dto;

import lombok.Getter;

// 비밀번호 변경 시 기존 비밀번호와 새 비밀번호 모두 입력해야 함.
@Getter
public class UpdatePasswordRequestDto {

  private final String oldPassword;

  private final String newPassword;

  public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }
}