<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
 -->
<!-- beautify ignore:start -->
<html lang="ko" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="assets/" data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>게시글 수정하기</title>

<meta name="description" content="" />

<!-- Favicon -->
<link rel="icon" type="image/x-icon" href="assets/img/favicon/favicon.ico" />

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet" />

<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

<!-- Core CSS -->
<link rel="stylesheet" href="assets/vendor/css/core.css" class="template-customizer-core-css" />
<link rel="stylesheet" href="assets/vendor/css/style.css" class="template-customizer-style-css" />
<link rel="stylesheet" href="assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />

<!-- Vendors CSS -->
<link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

<!-- Page CSS -->

<!-- Helpers -->
<script src="assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="assets/js/config.js"></script>
</head>

<body>
	<div class="container-xxl flex-grow-1 container-p-y">
		<h4 class="fw-bold py-3 mb-4">게시글 수정하기</h4>
	
		<form action="updateBoard.do?selectPage=${board.selectPage}" method="POST" onsubmit="return boardPasswordCheck();">
			<div class="row">
				<!-- HTML5 Inputs -->
				<div class="card mb-4">
					<div class="card-body">
						<!-- 글번호 -->
						<div class="mb-3 row">
							<font color="#28282D">글번호</font>
							<input class="form-control" type="text" name="boardNum" style="color:#28282D;" value="${board.boardNum}" readonly/>
						</div>
						<!-- 작성자 -->
						<div class="mb-3 row">
							<font color="#28282D">작성자</font>
							<input class="form-control" type="text" name="boardWriter" style="color:#28282D;" value="${board.boardWriter}" readonly/>
						</div>
						<!-- 암호 -->
						<div class="mb-3 row">
							<font color="#28282D">암호</font>
							<input class="form-control" type="password" id="boardPassword" style="color:#28282D;" required/>
						</div>
						<!-- 제목 -->
						<div class="mb-3 row">
							<font color="#28282D">제목</font>
							<input class="form-control" type="text" name="boardTitle" style="color:#28282D;" value="${board.boardTitle}" required/>
						</div>
						<!-- 내용 -->
						<div class="mb-3 row">
							<font color="#28282D">내용</font>
							<textarea class="form-control" aria-label="With textarea" style="color:#28282D; background-color:white; vertical-aligh:top; height:200px" id="boardContent" name="boardContent" required>${board.boardContent}</textarea>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 버튼 -->
			<div class=" mb-4" align="center">
				<input type="submit" class="button-purple" value="수정">
				<input type="button" onclick="location.href='boardDetailView.do?boardNum=' + ${board.boardNum} + '&selectPage=' + ${board.selectPage}" class="button-purple" value="취소">
			</div>

		</form>
	</div>

	<!— Core JS —>
	<!— build:js assets/vendor/js/core.js —>
	<script src="assets/vendor/libs/jquery/jquery.js"></script>
	<script src="assets/vendor/libs/popper/popper.js"></script>
	<script src="assets/vendor/js/bootstrap.js"></script>
	<script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

	<script src="assets/vendor/js/menu.js"></script>
	<!— endbuild —>

	<!— Vendors JS —>

	<!— Main JS —>
	<script src="assets/js/main.js"></script>

	<!— Page JS —>

	<!--  Place this tag in your head or just before your close body tag.  -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>
		<script>
		function boardPasswordCheck(){
			var boardPassword = $("#boardPassword").val();
			if(boardPassword == '${board.boardPassword}'){
				return true;
			}
			else{
				alert('비밀번호가 일치하지 않습니다.');
				return false;
			}
		}
	</script>
</body>
</html>
