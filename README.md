# 프로젝트 개요  
사용자가 자신의 일정을 관리할 수 있도록 지원하는 앱입니다.
사용자는 일정 추가, 조회, 수정, 삭제 기능을 활용하여 효율적으로 일정 관리를 할 수 있습니다.  

# 기술 스택  
Back-end : Spring Boot, Spring Data JPA  
Database : MySQL


# ERD
![Schedules_ERD](https://github.com/user-attachments/assets/ed7c8c8b-2bc7-4f3c-aa70-3dff0928fd34)


#  Schedule API 명세서

| 기능         | Method  | URL                   | 요청 형식                                                    | 응답 형식                                                                                                      | 상태코드                           |
|------------|---------|----------------------|------------------------------------------------|------------------------------------------------------------------------------------------------|---------------------------------|
| 일정 생성   | POST    | /schedules          | `{ "title": "제목", "contents": "내용", "username": "이름" }` | `{ "id": 1, "title": "제목", "contents": "내용", "username": "이름", "created_at": "2025-02-12T10:00:00" }` | `201 Created / 400 Bad Request` |
| 일정 조회   | GET     | /schedules          | -                                              | `{ "id": 1, "title": "제목", "contents": "내용", "modified_at": "2025-02-12T12:00:00" }`                      | `200 OK / 400 Bad Request`       |
| 회원가입   | POST    | /users/signup       | `{ "username": "이름", "password": "비밀번호", "email": "이메일" }` | `{ "id": 1, "username": "이름", "email": "이메일", "created_at": "2025-02-12T10:00:00" }`                      | `201 Created / 400 Bad Request` |
| 유저 전체 조회 | GET     | /users               | -                                              | `{ "users": [ { "id": 1, "username": "이름", "email": "이메일" }, { "id": 2, "username": "이름", "email": "이메일" } ] }` | `200 OK`                         |
| 유저 조회   | GET     | /users/{id}         | -                                              | `{ "id": 1, "username": "이름", "email": "이메일" }`                                                          | `200 OK / 404 Not Found`         |
| 비밀번호 변경 | PATCH   | /users/{id}/password | `{ "old_password": "기존 비밀번호", "new_password": "새 비밀번호" }` | -                                                                                                              | `200 OK / 400 Bad Request`       |
| 유저 삭제   | DELETE  | /users/{id}         | -                                              | -                                                                                                              | `204 No Content / 404 Not Found` |
| 로그인     | POST    | /users/login        | `{ "email": "이메일", "password": "비밀번호" }` | `{ "user": { "id": 1, "username": "이름", "email": "이메일" } }`                                               | `200 OK / 401 Unauthorized`     |
| 로그아웃   | POST    | /users/logout       | -                                              | -                                                                                                              | `200 OK`                         |
