package com.example.schedules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받는 엔티티에서 사용하게 함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
  @CreatedDate // 엔티티가 생성될 때 자동으로 날짜 설정
  @Column(updatable = false) //업데이트 불가능(초기 생성 시만)
  private LocalDateTime createdAt;

  @LastModifiedDate //엔터티가 수정될 때 자동으로 날짜 갱신
  private LocalDateTime modifiedAt;

}