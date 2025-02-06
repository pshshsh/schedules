package com.example.schedules.controlller;

import com.example.schedules.dto.SignUpRequestDto;
import com.example.schedules.dto.SignUpResponseDto;
import com.example.schedules.dto.UpdatePasswordRequestDto;
import com.example.schedules.dto.UserResponseDto;
import com.example.schedules.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  // 유저 생성
  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestDto){
    SignUpResponseDto signUpResponseDto =
        userService.signUp(
            requestDto.getUsername(),
            requestDto.getPassword(),
            requestDto.getEmail()
        );
    return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
  }

  // 유저 조회
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
    UserResponseDto userResponsDto = userService.findByID(id);
    return new ResponseEntity<>(userResponsDto,HttpStatus.OK);

  }
  // 유저 전체 조회
  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    List<UserResponseDto> users = userService.findAll();
    return ResponseEntity.ok(users);
  }
  // 유저 비밀번호 수정
  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(
      @PathVariable Long id,
      @RequestBody UpdatePasswordRequestDto requestDto) {

    userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
    return new ResponseEntity<>(HttpStatus.OK);
  }
  // 유저 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    userService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

}