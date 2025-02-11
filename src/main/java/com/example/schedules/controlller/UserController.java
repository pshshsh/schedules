package com.example.schedules.controlller;

import com.example.schedules.config.Const;
import com.example.schedules.dto.*;
import com.example.schedules.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  // 유저 생성
  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDto> signup(@Valid @RequestBody SignUpRequestDto requestDto, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      // 유효성 검사 오류가 있으면, 오류 메시지를 반환
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
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
    UserResponseDto userResponsDto = userService.findById(id);
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

  // 로그인
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
    // 사용자 정보 확인
    LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());
    Long userId = responseDto.getId();
    // 실패시 예외처리
    if (userId == null) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401에러
    }
    // 로그인 성공시 , request에 존재하면  기존 session 반환, 없을경우 새로 session 생성
    HttpSession session = request.getSession(true);
    UserResponseDto loginUser = userService.findById(userId);
    // 세션에 로그인한 사용자 정보 저장
    session.setAttribute(Const.LOGIN_USER, loginUser);
    log.info("로그인 성공 - 세션 저장: " + loginUser.getUsername());

    return new ResponseEntity<>(responseDto, HttpStatus.OK);

  }
  //로그아웃
  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    // 로그인하지 않으면 HttpSession이 null로 반환된다.
    HttpSession session = request.getSession(false);
    // 세션이 존재하면 -> 로그인이 된 경우
    if (session != null) {
      session.invalidate();  // 해당 세션 삭제
      log.info("로그아웃 성공 - 세션 삭제 완료");
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
  }
