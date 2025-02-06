package com.example.schedules.entity;


import jakarta.persistence.*;

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


}