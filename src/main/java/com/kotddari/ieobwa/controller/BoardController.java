package com.kotddari.ieobwa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kotddari.ieobwa.biz.board.BoardService;
import com.kotddari.ieobwa.biz.board.BoardVO;
import com.kotddari.ieobwa.biz.common.Crawling;
import com.kotddari.ieobwa.biz.reply.ReplyService;
import com.kotddari.ieobwa.biz.reply.ReplyVO;
import com.kotddari.ieobwa.biz.word.WordService;
import com.kotddari.ieobwa.biz.word.WordVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private WordService wordService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private Crawling crawling;

	// 게시판 목록 페이지 진입
	@RequestMapping(value = "/boardView.do")
	public String boardView(BoardVO bvo, Model model, HttpServletRequest request) {
		System.out.println("boardView.do 진입");
		List<BoardVO> datas = boardService.selectAll(bvo);
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("게시글 개수: " + datas.size());
		if (datas.size() < 5) { // 현재 게시글이 5개 미만이면
			datas = crawling.sample(request); // 크롤링
			if(datas != null) {
				for(int i=0; i<datas.size(); i++) {
					System.out.println("	로그: [" + (i + 1) + "]번 게시글 INSERT INTO BOARD");
					insertBoard(datas.get(i), model);
				}
			}
		}
		System.out.println("selectPage: " + bvo.getSelectPage());
		model.addAttribute("board", bvo);
		return "board.jsp";
	}

	// 게시글 상세보기 페이지 진입
	@RequestMapping(value = "/boardDetailView.do")
	public String boardDetailView(BoardVO bvo, Model model) {
		System.out.println("boardDetailView.do 진입");
		BoardVO selectBvo = boardService.findPreBoard(bvo);
		selectBvo.setSelectPage(bvo.getSelectPage());
		model.addAttribute("board", selectBvo);
		return "board_detail.jsp";
	}

	// 게시글 작성하기 페이지 진입
	@RequestMapping(value = "/insertBoardView.do")
	public String insertBoardView(BoardVO bvo, Model model) {
		System.out.println("insertBoardView.do 진입");
		model.addAttribute("board", bvo);
		return "board_write.jsp";
	}

	// 게시글 작성하기 페이지에서
	// 게시글 작성(insert) 수행 및 해당 글 상세 보기(작성 결과 보기) 페이지로 이동
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO bvo, Model model) {
		System.out.println("insertBoard.do 진입");
		// 새 게시글 저장
		boardService.insert(bvo);
		// 최근 게시물 가져오기(게시글 번호를 가져오기 위해)
		bvo.setBoardSearchCondition("last");
		// 현재 게시글 번호 저장
		BoardVO preBvo = boardService.selectOne(bvo);
		int preNum = preBvo.getBoardNum();
		System.out.println(preNum + "번 게시글 저장!");
		// 게시글 내용을 공백으로 구분하여 wordArray 배열에 담기
		String[] wordArray = makeWordArray(bvo);
		// 문자열의 끝에서 조사와 문장 부호를 제거하기
		wordArray = removePostPosition(wordArray);
		// 배열에서 중복 제거하기
		ArrayList<WordVO> wordList = removeDuplication(wordArray);
		// WORD 테이블 UPDATE 수행
		for(WordVO v : wordList) {
			v.setBoardNum(preNum);
			v.setSearchCondition("insert");
			wordService.insert(v);
		}
		// 가장 최근 게시글 B_NO 전달하기
		return "boardDetailView.do?boardNum=" + preNum;
	}
	
	// 게시글 수정하기 페이지 진입
	@RequestMapping(value = "/updateBoardView.do")
	public String updateBoardView(BoardVO bvo, Model model) {
		System.out.println("updateBoardView.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		System.out.println("bvo.selectPage: " + bvo.getSelectPage());
		BoardVO preBvo = boardService.selectOne(bvo);
		preBvo.setSelectPage(bvo.getSelectPage());
		model.addAttribute("board", preBvo);
		return "board_modify.jsp";
	}
	
	// 게시글 수정하기
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(BoardVO bvo, Model model) {
		System.out.println("updateBoard.do 진입");
		// WORD 테이블 DELETE 수행
		WordVO wvo = new WordVO();
		int preNum = bvo.getBoardNum();
		wvo.setBoardNum(preNum);
		wordService.delete(wvo);
		// BORAD 테이블의 데이터 갱신
		boardService.update(bvo);
		System.out.println(preNum + "번 게시글 갱신!");
		// 게시글 내용을 공백으로 구분하여 wordArray 배열에 담기
		String[] wordArray = makeWordArray(bvo);
		// 문자열의 끝에서 조사와 문장 부호를 제거하기
		wordArray = removePostPosition(wordArray);
		// 배열에서 중복 제거하기
		ArrayList<WordVO> wordList = removeDuplication(wordArray);
		// WORD 테이블 UPDATE 수행
		for(WordVO v : wordList) {
			v.setBoardNum(preNum);
			v.setSearchCondition("insert");
			wordService.insert(v);
		}
		// 게시글 B_NO 전달하기
		return "boardDetailView.do?boardNum=" + preNum +"&selectPage=" + bvo.getSelectPage();
	}
	
	// 게시글 삭제하기 페이지 진입
	@RequestMapping(value = "/deleteBoardView.do")
	public String deleteBoardView(BoardVO bvo, Model model) {
		System.out.println("deleteBoardView.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		BoardVO preBvo = boardService.findPreBoard(bvo);
		preBvo.setSelectPage(bvo.getSelectPage());
		System.out.println("preBvo: " + preBvo);
		model.addAttribute("board", preBvo);
		return "board_delete.jsp";
	}
	
	// 게시글 삭제하기
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO bvo, Model model) {
		System.out.println("deleteBoard.do 진입");
		// WORD 테이블 DELETE
		WordVO wvo = new WordVO();
		wvo.setBoardNum(bvo.getBoardNum());
		wordService.delete(wvo);
		// REPLY 테이블 DELETE
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNum(bvo.getBoardNum());
		replyService.delete(rvo);
		// BORAD 테이블 DELETE
		boardService.delete(bvo);
		return "boardView.do?selectPage=" + bvo.getSelectPage();
	}
	
	// 게시글 목록 불러오기
	@ResponseBody
	@RequestMapping(value = "/getBoardList.do")
	public JsonArray sendBoardList() {
		System.out.println("getBoardList.do 진입");
		// JsonArray를 전달
		List boardList = null;
		BoardVO bvo = new BoardVO();
		boardList = boardService.selectAll(bvo); // 전체 게시글 목록
		JsonArray data = new Gson().toJsonTree(boardList).getAsJsonArray(); // JsonArry로 변경하여 반환
		return data;
	}
	
	// 관련 게시글 목록 불러오기
	@ResponseBody
	@RequestMapping(value = "/getRelatedList.do")
	public JsonArray sendRelatedList(WordVO wvo) {
		System.out.println("getRelatedList.do 진입");
		System.out.println("boardNum: " + wvo.getBoardNum());
		// JsonArray를 전달
		List relatedList = null;
		relatedList = wordService.selectAll(wvo); // 전체 게시글 목록
		JsonArray data = new Gson().toJsonTree(relatedList).getAsJsonArray(); // JsonArry로 변경하여 반환
		return data;
	}
	
	// 댓글 삽입
	@ResponseBody
	@RequestMapping(value = "/insertReply.do")
	public JsonArray insertReply(ReplyVO rvo) {
		System.out.println("insertReply.do 진입");
		replyService.insert(rvo);
		replyService.update(replyService.selectOne(rvo));
		List replyList = null;
		replyList = replyService.selectAll(rvo);
		JsonArray data = new Gson().toJsonTree(replyList).getAsJsonArray();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertReReply.do")
	public JsonArray insertReReply(ReplyVO rvo) {
		System.out.println("insertReReply.do 진입");
		replyService.insert(rvo);
		List replyList = null;
		replyList = replyService.selectAll(rvo);
		JsonArray data = new Gson().toJsonTree(replyList).getAsJsonArray();
		return data;
	}
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteReply.do")
	public JsonArray deleteReply(ReplyVO rvo) {
		System.out.println("deleteReply.do 진입");
		replyService.delete(rvo);
		List replyList = null;
		replyList = replyService.selectAll(rvo);
		JsonArray data = new Gson().toJsonTree(replyList).getAsJsonArray();
		return data;
	}
	
	// 댓글 목록 불러오기
	@ResponseBody
	@RequestMapping(value = "/selectAllReply.do")
	public JsonArray selectAllReply(ReplyVO rvo) {
		System.out.println("selectAllReply.do 진입");
		System.out.println("rvo.boardNum: " + rvo.getBoardNum());
		List replyList = null;
		replyList = replyService.selectAll(rvo);
		JsonArray data = new Gson().toJsonTree(replyList).getAsJsonArray(); 
		return data;
	}
	
	////////////////////////////////////// 메서드 모듈화 부분 //////////////////////////////////////
		
	// ------------------------------- 게시글 내용 배열화 파트 시작 -------------------------------
	public String[] makeWordArray(BoardVO bvo) {
		// 게시글 내용을 공백으로 구분하여 wordArray 배열에 담기
		System.out.println("inserBoard.do step 단어 나누기");
		String[] wordArray = bvo.getBoardContent().split(" ");

	    return wordArray;
	}
	// ------------------------------- 게시글 내용 배열화 파트 끝 -------------------------------
	
	
	// ------------------------------- 단어에서 조사와 문장부호 제거 파트 시작 -------------------------------
	public String[] removePostPosition(String[] wordArray) {
		System.out.println("inserBoard.do step 단어 다듬기");
		for(int n=0; n<wordArray.length; n++) {
	        // 세 글면 마지막 글자 가져와서 처리
	        if(wordArray[n].length() == 3) {
	        	String lastChar = wordArray[n].substring(wordArray[n].length() - 1);
	        	if(lastChar.matches("은|는|이|가|을|를|에|로|의|\\,|\\.|\\!|\\?|\\:")) {
	        		wordArray[n] = wordArray[n].substring(0, wordArray[n].length() - 1);
	        	}
	        }
	        // 네 글자 이상이면 마지막 두 글자 가져와서 처리
	        else if(wordArray[n].length() > 3) {
	        	String lastChar = wordArray[n].substring(wordArray[n].length() - 1);
	        	String lastTwoChars = wordArray[n].substring(wordArray[n].length() - 2);
	        	if(lastChar.matches("은|는|이|가|을|를|에|로|의|\\,|\\.|\\!|\\?|\\:")) {
	        		wordArray[n] = wordArray[n].substring(0, wordArray[n].length() - 1);
	        	}
	        	else if(lastTwoChars.matches("에서|으로|부터|라고|까지|하고|하여|하지|")) {
	        		wordArray[n] = wordArray[n].substring(0, wordArray[n].length() - 2);
	        	}
	        }
	    }
		return wordArray;
	}
	// ------------------------------- 단어에서 조사와 문장부호 제거 파트 끝 -------------------------------
	
	
	// ------------------------------- 중복 단어 제거한 단어 배열 생성 파트 시작 -------------------------------
	public ArrayList<WordVO> removeDuplication(String[] wordArray) {
		// wordArray를 WORD 테이블에 갱신하기 데이터 만들기
		System.out.println("inserBoard.do step 중복 제거");
		ArrayList<WordVO> wordList = new ArrayList<WordVO>(); // 최종 리스트
		for(int i=0; i<wordArray.length; i++) {
			// wordArray[i] : 현재 중복을 체크하는 word
			// wordList의 단어가 wordList2(중복을 제거한 리스트)에 이미 있는지 체크
			boolean foundFlag = false;
			for(WordVO v : wordList) {
				if(wordArray[i].equals(v.getWordWord())){
					foundFlag = true;
					break;
				}
			}
			if(!foundFlag) {
				// wordList2(중복을 제거한 리스트)에 아직 없는 단어라면
				WordVO wvo = new WordVO();
				int wordFound=0;
				wvo.setWordWord(wordArray[i]);
				for(int a=0; a<wordArray.length; a++) {
					if(wordArray[i].equals(wordArray[a])) { // 글 내부에서 같은 단어 발견
						wordFound ++; // 단어 수 카운트
					}
				}
				
				wvo.setWordFound(wordFound);
				wvo.setWordRatio((int)Math.round((wordFound * 1.0) / (wordArray.length * 1.0) * 100));
				wordList.add(wvo); // 고유한 wvo를 추가함
			}
		}
		return wordList;
	}
	// ------------------------------- 중복 단어 제거한 단어 배열 생성 파트 끝 -------------------------------
	
	
	// ------------------------------- 버블 정렬 파트 시작 -------------------------------
	// 버블정렬 가동
	public void bubbleSort(ArrayList<WordVO> data) {
		System.out.println("inserBoard.do step 배열리스트 정렬");
		bubbleSort(data, data.size());
	}
	
	// 버블정렬 실행
	private void bubbleSort(ArrayList<WordVO> data, int size) {
		// round는 배열 크기 - 1 만큼 진행됨 
		for(int i = 1; i < size; i++) {
			// 각 라운드별 비교횟수는 배열 크기의 현재 라운드를 뺀 만큼 비교함
			for(int j = 0; j < size - i; j++) {
				/*
				 *  현재 원소가 다음 원소보다 클 경우
				 *  서로 원소의 위치를 교환한다. 
				 */
				if(data.get(j).getWordFound() > data.get(j+1).getWordFound()) {
					swap(data, j, j + 1);
				}
			}
		}
	}
	
	// 치환 로직
	private void swap(ArrayList<WordVO> data, int i, int j) {
		WordVO tmp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, tmp);
	}
	// ------------------------------- 버블 정렬 파트 끝 -------------------------------
}