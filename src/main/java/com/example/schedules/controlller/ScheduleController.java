package com.example.schedules.controlller;

import com.example.schedules.dto.CreateScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
  private final ScheduleService scheduleService;
  // 일정 생성
  @PostMapping
  public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto){
    ScheduleResponseDto schedueleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());
  return new ResponseEntity<>(schedueleResponseDto, HttpStatus.CREATED);
  }

}
