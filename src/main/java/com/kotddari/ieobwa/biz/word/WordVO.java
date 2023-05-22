package com.kotddari.ieobwa.biz.word;

public class WordVO {
	// 멤버변수(DB 테이블에 존재)
	private int wordNum; // 등록 단어 번호
	private int boardNum; // 단어가 쓰인 게시글 번호
	private String wordWord; // 단어
	private int wordFound; // 해당 게시글에서 해당 단어가 쓰인 횟수
	private int wordRatio; // 해당 게시글에서 해당 단어가 차지하는 비율
	// 멤버변수(VO에만 존재)
	private String searchCondition;
	// getter, setter
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getWordWord() {
		return wordWord;
	}
	public void setWordWord(String wordWord) {
		this.wordWord = wordWord;
	}
	public int getWordFound() {
		return wordFound;
	}
	public void setWordFound(int wordFound) {
		this.wordFound = wordFound;
	}
	public int getWordRatio() {
		return wordRatio;
	}
	public void setWordRatio(int wordRatio) {
		this.wordRatio = wordRatio;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	// toString 오버라이드
	@Override
	public String toString() {
		return "WordVO [wordNum=" + wordNum + ", boardNum=" + boardNum + ", wordWord=" + wordWord + ", wordFound="
				+ wordFound + ", wordRatio=" + wordRatio + ", searchCondition=" + searchCondition + "]";
	}
}