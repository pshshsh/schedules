package com.example.schedules.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title; // 할 일 제목

  @Column(columnDefinition = "longtext")
  private String contents; // 할 일 내용

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Schedule(){

  }
  public Schedule(String title, String contents){
    this.title = title;
    this.contents = contents;
  }
  //Schedule 객체에 User 객체를 설정하는 메서드
  public void SetUser(User user) {
    this.user = user;
  }

  public void updateSchedule(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }
}