# 이어봐 게시판
1. 프로젝트명 : Spring Framework를 적용한 연관 게시글이 있는 게시판 만들기
2. 프로젝트 웹 주소 : http://52.79.195.214:8080
3. 프로젝트 Notion 페이지 : https://t.ly/ZxQk
4. 개발기간 : 2023-05-11~
5. 개발 환경
  - 운영체제 : Windows 11
  - IDE : Eclipse
  - Language & Library : JAVA 11, JavaScript, AJAX, HTML, CSS, JSTL, JSP, selenium 4.9.1
  - Framework : Spring 3, MyBatis
  - DBMS : MySQL
6. 주요 기능
  - 새 게시글 작성하기
  - 새 게시글 작성시 단어별 데이터베이스 등록
  - 게시글 상세 보기 페이지에 진입하면 해당글의 연관 게시물 리스트를 실시간으로 생성하여 표시함
      - 연관도를 파악할 때 한국어 조사나 문장부호로 인한 오차를 줄이기 위한 로직 구현
      - 연관 게시글 목록은 연관도가 높은 게시글이 위에 표시되도록 함
  - 게시글 수정하기
  - 게시글 삭제하기
