package com.kotddari.ieobwa.biz.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.kotddari.ieobwa.biz.board.BoardVO;

@Component
public class Crawling {
	final String SWITCH = "N"; // ※ 크롤링 여부 (Y/N)
	final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
	final String WEB_DRIVER_PATH = "F:/Dev/kotddari/workspace/ieobwa/src/main/webapp/Source/chromedriver.exe"; // 드라이버
	final int BOARD = 100; // ※ 크롤링할 게시물 개수 (범위는 5~100이며 클수록 시간이 늘어나므로 30이하로 하는 것을 추천!)
	
	private int max = BOARD / 5 * 2;
	List<BoardVO> datas = new ArrayList<BoardVO>(); // 크롤링 데이터 저장 배열리스트

	public List<BoardVO> sample(HttpServletRequest request) {
		if(SWITCH.equals("N")) {
			System.out.println("크롤링 실행 안함");
			return null;
		}

		System.out.println("로그: Crawling.sample 시작");
		System.out.println();


		List<BoardVO> datas = sampleStep01(request); // 반환받은 url 배열리스트

		// sampleStep02 시작
		System.out.println("로그: sampleStep02 시작");
		System.out.println();

		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-popup-blocking"); // 팝업 안 띄움
		options.addArguments("headless"); // 브라우저 안 띄움
		options.addArguments("--disable-gpu"); // gpu 비활성화
		options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 다운 안 받음

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		for (int i = 0; i < datas.size(); i++) { // url 배열 크기만큼 반복
			
			System.out.println("▶ " + (i + 1) + "번 sampleStep02 시작!");

			driver.get(datas.get(i).getBoardSearchCondition()); // 크롤링할 상세 페이지 링크 연결;
			
			datas.get(i).setBoardWriter("TEST");
			datas.get(i).setBoardPassword("1234");
			
			// 제목 용도 데이터 크롤링
			String info;
			try {
				info = driver.findElement(By.xpath("html/body/div/div/div/div/div/div/h1")).getText();
				datas.get(i).setBoardTitle(info);
			} catch (Exception e) {
				System.out.println("※※※ sampleStep02 " + (i + 1) + "번 상품 이름 크롤링 실패!");
				datas.get(i).setBoardTitle("아름다운 우리 강산 푸르게 푸르게");
			}
			
			// 내용 용도 데이터 크롤링
			List<WebElement> info2 = new ArrayList<WebElement>();;
			try {
				info2 = driver.findElements(By.xpath("html/body/div/div/div/div/div/div/div/p"));
				String info2Sum = "";
				for(WebElement v : info2) {
					info2Sum += v.getText();
				}
				datas.get(i).setBoardContent(info2Sum);
			} catch (Exception e) {
				System.out.println("※※※ sampleStep02 " + (i + 1) + "번 상세정보 크롤링 실패!");
				datas.get(i).setBoardContent("얼마나 같은 이상의 인생의 가치를 있는 무한한 아니다. 이상 꽃이 낙원을 날카로우나 그러므로 것은 황금시대다. 없으면, 앞이 착목한는 힘있다. 무한한 들어 구하지 이것이다. 꽃이 이상 구하지 보라. 일월과 현저하게 피고, 피에 피다. 그들은 있으며, 장식하는 그림자는 두기 아니다. 끝까지 인생에 능히 하여도 힘차게 품고 착목한는 아니다. 기쁘며, 방황하였으며, 타오르고 예수는 구할 용감하고 능히 청춘의 속잎나고, 보라. 풀밭에 간에 피부가 속잎나고, 반짝이는 이것이다.");
			}

			// 크롤링 데이터 확인 부분
			System.out.println("글번호: " + (i + 1));
			System.out.println("제목: " + datas.get(i).getBoardTitle());
			try {
				System.out.println("내용: " + datas.get(i).getBoardContent().substring(0, 50) + " ...");
			} catch (Exception e) {
				System.out.println("내용: " + datas.get(i).getBoardContent());
			}
			
			// 리스트에 반환하기
			
		}
		try {
			if (driver != null) {
				// 드라이버 연결 종료
				driver.close(); // 드라이버 연결 해제
				// 프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		// sampleStep02 끝
		System.out.println("sampleStep02 끝");
		System.out.println();
		System.out.println("Crawling.sample 끝");
		System.out.println();
		return datas;
	}

// ---------------------------------------------------------------------------------------

	public List<BoardVO> sampleStep01(HttpServletRequest request) { // 카테고리별 url 가져오기

		// sampleStep01 시작
		System.out.println("sampleStep01 시작");
		System.out.println();

		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");
		options.addArguments("headless"); // 브라우저 안 띄움

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		List<String> categoryDatas = new ArrayList<String>(); // 페이지 리스트

		categoryDatas.add("https://zdnet.co.kr/news/?lstcode=0000&page=5");
		categoryDatas.add("https://zdnet.co.kr/news/?lstcode=0000&page=4");
		categoryDatas.add("https://zdnet.co.kr/news/?lstcode=0000&page=3");
		categoryDatas.add("https://zdnet.co.kr/news/?lstcode=0000&page=2");
		categoryDatas.add("https://zdnet.co.kr/news/?lstcode=0000&page=1");

		int count = 1;
		for (int a = 0; a < categoryDatas.size(); a++) { // 카테고리 수만큼 반복
			driver.get(categoryDatas.get(a)); // 순차적으로 url 크롤링
			System.out.println("▶ " + (a + 1) + "페이지 크롤링 시작!");
			// 카테고리별 MAX만큼 상세 페이지 URL 크롤링
			List<WebElement> el1 = driver.findElements(By.xpath("html/body/div/div/div/div/div/div/a"));
			
			// 카테고리별 MAX만큼 datas에 추가
			for (int i = 0; i < max; i++) {
				
				if(i % 2 == 0) { // 중복 크롤링 제거
					System.out.println("▶ " + count + "번 sampleStep01 시작!");
	
					BoardVO data = new BoardVO();
	
					try {
						data.setBoardSearchCondition(el1.get(i).getAttribute("href")); // 상세 페이지 주소를 검색 조건에 저장
					} catch (Exception e) {
						System.out.println("※※※ sampleStep01 " + count + "번 상세주소 크롤링 실패!");
						data.setBoardSearchCondition("No Detail URL");
					}
					
					// 크롤링 데이터 확인 부분
					System.out.println("글번호: " + count);
					System.out.println("상세페이지주소: " + data.getBoardSearchCondition());
	
					datas.add(data);
					
					count ++;
					
					System.out.println("■ " + count + "번 sampleStep01 완료!");
					System.out.println();
					System.out.println();
				}
			}

		}
		try {
			if (driver != null) {
				// 드라이버 연결 종료
				driver.close(); // 드라이버 연결 해제
				// 프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		// sampleStep01 끝
		System.out.println("sampleStep01 끝");
		System.out.println();

		return datas; // sampleStep01 결과 담은 배열리스트 반환
	}
}
