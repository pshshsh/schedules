package com.example.schedules.service;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import com.example.schedules.entity.User;
import com.example.schedules.repository.ScheduleRepository;
import com.example.schedules.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
  private final UserRepository userRepository;
  private final ScheduleRepository scheduleRepository;

  public ScheduleResponseDto save(String title, String contents, String username) {
    User findUser = userRepository.findUserByUsernameOrElseThrow(username);
    Schedule schedule = new Schedule(title, contents);
    schedule.SetUser(findUser);
    scheduleRepository.save(schedule);
    return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());

  }

  public List<ScheduleResponseDto> findAll() {
    // 모든 Schedule 객체를 가져와서 schedules 리스트에 저장
    List<Schedule> schedules = scheduleRepository.findAll();
    // ScheduleResponseDto 객체들을 저장할 준비
    List<ScheduleResponseDto> responseDtos = new ArrayList<>();
    // schedules에 있는 모든 Schedules 객체를 schedule 변수에 대입하면서 반복문 실행
    for (Schedule schedule : schedules) {
      responseDtos.add(ScheduleResponseDto.toDto(schedule));
    }
    return responseDtos;
  }
}
