package com.kim.iobwa.biz.board;

public class BoardVO {
	// 멤버변수(DB 테이블에 존재)
	private int boardNum; // 글 번호
	private String boardDate; // 글 작성일
	private String boardTitle; // 글 제목
	private String boardContent; // 글 내용
	// 멤버변수(VO에만 존재)
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

	public String getBoardSearchCondition() {
		return boardSearchCondition;
	}

	public void setBoardSearchCondition(String boardSearchCondition) {
		this.boardSearchCondition = boardSearchCondition;
	}
	// toString 오버라이드
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardDate=" + boardDate + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardSearchCondition=" + boardSearchCondition + "]";
	}
}