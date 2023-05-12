package com.kim.board.biz.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wordService")
public class WordServiceImpl implements WordService {
	@Autowired
	private WordDAO wordDAO;

	@Override
	public boolean update(WordVO vo) {
		return wordDAO.update(vo);
	}
	
	@Override
	public WordVO selectOne(WordVO vo) {
		return wordDAO.selectOne(vo);
	}
}
