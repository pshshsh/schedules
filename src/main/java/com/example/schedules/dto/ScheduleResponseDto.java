package com.example.schedules.dto;

import com.example.schedules.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
  private final Long id;
  private final String title;
  private final String contents;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public ScheduleResponseDto(Long id, String title, String contents,LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }

  public static ScheduleResponseDto toDto(Schedule schedule) {
    return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getModifiedAt() );
  }
}
