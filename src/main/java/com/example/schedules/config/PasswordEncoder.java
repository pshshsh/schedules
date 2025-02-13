package com.example.schedules.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
// 비밀번호 암호화
public class PasswordEncoder {
  public String encode(String rawPassword) {
    return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
  }
// 비밀번호 검증
  public boolean matches(String rawPassword, String encodedPassword) {
    BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
    return result.verified;
  }
}
