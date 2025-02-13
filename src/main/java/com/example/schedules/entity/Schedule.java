package com.example.schedules.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedules") // schedules 테이블과 매핑
public class Schedule extends BaseEntity {
  // 기본키 자동 증가
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //  할 일 제목(널 불가)
  @Column(nullable = false)
  private String title;

  // 할 일 내용(긴 문자열 저장 가능)
  @Column(columnDefinition = "longtext")
  private String contents;

  // user_id 외래 키 설정
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Schedule(){

  }

  public Schedule(String title, String contents){
    this.title = title;
    this.contents = contents;
  }

  // Schedule 객체에 User 객체를 설정하는 메서드
  public void SetUser(User user) {
    this.user = user;
  }

  // 일정 제목 및 내용을 수정하는 메서드
  public void updateSchedule(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }
}