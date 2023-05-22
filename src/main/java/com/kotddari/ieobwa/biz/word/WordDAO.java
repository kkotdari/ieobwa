package com.kotddari.ieobwa.biz.word;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kotddari.ieobwa.biz.board.BoardVO;

@Repository("wardDAO")
public class WordDAO {
	@Autowired
	private SqlSessionTemplate myBatis;

	public boolean insert(WordVO vo) {
		int res = myBatis.insert("WordDAO.insertWord", vo);
		if (res < 1) {
				return false;
		}
		return true;
	}
	
	public boolean delete(WordVO vo) {
		int res = myBatis.delete("WordDAO.deleteWord", vo);
		if (res < 1) {
				return false;
		}
		return true;
	}
	
	public List<BoardVO> selectAll(WordVO vo) {
		return myBatis.selectList("WordDAO.selectAllWord", vo);
	}
}

