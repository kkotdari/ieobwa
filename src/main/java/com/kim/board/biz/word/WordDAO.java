package com.kim.board.biz.word;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository("wardDAO")
public class WordDAO {
	@Autowired
	private SqlSessionTemplate myBatis;

	public boolean update(WordVO vo) {
		WordVO wvo = selectOne(vo); 
		if(wvo != null) {
			int res2 = myBatis.update("WordDAO.updateWord", vo);
			if (res2 < 1) {
				return false;
			}
			return true;
		}
		else {
			int res2 = myBatis.insert("WordDAO.insertWord", vo);
			if (res2 < 1) {
				return false;
			}
			return true;
		}
	}
	
	public WordVO selectOne(WordVO vo) {
		try {
			return myBatis.selectOne("WordDAO.selectOneWord", vo);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("WordDAO selectOne 결과 없음");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

