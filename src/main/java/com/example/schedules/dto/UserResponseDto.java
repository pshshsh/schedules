package com.example.schedules.dto;

import com.example.schedules.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

//사용자 정보 조회 시 유저이름, 이메일, 생성/수정 시간 반환
@Getter
public class UserResponseDto {
  private String username;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public UserResponseDto(String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.username = username;
    this.email = email;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }

  // 엔티티 → DTO 변환 메서드
  public static UserResponseDto toDto(User user) {
    return new UserResponseDto(
        user.getUsername(),
        user.getEmail(),
        user.getCreatedAt(),
        user.getModifiedAt()
    );
  }
}