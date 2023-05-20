package com.kim.iobwa.biz.reply;

public class ReplyVO {
	// 멤버변수(DB 테이블에 존재)
	private int replyNum; // 댓글 번호
	private int boardNum; // 글 번호
	private int parentNum; // 대댓글의 경우 부모 댓글 번호
	private int replyStep; // 댓글 단계
	private String replyDate; // 댓글 작성일
	private String replyContent; // 댓글 내용
	// 멤버변수(VO에만 존재)
	// getter, setter
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getParentNum() {
		return parentNum;
	}
	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}
	public int getReplyStep() {
		return replyStep;
	}
	public void setReplyStep(int replyStep) {
		this.replyStep = replyStep;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	// toString 오버라이드
	@Override
	public String toString() {
		return "ReplyVO [replyNum=" + replyNum + ", boardNum=" + boardNum + ", parentNum=" + parentNum + ", replyStep="
				+ replyStep + ", replyDate=" + replyDate + ", replyContent=" + replyContent + "]";
	}
}