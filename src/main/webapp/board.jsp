<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<html lang="en" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="assets/" data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>이어봐</title>

<meta name="description" content="" />

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

<!-- Helpers -->
<script src="assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="assets/js/config.js"></script>
</head>

<body>
	<!-- Content wrapper -->
	<div class="content-wrapper">
	
		<div class="container-xxl flex-grow-1 container-p-y">
			<div style="display:flex; justify-content:space-between; align-items:center;">
				<h4 class="fw-bold py-3 mb-4"> <a style="color:#00997b;" href="boardView.do?selectPage=1"">이어봐</a></h4>
			</div>
	      	
			<!-- 전체 게시글 목록 -->
			<div class="card">
				<div class="table-responsive text-nowrap">
					<table class="table table-hover" style="table-layout:fixed; overflow:hidden; white-space:nowrap;">
						<thead>
							<tr>
								<th width="15%" style="text-align:center;">글번호</th>
								<th width="15%" style="text-align:center;">작성일</th>
								<th width="15%" style="text-align:center;">작성자</th>
								<th width="40%" style="text-align:center;">제목</th>
								<th width="15%" style="text-align:center;">댓글</th>
							</tr>
						</thead>
						<!-- 목록 출력부분 -->
						<tbody class="table-border-bottom-0" id="dataTableBody">
						</tbody>
					</table>
		      		<!-- 페이지네이션 부분 -->
					<div class="product__pagination">
						<ul id="pagingul" 
							style="align-items: center; justify-content: center; margin:5px 0px 15px 0px; padding:0px 0px 0px 18px;">
						</ul>
					</div>
					<!-- 버튼 -->
					<div class=" mb-4" align="right" style="margin:0px 10px 10px 10px;">
						<input type="button" style="width:100%; max-width:100%;" onclick="location.href='insertBoardView.do?selectPage='+${board.selectPage}" class="button-purple" value="글쓰기">
					</div>
				</div>
			</div>
	
		</div>
	   <!-- / Content -->

       <!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>
   <!-- / Layout wrapper -->


   <!-- Core JS -->
   <!-- build:js assets/vendor/js/core.js -->
   <script src="assets/vendor/libs/jquery/jquery.js"></script>
   <script src="assets/vendor/libs/popper/popper.js"></script>
   <script src="assets/vendor/js/bootstrap.js"></script>
   <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

   <script src="assets/vendor/js/menu.js"></script>
   <!-- endbuild -->

   <!-- Vendors JS -->

   <!-- Main JS -->
   <script src="assets/js/main.js"></script>

   <!-- Page JS -->
   <script src="assets/js/board.js"></script>

   <!-- Place this tag in your head or just before your close body tag. -->
   <script async defer src="https://buttons.github.io/buttons.js"></script>
   
	<!-- 리스트세팅 -->
    <script>
		$(document).ready(function() {
			var selectPage = "${board.selectPage}";
			list(selectPage);
		});
    </script>
    
</body>
</html>