# KDH_Board
1. 프로젝트명 : 한국글로벌널리지네트워크 입사 과제
2. 개발자 : 김대현 지원자
3. 개발기간 : 2023-05-11 ~ 2023-05-14
4. 개발 환경
  - 운영체제 : Windows 11
  - IDE : Eclipse
  - Language : JAVA 11
  - Framework : Spring 3, MyBatis
  - DBMS : MySQL 
5. 주요 기능
  - 게시판 최초 접속시 샘플데이터 생성을 위해 IT 뉴스 기사 35건에 대한 클롤링을 수행함
    - 크롤링 해제 : com.kim.board.controller.BoardController.java 클래스의 47-55행을 주석처리
    - 크롤링하는 기사의 수량 조절 : com.kim.board.biz.common.Crawling.java 클래스의 20행 상수 BOARD의 값을 변경 (범위 5~100)
  - 새 글 쓰기 기능
  - 새 글 작성시 단어별 데이터베이스 등록 및 연관 게시물 데이터 생성 기능
    - 연관도를 파악할 때 한국어 조사나 문장부호로 인한 오차를 줄이기 위한 로직 구현
  - 글 상세보기 및 상세보기하는 글의 연관 게시물 목록 표시 기능
  - 비즈니스 메서드에 대한 Log Advise 기능
 
 감사합니다.
