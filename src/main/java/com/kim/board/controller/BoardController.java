package com.kim.board.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kim.board.biz.board.BoardService;
import com.kim.board.biz.board.BoardVO;
import com.kim.board.biz.common.Crawling;
import com.kim.board.biz.related.RelatedService;
import com.kim.board.biz.related.RelatedVO;
import com.kim.board.biz.word.WordService;
import com.kim.board.biz.word.WordVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private RelatedService relatedService;
	@Autowired
	private WordService wordService;
	@Autowired
	private Crawling crawling;

	// 게시판 목록 페이지 진입
	@RequestMapping(value = "/boardView.do")
	public String boardView(BoardVO bvo, Model model, HttpServletRequest request) {
		System.out.println("boardView.do 진입");
		List<BoardVO> datas = boardService.selectAll(bvo);
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("게시글 개수: " + datas.size()); // 상품 개수 로그
		if (datas.size() < 5) { // 현재 게시글이 25개 미만이면
			datas = crawling.sample(request); // 크롤링을 하고
			for(BoardVO v: datas) {
				insertBoard(v, model); // 크롤링한 데이터로 게시글 작성하기 과정을 수행
			}
		}
		else {
			System.out.println("게시물 이미 생성됨");
		}
		model.addAttribute("boardNum", bvo.getBoardNum());
		return "board.jsp";
	}

	// 게시글 상세보기 페이지 진입
	@RequestMapping(value = "/boardDetailView.do")
	public String boardDetailView(BoardVO bvo, Model model) {
		System.out.println("boardDetailView.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		BoardVO preBvo = boardService.selectOne(bvo);
		model.addAttribute("board", preBvo);
		return "board_detail.jsp";
	}

	// 게시글 작성하기 페이지 진입
	@RequestMapping(value = "/insertBoardView.do")
	public String insertBoardView(HttpSession session, HttpServletResponse response) {
		System.out.println("insertBoardView.do 진입");
		return "board_write.jsp";
	}

	// 게시글 작성하기 페이지에서
	// 게시글 작성(insert) 수행 및 해당 글 상세 보기(작성 결과 보기) 페이지로 이동
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO bvo, Model model) {
		System.out.println("insertBoard.do 진입");
		System.out.println("bvo: " + bvo);
		// 전체 게시글 목록 가져오고 새 게시글 저장
		System.out.println("inserBoard.do step 1-1");
		List<BoardVO> allBoardList = boardService.selectAll(bvo);
		boardService.insert(bvo);
		bvo.setBoardSearchCondition("last");
		BoardVO preBvo = boardService.selectOne(bvo);
		System.out.println("저장한 게시글 preBvo: " + preBvo);
		int preNum = preBvo.getBoardNum();
		// 연관 게시물 탐색
			
			// 게시글 내용을 공백으로 구분하여 wordArray 배열에 담기
			String[] wordArray = makeWordArray(bvo);
			
			// 문자열의 끝에서 조사와 문장 부호를 제거하기
			wordArray = removePostPosition(wordArray);
			
			// 배열에서 중복 제거하기
			String[] uniqueArray = removeDuplication(wordArray);
			
			// WORD 테이블 UPDATE 수행
			WordVO wvo = new WordVO();
			wvo.setSearchCondition("insert");
			for(String v : uniqueArray) {
				wvo.setWordWord(v);
				wordService.update(wvo);
			}
			
			// uniqueArray 배열을 preWordVOList 배열리스트로 변경하기(발견도 40 이상 단어 제외)
			ArrayList<WordVO> wordVOList = makeWordList(uniqueArray);
			
			// 게시글 내의 사용 빈도 기준으로 내림차순 정렬
			bubbleSort(wordVOList);

			// 비교작업 시작
			System.out.println("inserBoard.do step 비교작업");
			for(int j=0; j<allBoardList.size(); j++) {
			// 비교 게시글도 작업 수행
				System.out.println("inserBoard.do step 비교작업-" + (j + 1));
				BoardVO bvo2 = new BoardVO();
				bvo2.setBoardContent(allBoardList.get(j).getBoardContent());
				String[] uniqueArray2 = makeWordArray(bvo2);
				ArrayList<WordVO> wordVOList2 = makeWordList(uniqueArray2);
				bubbleSort(wordVOList2);
				
				// preWordVOList와 compareWordVOList를 비교하기 시작
				int repetition = 0;
				int totalWord = 0;
				int foundWordCount = 0;
				for(int b=0; b<wordVOList2.size(); b++) {
					boolean foundFlag = false;
					totalWord += wordVOList2.get(b).getWordFound(); // 모든 단어의 개수의 합을 구함
					for(int a=0; a<wordVOList.size(); a++) {
						// 같은 단어를 발견하면
						if(wordVOList2.get(b).getWordWord()
								.equals(wordVOList.get(a).getWordWord())) {
							repetition += wordVOList2.get(b).getWordFound();
							foundFlag = true;
							System.out.println("pre-compare 일치하는 단어 발견");
						}
					}
					if(foundFlag) {
						System.out.println("같은 단어 발견됨");
						foundWordCount ++;
					}
				}
				if(foundWordCount >= 2) {
					// RELATED 테이블에 INSERT : repetition은 클수록, importance는 작을수록 연관도 높음)
					// 이전의 게시물에 대해 연관이 생긴다면 이전게시물이 메인인 정보도 이 때 함께 만들어줘야 함
					RelatedVO rvo = new RelatedVO();
					int relatedNum = allBoardList.get(j).getBoardNum();
					rvo.setOriginalBoardNum(preNum);
					rvo.setRelatedBoardNum(relatedNum);
					System.out.println("repetition: " + repetition);
					System.out.println("totalWord: " + totalWord);
					rvo.setRelatedRepetition(repetition); // 중복 발견되므로 나누기 2
					rvo.setRelatedImportance((int)Math.round((repetition * 1.0) / (totalWord * 1.0) * 100)); // 전체중의 연관 단어 비율
					System.out.println("foundWordCount: " + foundWordCount + " / INSERTING rvo: " + rvo);
					relatedService.insert(rvo);
					// 원본 게시물 번호와 연관 게시물 번호 서로 바꿔서 생성해주기
					rvo.setOriginalBoardNum(relatedNum);
					rvo.setRelatedBoardNum(preNum);
					relatedService.insert(rvo);
					
				}
			}
			// 전체 게시글에 대해 1-7 작업을 끝내면 종료
		
		// 가장 최근 게시글 B_NO 전달하기
		return "boardDetailView.do?boardNum=" + preNum;
	}
	
	// 게시글 상세보기 페이지 진입
	@RequestMapping(value = "/updateBoardView.do")
	public String updateBoardView(BoardVO bvo, Model model) {
		System.out.println("boardDetailView.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		BoardVO preBvo = boardService.selectOne(bvo);
		model.addAttribute("board", preBvo);
		return "board_modify.jsp";
	}
	
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(BoardVO bvo, Model model) {
		// WORD 테이블에 저장된 현재 boardNum 게시글의 word에 대하여 W_COUNT, W_RATIO 재계산
		// 현재 boardNum의 데이터 가져오기
		String newContent = bvo.getBoardContent(); // 수정한 글 내용을 임시 저장
		String preContent = boardService.selectOne(bvo).getBoardContent(); // 원래 글 내용을 가져오기
		bvo.setBoardContent(preContent); // bvo의 내용을 원래 내용으로 변경
		String[] wordArray = makeWordArray(bvo); // bvo의 내용을 배열로 만들기
		// 문자열의 끝에서 조사와 문장 부호를 제거하기
		wordArray = removePostPosition(wordArray);
		// 배열에서 중복 제거하기
		String[] uniqueArray = removeDuplication(wordArray);
		// WORD 테이블 UPDATE 수행
		WordVO wvo = new WordVO();
		wvo.setSearchCondition("delete");
		for(String v : uniqueArray) {
			wvo.setWordWord(v);
			wordService.update(wvo);
		}		
		// RELATED 테이블에서 현재 글 번호가 들어간 데이터를 삭제
		RelatedVO rvo = new RelatedVO();
		rvo.setOriginalBoardNum(bvo.getBoardNum());
		relatedService.delete(rvo);
		
		bvo.setBoardContent(newContent); // bvo의 내용을 새 내용으로 변경
		
		// 전체 게시글 목록 가져오고 새 게시글 저장
		System.out.println("inserBoard.do step 1-1");
		List<BoardVO> allBoardList = boardService.selectAll(bvo);
		boardService.update(bvo);
		int preNum = bvo.getBoardNum();
		// 연관 게시물 탐색
			
			// 게시글 내용을 공백으로 구분하여 wordArray 배열에 담기
			wordArray = makeWordArray(bvo);
			
			// 문자열의 끝에서 조사와 문장 부호를 제거하기
			wordArray = removePostPosition(wordArray);
			
			// 배열에서 중복 제거하기
			uniqueArray = removeDuplication(wordArray);
			
			// WORD 테이블 UPDATE 수행
			wvo = new WordVO();
			wvo.setSearchCondition("insert");
			for(String v : uniqueArray) {
				wvo.setWordWord(v);
				wordService.update(wvo);
			}
			
			// uniqueArray 배열을 preWordVOList 배열리스트로 변경하기(발견도 40 이상 단어 제외)
			ArrayList<WordVO> wordVOList = makeWordList(uniqueArray);
			
			// 게시글 내의 사용 빈도 기준으로 내림차순 정렬
			bubbleSort(wordVOList);

			// 비교작업 시작
			System.out.println("inserBoard.do step 비교작업");
			for(int j=0; j<allBoardList.size(); j++) {
			// 비교 게시글도 작업 수행
				System.out.println("inserBoard.do step 비교작업-" + (j + 1));
				BoardVO bvo2 = new BoardVO();
				bvo2.setBoardContent(allBoardList.get(j).getBoardContent());
				String[] uniqueArray2 = makeWordArray(bvo2);
				ArrayList<WordVO> wordVOList2 = makeWordList(uniqueArray2);
				bubbleSort(wordVOList2);
				
				// preWordVOList와 compareWordVOList를 비교하기 시작
				int repetition = 0;
				int totalWord = 0;
				int foundWordCount = 0;
				for(int b=0; b<wordVOList2.size(); b++) {
					boolean foundFlag = false;
					totalWord += wordVOList2.get(b).getWordFound(); // 모든 단어의 개수의 합을 구함
					for(int a=0; a<wordVOList.size(); a++) {
						// 같은 단어를 발견하면
						if(wordVOList2.get(b).getWordWord()
								.equals(wordVOList.get(a).getWordWord())) {
							repetition += wordVOList2.get(b).getWordFound();
							foundFlag = true;
							System.out.println("pre-compare 일치하는 단어 발견");
						}
					}
					if(foundFlag) {
						System.out.println("같은 단어 발견됨");
						foundWordCount ++;
					}
				}
				if(foundWordCount >= 2) {
					// RELATED 테이블에 INSERT : repetition은 클수록, importance는 작을수록 연관도 높음)
					// 이전의 게시물에 대해 연관이 생긴다면 이전게시물이 메인인 정보도 이 때 함께 만들어줘야 함
					int relatedNum = allBoardList.get(j).getBoardNum();
					rvo.setOriginalBoardNum(preNum);
					rvo.setRelatedBoardNum(relatedNum);
					if(preNum != relatedNum) {
						rvo.setRelatedRepetition(repetition); // 중복 발견되므로 나누기 2
						rvo.setRelatedImportance((int)Math.round((repetition * 1.0) / (totalWord * 1.0) * 100)); // 전체중의 연관 단어 비율
						System.out.println("foundWordCount: " + foundWordCount + " / INSERTING rvo: " + rvo);
						relatedService.insert(rvo);
						// 원본 게시물 번호와 연관 게시물 번호 서로 바꿔서 생성해주기
						rvo.setOriginalBoardNum(relatedNum);
						rvo.setRelatedBoardNum(preNum);
						relatedService.insert(rvo);
					}
				}
			}
			// 전체 게시글에 대해 1-7 작업을 끝내면 종료
		
		// 가장 최근 게시글 B_NO 전달하기
		return "boardDetailView.do?boardNum=" + preNum;
		
	}
	
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO bvo, Model model) {
		// WORD 테이블에 저장된 현재 boardNum 게시글의 word에 대하여 W_COUNT, W_RATIO 재계산
		// 현재 boardNum의 데이터 가져오기
		String preContent = boardService.selectOne(bvo).getBoardContent(); // 원래 글 내용을 가져오기
		bvo.setBoardContent(preContent); // bvo의 내용을 원래 내용으로 변경
		String[] wordArray = makeWordArray(bvo); // bvo의 내용을 배열로 만들기
		// 문자열의 끝에서 조사와 문장 부호를 제거하기
		wordArray = removePostPosition(wordArray);
		// 배열에서 중복 제거하기
		String[] uniqueArray = removeDuplication(wordArray);
		// WORD 테이블 UPDATE 수행
		WordVO wvo = new WordVO();
		wvo.setSearchCondition("delete");
		for(String v : uniqueArray) {
			wvo.setWordWord(v);
			wordService.update(wvo);
		}		
		// RELATED 테이블에서 현재 글 번호가 들어간 데이터를 삭제
		RelatedVO rvo = new RelatedVO();
		rvo.setOriginalBoardNum(bvo.getBoardNum());
		relatedService.delete(rvo);
		// BORAD 테이블의 데이터 삭제
		boardService.delete(bvo);
		return "boardView.do";
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
	public JsonArray sendRelatedList(RelatedVO rvo) {
		System.out.println("getRelatedList.do 진입");
		System.out.println("originalBoardNum: " + rvo.getOriginalBoardNum());
		// JsonArray를 전달
		List relatedList = null;
		relatedList = relatedService.selectAll(rvo); // 전체 게시글 목록
		JsonArray data = new Gson().toJsonTree(relatedList).getAsJsonArray(); // JsonArry로 변경하여 반환
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
	public String[] removeDuplication(String[] wordArray) {
		// wordArray를 WORD 테이블에 갱신하기 위해 중복 제거하기
		System.out.println("inserBoard.do step 중복 제거");
		// 중복 제거를 위해 HashSet 사용
	    HashSet<String> uniqueWords = new HashSet<String>(Arrays.asList(wordArray));
	    // 중복 제거된 문자열 배열 생성
	    String[] uniqueArray = uniqueWords.toArray(new String[uniqueWords.size()]);
		return uniqueArray;
	}
	// ------------------------------- 중복 단어 제거한 단어 배열 생성 파트 끝 -------------------------------
	
	
	// ------------------------------- 연관도 비교를 단어 배열리스트 생성 시작 -------------------------------
	public ArrayList<WordVO> makeWordList(String[] uniqueArray){
		// 1-4. uniqueArray 배열을 순회하며 해당 단어가 몇 번 나오는지 알아내기
		System.out.println("inserBoard.do step 단어 사용수 조사");
		ArrayList<WordVO> wordVOList = new ArrayList<WordVO>();
		for(int i=0; i<uniqueArray.length; i++) {
			int wordFound=0;
			// preWordVOList에 아직 없는 단어라면
			boolean foundFlag = false;
			for(WordVO v : wordVOList) {
				if(uniqueArray[i].equals(v.getWordWord())){
					foundFlag = true;
				}
			}
			if(!foundFlag) {
				WordVO wvo2 = new WordVO();
				for(int a=0; a<uniqueArray.length; a++) {
					wvo2.setWordWord(uniqueArray[i]);
					if(uniqueArray[i].equals(uniqueArray[a])) {
						wordFound++;
						System.out.println("pre 내부 일치하는 단어 발견");
					}
				}
				// 1-5. 단어별로 w_percentage < 40 면 단어, 횟수, 비중을 wordVO에 담기 -> wordVOList에 담기
				System.out.println("inserBoard.do step 배열리스트 생성");
				if(wordService.selectOne(wvo2) == null || wordService.selectOne(wvo2).getWordRatio() < 40) {
					wvo2.setWordFound(wordFound);
					wvo2.setWordRatio(wordFound/uniqueArray.length*100);
					wordVOList.add(wvo2);
					System.out.println("단어 발견 빈도 40% 미만. preWordVOList에 추가함");
				}
			}
		}
		return wordVOList;
	}
	// ------------------------------- 연관도 비교를 단어 배열리스트 생성 끝 -------------------------------
	
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
