package com.example.schedules.controlller;

import com.example.schedules.dto.CreateScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  // 전체 일정 조회
  @GetMapping
  public ResponseEntity<List<ScheduleResponseDto>> findAll(){
    List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
    return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
  }

}
