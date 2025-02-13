package com.example.schedules.config;

import com.example.schedules.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
  // 로그인 필터를 등록하는 메서드
  @Bean
  public FilterRegistrationBean loginFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LoginFilter()); // Filter 등록
    filterRegistrationBean.setOrder(1); // Filter 순서 1 설정
    filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

    return filterRegistrationBean;
  }
}
