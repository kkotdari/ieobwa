package com.kotddari.ieobwa.biz.board;

public class BoardVO {
	// 멤버변수(DB 테이블에 존재)
	private int boardNum; // 글 번호
	private String boardDate; // 글 작성일
	private String boardWriter; // 작성자
	private String boardPassword; // 암호
	private String boardTitle; // 글 제목
	private String boardContent; // 글 내용
	// 멤버변수(VO에만 존재)
	private int selectPage; // 페이지 번호
	private int replyCount; // 댓글 수
	private int viewCount; //조회수
	private String boardSearchCondition; // 글 조회 조건
	// getter, setter
	public int getBoardNum() {
		return boardNum;
	}


	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardPassword() {
		return boardPassword;
	}

	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getBoardSearchCondition() {
		return boardSearchCondition;
	}

	public void setBoardSearchCondition(String boardSearchCondition) {
		this.boardSearchCondition = boardSearchCondition;
	}
	// toString 오버라이드
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardDate=" + boardDate + ", boardWriter=" + boardWriter
				+ ", boardPassword=" + boardPassword + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", selectPage=" + selectPage + ", replyCount=" + replyCount + ", viewCount=" + viewCount
				+ ", boardSearchCondition=" + boardSearchCondition + "]";
	}
}