package com.example.schedules.dto;

import lombok.Getter;

//사용자가 일정을 수정할 때 제목과 내용 변경 가능
@Getter
public class UpdateScheduleRequestDto {
  private final String title;
  private final String contents;

  public UpdateScheduleRequestDto(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }
}
