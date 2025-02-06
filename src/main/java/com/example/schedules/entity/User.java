package com.example.schedules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import jakarta.persistence.Table;  // 추가 필요
import jakarta.persistence.Id;

@Getter
@Entity
@Table(name = "users") // "user" 테이블 매핑
public class User extends BaseEntity{
  @Id
  // 기본키 자동 증가
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // 유저명(중복 불가,널 불가)
  @Column(nullable = false, unique = true)
  private String username;
  // 비밀번호(널 불가)
  @Column(nullable = false)
  private String password;
  // 이메일(필수x)
  private String email;
  //기본 생성자
  public User(){

  }
  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
  public void updatePassword(String password) {
    this.password = password;
  }
}