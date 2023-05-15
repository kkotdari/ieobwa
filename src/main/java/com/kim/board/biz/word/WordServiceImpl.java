package com.kim.board.biz.word;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.board.biz.board.BoardVO;

@Service("wordService")
public class WordServiceImpl implements WordService {
	@Autowired
	private WordDAO wordDAO;

	@Override
	public boolean insert(WordVO vo) {
		return wordDAO.insert(vo);
	}
	
	@Override
	public boolean delete(WordVO vo) {
		return wordDAO.delete(vo);
	}
	
	@Override
	public List<BoardVO> selectAll(WordVO vo) {
		return wordDAO.selectAll(vo);
	}
}
