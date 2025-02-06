package com.example.schedules.dto;
import com.example.schedules.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
  private String username;
  private String email;

  public UserResponseDto( String username, String email) {

    this.username = username;
    this.email = email;
  }

  // 엔티티 → DTO 변환 메서드
  public static UserResponseDto toDto(User user) {
    return new UserResponseDto( user.getUsername(), user.getEmail());
  }
}