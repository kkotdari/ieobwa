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

<title>게시글 상세 보기</title>

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
		<h4 class="fw-bold py-3 mb-4">게시글 상세 보기</h4>
		<div class="row">
			<!-- HTML5 Inputs -->
			<div class="card mb-4">
				<div class="card-body">
					<!-- 글번호 -->
					<div class="mb-3 row">
						<label for="html5-text-input" class="col-md-1 col-form-label">글번호</label>
						<div class="col-md-11">
							${board.boardNum}
						</div>
					</div>
					<!-- 작성일 -->
					<div class="mb-3 row">
						<label for="html5-text-input" class="col-md-1 col-form-label">작성일</label>
						<div class="col-md-11">
							${board.boardDate}
						</div>
					</div>
					<!-- 제목 -->
					<div class="mb-3 row">
						<label for="html5-text-input" class="col-md-1 col-form-label">제목</label>
						<div class="col-md-11">
							${board.boardTitle}
						</div>
					</div>
					<!-- 내용 -->
					<div class="mb-3 row">
						<label for="html5-number-input" class="col-md-1 col-form-label">내용</label>
						<div class="col-md-11">
                            ${board.boardContent}
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 버튼 -->
	<div class=" mb-4" align="center">
		<input type="button" onclick="location.href='updateBoardView.do?boardNum=' + ${board.boardNum}" class="button-purple" value="수정하기">
		<input type="button" onclick="location.href='deleteBoard.do?boardNum=' + ${board.boardNum}" class="button-purple" value="삭제하기">
		<input type="button" onclick="location.href='boardView.do'" class="button-purple" value="목록으로">
	</div>
	
	<div class="container-xxl flex-grow-1 container-p-y">	
		
		<!-- 연관 게시물 목록 -->
		<div class="card">
		   <h5 class="card-header">연관 게시글 목록</h5>
		   <div class="table-responsive text-nowrap">
			      <table class="table table-hover" style="table-layout:fixed; overflow:hidden; white-space:nowrap;">
			         <thead>
			            <tr>
			               <th width="15%" style="text-align:center;">글번호</th>
			               <th width="25%" style="text-align:center;">작성일</th>
			               <th width="40%" style="text-align:center;">제목</th>
			            </tr>
			         </thead>
		         <!-- 목록 출력부분 -->
		         <tbody class="table-border-bottom-0" id="dataTableBody">
		         </tbody>
		      </table>
		      <!-- 페이지네이션 부분 -->
		      <div class="product__pagination"><ul id="pagingul"></ul>
			</div>
		</div>
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

	<!-- Page JS -->
	<script src="assets/js/related.js"></script>

	<!--  Place this tag in your head or just before your close body tag.  -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>

	<!-- 리스트세팅 -->
    <script>
      $(document).ready(function() {
        list(1, ${board.boardNum});
      });
    </script>
    
</body>
</html>