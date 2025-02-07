package com.example.schedules.service;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import com.example.schedules.entity.User;
import com.example.schedules.repository.ScheduleRepository;
import com.example.schedules.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
  private final UserRepository userRepository;
  private final ScheduleRepository scheduleRepository;

  public ScheduleResponseDto save(String title, String contents, String username) {
    User findUser = userRepository.findUserByUsernameOrElseThrow(username);
    Schedule schedule = new Schedule(title,contents);
    schedule.SetUser(findUser);
    scheduleRepository.save(schedule);
    return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(),schedule.getContents());

  }
}
