package com.kotddari.ieobwa.biz.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate myBatis;

	public boolean insert(BoardVO vo) {
		int res = myBatis.insert("BoardDAO.insertBoard", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}
	
	public boolean update(BoardVO vo) {
		int res = myBatis.update("BoardDAO.updateBoard", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}
	
	public boolean delete(BoardVO vo) {
		int res = myBatis.delete("BoardDAO.deleteBoard", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}

	public BoardVO selectOne(BoardVO vo) {
		if(vo.getBoardSearchCondition() != null) { // 검색 조건으로 분기 작업
			if(vo.getBoardSearchCondition().equals("last")) { // 검색조건이 last == 최신 게시글 조회
				System.out.println("	로그: 최신글 조회 진입");
				try {
					return myBatis.selectOne("BoardDAO.selectOneBoardLast", vo);
				} catch (EmptyResultDataAccessException e) {
					System.out.println("BoardDAO2 selectOne 결과 없음");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			else {
				return null;
			}
		}
		else { // 검색 조건이 null == 기본적인 게시글 조회
			try {
				return myBatis.selectOne("BoardDAO.selectOneBoard", vo);
			} catch (EmptyResultDataAccessException e) {
				System.out.println("BoardDAO2 selectOne 결과 없음");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public List<BoardVO> selectAll(BoardVO vo) {
		return myBatis.selectList("BoardDAO.selectAllBoard", vo);
	}
}

