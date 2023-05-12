package com.kim.board.biz.word;

public class WordVO {
	// 멤버변수(DB 테이블에 존재)
	private int wordNum; // 등록 단어 번호
	private String wordWord; // 단어
	private int wordFound; // 전체 게시글에서 해당 단어가 발견된 게시글의 수
	private int wordRatio; // 전체 게시글에서 해당 단어가 발견된 게시글의 비율
	// 멤버변수(VO에만 존재)
	// getter, setter
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
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
	// toString 오버라이드
	@Override
	public String toString() {
		return "WordVO [wordNum=" + wordNum + ", wordWord=" + wordWord + ", wordFound=" + wordFound + ", wordRatio="
				+ wordRatio + "]";
	}
}