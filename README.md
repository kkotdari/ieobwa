# KDH_Board
1. 프로젝트명 : Spring Framework를 적용한 연관 게시글이 있는 게시판 만들기(KDH_Board)
2. 프로젝트 Notion 페이지 : https://t.ly/ZxQk
3. 개발자 : 김대현 지원자
4. 개발기간 : 2023-05-11 ~ 2023-05-14 (이후 업데이트 중)
5. 개발 환경
  - 운영체제 : Windows 11
  - IDE : Eclipse
  - Language & Library : JAVA 11, JavaScript, AJAX, HTML, CSS, JSTL, JSP, selenium 4.9.1
  - Framework : Spring 3, MyBatis
  - chromedriver : chrome 113버전용
  - DBMS : MySQL
6. 주요 기능
  - 새 게시글 작성하기
  - 새 게시글 작성시 단어별 데이터베이스 등록
  - 게시글 상세 보기 페이지에 진입하면 해당글의 연관 게시물 리스트를 실시간으로 생성하여 표시함
      - 연관도를 파악할 때 한국어 조사나 문장부호로 인한 오차를 줄이기 위한 로직 구현
      - 연관 게시글 목록은 연관도가 높은 게시글이 위에 표시되도록 함
  - 게시글 수정하기
  - 게시글 삭제하기
  - 크롤링 : 게시판 최초 접속시 IT 뉴스 기사 10건을 크롤링하여 샘플데이터 생성
    - 크롤링 기능은 Chrome browser 113버전에서 개발되었습니다.
    - 크롤링 작동 설정 : com.kim.board.controller.BoardController.java 클래스의 37-51행의 주석을 해제하시면 됩니다.
    - 크롤링하는 기사의 수량 조절 : com.kim.board.biz.common.Crawling.java 클래스의 20행 상수 BOARD의 값을 변경하시면 됩니다. (범위 5~100)
 
- 감사합니다.
