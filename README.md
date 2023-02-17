# Bulletin-Board

연습용 게시판 만들기 예제입니다.

---

### Tech specification

* Spring Boot
* Thymeleaf

---

### Functions

* 게시판
    * 글 등록, 열람, 수정, 삭제

* 회원
    * 회원 가입, 정보 수정

---

### TODO

1. Validation
    1. 회원
        * 아이디 중복 확인
        * 비밀번호 규칙
        * 닉네임 중복 확인

    2. 글
        * 로그인한 회원만 글 작성 가능
        * 글 작성자만 글 삭제 가능

####

2. DBMS 연결
    * 현행: local memory
    * 변경: H2 Database

####

3. 회원 권한 레벨
    1. Administrator
        * 모든 페이지 접근 및 작업 수행 가능
    2. User
        * 회원 목록 페이지 접근 불가

####

4. 댓글 기능
   * 작성자
   * 내용
   * 작성 시각
   * 댓글이 작성된 게시글 번호
   * 대댓글이 작성된 댓글 번호 (대댓글이 아니면 자신 번호)