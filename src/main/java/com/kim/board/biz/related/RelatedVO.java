package com.kim.board.biz.related;

public class RelatedVO {
	// 멤버변수(DB 테이블에 존재)
	private int relatedNum; // 연관 발생 번호
	private int originalBoardNum; // 원본 게시글 번호
	private int relatedBoardNum; // 연관 게시글 번호
	private int relatedRepetition; // 연관 횟수
	private int relatedImportance; // 연관 중요도
	// 멤버변수(VO에만 존재)
	// getter, setter
	public int getRelatedNum() {
		return relatedNum;
	}
	public void setRelatedNum(int relatedNum) {
		this.relatedNum = relatedNum;
	}
	public int getOriginalBoardNum() {
		return originalBoardNum;
	}
	public void setOriginalBoardNum(int originalBoardNum) {
		this.originalBoardNum = originalBoardNum;
	}
	public int getRelatedBoardNum() {
		return relatedBoardNum;
	}
	public void setRelatedBoardNum(int relatedBoardNum) {
		this.relatedBoardNum = relatedBoardNum;
	}
	public int getRelatedRepetition() {
		return relatedRepetition;
	}
	public void setRelatedRepetition(int relatedRepetition) {
		this.relatedRepetition = relatedRepetition;
	}
	public int getRelatedImportance() {
		return relatedImportance;
	}
	public void setRelatedImportance(int relatedImportance) {
		this.relatedImportance = relatedImportance;
	}
	// toString 오버라이드
	@Override
	public String toString() {
		return "RelatedVO [relatedNum=" + relatedNum + ", originalBoardNum=" + originalBoardNum + ", relatedBoardNum="
				+ relatedBoardNum + ", relatedRepetition=" + relatedRepetition + ", relatedImportance="
				+ relatedImportance + "]";
	}
}