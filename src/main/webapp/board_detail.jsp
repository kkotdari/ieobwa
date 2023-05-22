<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

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
		<!-- HTML5 Inputs -->
		<div class="card mb-4">
			<div class="card-body">
				<!-- 제목 -->
				<table>
					<b><font color="#28282D">${board.boardTitle}</font></b>
				</table>
				<!-- 글번호 -->
				<hr style="padding:0; margin:10px 0px;">
				<font color="#28282D">글번호 : </font>
				<table>
					<font color="#28282D">${board.boardNum}</font>
				</table>
				<!-- 작성일 -->
				<font color="#28282D">작성일 : </font>
				<table>
					<font color="#28282D">${board.boardDate}</font>
				</table>
				<!-- 작성자 -->
				<font color="#28282D">작성자 : </font>
				<table>
					<font color="#28282D">${board.boardWriter}</font>
				</table>
				<!-- 내용 -->
				<hr style="padding:0; margin:10px 0px;">
				<table>
					<tr><font style="white-space:pre-line; color:#28282D">${board.boardContent}</font></tr>
				</table>
			</div>
			<!-- 댓글 부분 -->
			<div class="card-body">
				<div class="showReply">
					<div id="showReply">댓글 [${board.replyCount}]</div>
					<div id="reply">
						<!-- 댓글 출력 부분 -->
					</div>
				</div>
				<hr style="margin:10px 0px 0px 0px;">
				<div style='vertical-align:middle; text-align:right; width:100%; padding:5px; border-radius: 5px; border: 1.7px none #dfdfed;'>
					<a href="javascript:doDisplay1()"><font style="color:#00997b;" size=2>▼ 댓글 쓰기</font></a>
				</div>
				<div id="replyInput" style="display:none; flex-wrap:wrap;">
					<div class="col-md-3 col-sm-3 col-xs-12" style="flex:auto; padding-left:0; padding-right:0; margin-top:10px">
						<input id="replyWriter" type="text" name="replyWriter" style="padding:10px; height:35px; width:100%; border-radius:5px; border:1px solid #7F7F7F;" placeholder="작성자 입력" required />
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12" style="flex:auto; padding-left:0; padding-right:0; margin-top:10px">
						<input id="replyContent" type="text" name="replyContent" style="padding:10px; height:35px; width:100%; border-radius:5px; border:1px solid #7F7F7F;" placeholder="댓글 내용 입력" required />
					</div>
					<div class="col-md-3 col-sm-3 col-xs-12" style="flex:auto; padding-left:0; padding-right:0; margin-top:10px" >
						<input type="button" style="max-width:100%; padding:0; height:35px; width:100%; border-radius:5px; border:1px solid #28282D;" onclick="insertReply()" class="button-purple" value="확인" />
					</div>
				</div>
				<!-- 버튼 부분 -->
				<hr>
				<div class=" mb-4" style="display:flex; flex-wrap:wrap; justify-content: space-between;">
					<input type="button" onclick="location.href='updateBoardView.do?boardNum='+${board.boardNum}+'&selectPage='+${board.selectPage}" class="button-purple" value="수정하기">
					<input type="button" onclick="location.href='deleteBoardView.do?boardNum='+${board.boardNum}+'&selectPage='+${board.selectPage}" class="button-purple" value="삭제하기">
					<input type="button" onclick="location.href='boardView.do?selectPage='+${board.selectPage}" class="button-purple" value="목록으로">
				</div>
			</div>
		</div>
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
        $.ajax({
			type : 'POST',
			url : 'selectAllReply.do',
			data : {
				boardNum: '${board.boardNum}',
			},
			success : function(data) {
				var replyHtml = "";
				for (var i = 0; i < data.length; i++) {
					if(data[i].replyStep == 1){
						replyHtml += "<hr style='margin:10px 0px;'>"
							+ "<div style='margin:0; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font><a href='javascript:doDisplay2("+data[i].replyNum+")'><font style='color:#00997b;' size=1.5>&nbsp;&nbsp;▼ 대댓글 쓰기</font></a></div>"
							+ "<div id='reReply"+data[i].replyNum+"' style='display:none; flex-wrap:wrap; margin-bottom:10px;'>"
								+ "<div class='col-md-6 col-sm-6 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyWriter"+data[i].replyNum+"' type='text' name='reReplyWirter' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='작성자 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyContent"+data[i].replyNum+"' type='text' name='reReplyContent' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='대댓글 내용 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input type='button' style='padding:0; height:35px; width:100%; max-width:100%; border-radius:5px; border:1px solid #28282D;' onclick='insertReReply("+data[i].replyNum+")' class='button-purple' value='확인'>"
								+ "</div>"
							+ "</div>";
					}
					else if(data[i].replyStep == 2){
						replyHtml += "<div style='margin:0px 0px 0px 20px; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font>"
						+ "</div>"
					}
				}
				$("#reply").html(replyHtml);
			},
			error : function() {
				alert('error');
			}
		})
      });
    
	<!-- 댓글 입력 -->
	function insertReply(replyNum){
		var replyContent = $("#replyContent").val();
		document.getElementById('replyContent').value = null;
		var replyWriter = $("#replyWriter").val();
		document.getElementById('replyWriter').value = null;
		$.ajax({
			type : 'POST',
			url : 'insertReply.do',
			data : {
				boardNum: '${board.boardNum}',
				replyStep: '1',
				replyWriter : replyWriter,
				replyPassword : '0000',
				replyContent : replyContent,
			},
			success : function(data) {
				var replyHtml = "";
				for (var i = 0; i < data.length; i++) {
					if(data[i].replyStep == 1){
						replyHtml += "<hr style='margin:10px;'>"
							+ "<div style='margin:0; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font><a href='javascript:doDisplay2("+data[i].replyNum+")'><font style='color:#00997b;' size=1.5>&nbsp;&nbsp;▼ 대댓글 쓰기</font></a></div>"
							+ "<div id='reReply"+data[i].replyNum+"' style='display:none; flex-wrap:wrap; margin-bottom:10px;'>"
								+ "<div class='col-md-6 col-sm-6 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyWriter"+data[i].replyNum+"' type='text' name='reReplyWirter' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='작성자 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyContent"+data[i].replyNum+"' type='text' name='reReplyContent' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='대댓글 내용 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input type='button' style='padding:0; height:35px; width:100%; max-width:100%; border-radius:5px; border:1px solid #28282D;' onclick='insertReReply("+data[i].replyNum+")' class='button-purple' value='확인'>"
								+ "</div>"
							+ "</div>";
					}
					else if(data[i].replyStep == 2){
						replyHtml += "<div style='margin:0px 0px 0px 20px; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font>"
						+ "</div>"
					}
				}
				$("#reply").html(replyHtml);
			},
			error : function() {
				alert('error');
			}
		})
	}
	
	
	<!-- 대댓글 입력 -->
	function insertReReply(replyNum){
		var reReplyContent = $("#reReplyContent" + replyNum).val();
		document.getElementById('reReplyContent' + replyNum).value = null;
		var reReplyWriter = $("#reReplyWriter" + replyNum).val();
		document.getElementById('reReplyWriter' + replyNum).value = null;
		$.ajax({
			type : 'POST',
			url : 'insertReReply.do',
			data : {
				boardNum: '${board.boardNum}',
				replyStep: '2',
				parentNum: replyNum,
				replyWriter : reReplyWriter,
				replyPassword : '0000',
				replyContent : reReplyContent,
			},
			success : function(data) {
				var replyHtml = "";
				for (var i = 0; i < data.length; i++) {
					if(data[i].replyStep == 1){
						replyHtml += "<hr style='margin:10px;'>"
							+ "<div style='margin:0; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font><a href='javascript:doDisplay2("+data[i].replyNum+")'><font style='color:#00997b;' size=1.5>&nbsp;&nbsp;▼ 대댓글 쓰기</font></a></div>"
							+ "<div id='reReply"+data[i].replyNum+"' style='display:none; flex-wrap:wrap; margin-bottom:10px;'>"
								+ "<div class='col-md-6 col-sm-6 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyWriter"+data[i].replyNum+"' type='text' name='reReplyWirter' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='작성자 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input id='reReplyContent"+data[i].replyNum+"' type='text' name='reReplyContent' style='width:100%; height:35px; padding:10px; width:100%; border-radius:5px; border:1px solid #7F7F7F;' placeholder='대댓글 내용 입력' required />"
								+ "</div>"
								+ "<div class='col-md-3 col-sm-3 col-xs-12' style='flex:auto; padding-left:0; padding-right:0; margin-top:10px'>"
									+ "<input type='button' style='padding:0; height:35px; width:100%; max-width:100%; border-radius:5px; border:1px solid #28282D;' onclick='insertReReply("+data[i].replyNum+")' class='button-purple' value='확인'>"
								+ "</div>"
							+ "</div>";
					}
					else if(data[i].replyStep == 2){
						replyHtml += "<div style='margin:0px 0px 0px 20px; padding:0'>"+data[i].replyWriter+" : "+data[i].replyContent+"<font size=1.5>&nbsp;&nbsp;("+data[i].replyDate+")</font>"
						+ "</div>"
					}
				}
				$("#reply").html(replyHtml);
			},
			error : function() {
				alert('error');
			}
		})
	}
	
	<!-- 댓글 입력창 접고 펴기 -->
	function doDisplay1(){ 	
	    var con = document.getElementById("replyInput"); 	
	    if(con.style.display == 'none'){ 		
	    	con.style.display = 'flex';
	    }else{ 		
	    	con.style.display = 'none'; 	
	    } 
	}
	
	<!-- 대댓글 입력창 접고 펴기 -->
	function doDisplay2(replyNum){ 	
	    var con = document.getElementById("reReply" + replyNum); 	
	    if(con.style.display == 'none'){ 		
	    	con.style.display = 'flex';
	    }else{ 		
	    	con.style.display = 'none'; 	
	    } 
	}
    </script>
    
</body>
</html>
