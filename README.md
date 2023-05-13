# KDH_Board
1. 프로젝트명 : 한국글로벌널리지네트워크 입사 과제
2. 개발자 : 김대현 지원자
3. 개발기간 : 2023-05-11 ~ 2023-05-14
4. 개발 환경
  - 운영체제 : Windows 11
  - IDE : Eclipse
  - Language : JAVA 11, JavaScript, AJAX, HTML, CSS, JSTL, JSP
  - Framework : Spring 3, MyBatis
  - Library : selenium 4.9.1
  - chromedriver : chrome 113버전용
  - DBMS : MySQL
5. 주요 기능
  - 과제 요구 사항
    - 새 게시글 작성하기
    - 새 게시글 작성시 단어별 데이터베이스 등록 및 연관 게시글 데이터 생성
      - 연관도를 파악할 때 한국어 조사나 문장부호로 인한 오차를 줄이기 위한 로직 구현
      - 새로 생성한 글뿐만 아니라 이전의 글에 대해서도 연관 게시글 데이터를 생성함
    - 게시글 상세보기 및 해당 게시글의 연관 게시글 목록 표시
  - 추가 구현 기능
    - 크롤링 : 게시판 최초 접속시 IT 뉴스 기사 10건을 크롤링하여 샘플데이터 생성
      - 크롤링 기능은 Chrome browser 113버전에서 개발되었습니다.
      - 크롤링 작동 설정 : com.kim.board.controller.BoardController.java 클래스의 43-55행을 주석을 해제하시면 됩니다.
      - 크롤링하는 기사의 수량 조절 : com.kim.board.biz.common.Crawling.java 클래스의 20행 상수 BOARD의 값을 변경하시면 됩니다. (범위 5~100)
    - 게시글 수정하기
    - 게시글 삭제하기
    - 비즈니스 메서드에 대한 Log Advise
 
  - 감사합니다.
