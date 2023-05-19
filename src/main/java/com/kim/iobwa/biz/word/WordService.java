package com.kim.iobwa.biz.word;

import java.util.List;

import com.kim.iobwa.biz.board.BoardVO;

public interface WordService {
	public boolean insert(WordVO vo);
	public boolean delete(WordVO vo);
	public List<BoardVO> selectAll(WordVO vo);
}
