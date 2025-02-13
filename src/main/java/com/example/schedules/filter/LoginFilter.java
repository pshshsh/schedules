package com.example.schedules.filter;

import com.example.schedules.config.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
@Slf4j
public class LoginFilter implements Filter {
  // 인증을 하지 않아도될 URL Path 배열
  private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login", "/users/logout"};
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    log.info("로그인 필터 로직 실행");
    // 화이트리스트에 포함되지 않은 경우 세션 체크
    if(!isWhiteList(requestURI)){
      HttpSession session = httpRequest.getSession(false);

      // 세션이 존재하는지 확인
      if (session == null) {
        log.error("세션이 존재하지 않음: " + requestURI);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
        return;
      }
      // 세션에서 로그인 정보 확인
      Object userSession = session.getAttribute(Const.LOGIN_USER);
      log.info("현재 세션 값: " + userSession);
      if (userSession == null) {
        log.error("세션에 로그인 정보 없음: " + requestURI);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
        return;
      }
      log.info("로그인된 사용자 요청: " + requestURI);
    }
    // 1번경우 : whiteListURL에 등록된 URL 요청이면 바로 chain.doFilter()
    // 2번경우 : 필터 로직 통과 후 다음 필터 호출 chain.doFilter()
    // 다음 필터 없으면 Servlet -> Controller 호출
    chain.doFilter(request, response);
  }
  // 로그인 여부를 확인하는 URL인지 체크하는 메서드
  private boolean isWhiteList(String requestURI) {
    // request URI가 whiteListURL에 포함되는지 확인
    // 포함되면 true 반환
    // 포함되지 않으면 false 반환
    return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
  }

}