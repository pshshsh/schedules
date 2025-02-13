package com.example.schedules.dto;

import lombok.Getter;
// 일정 생성시 제목, 내용, 유저 이름이 필요
@Getter
public class CreateScheduleRequestDto {
  private final String title;
  private final String contents;
  private final String username;

  public CreateScheduleRequestDto(String title, String contents, String username) {
    this.title = title;
    this.contents = contents;
    this.username = username;
  }
}
