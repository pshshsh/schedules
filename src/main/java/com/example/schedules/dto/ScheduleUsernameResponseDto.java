package com.example.schedules.dto;

import lombok.Getter;

@Getter
public class ScheduleUsernameResponseDto {
  private final String title;
  private final String contents;
  private final String username;

  public ScheduleUsernameResponseDto(String title, String contents, String username) {
    this.title = title;
    this.contents = contents;
    this.username = username;
  }
}
