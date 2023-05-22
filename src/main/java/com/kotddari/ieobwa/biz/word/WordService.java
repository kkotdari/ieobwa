package com.kotddari.ieobwa.biz.word;

import java.util.List;

import com.kotddari.ieobwa.biz.board.BoardVO;

public interface WordService {
	public boolean insert(WordVO vo);
	public boolean delete(WordVO vo);
	public List<BoardVO> selectAll(WordVO vo);
}
