package com.example.schedules.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
// 사용자가 입력한 비밀번호를 암호화 하는 메서드
public class PasswordEncoder {
  public String encode(String rawPassword) {
    return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
  }
// 입력된 비밀번호가 저장된 암호화된 비밀번호와 일치하는지 검증
  public boolean matches(String rawPassword, String encodedPassword) {
    BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
    return result.verified;
  }
}
