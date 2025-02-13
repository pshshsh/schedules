package com.example.schedules.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 특정 일정 조회시 응답되는 Dto(유저 이름 포함)
@Getter
public class ScheduleUsernameResponseDto {
  private final String title;
  private final String contents;
  private final String username;
  private final LocalDateTime createdAt;
  private final LocalDateTime modifiedAt;

  public ScheduleUsernameResponseDto(String title, String contents, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.title = title;
    this.contents = contents;
    this.username = username;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }

}
