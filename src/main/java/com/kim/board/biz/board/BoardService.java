package com.kim.board.biz.board;

import java.util.List;

public interface BoardService {
	public boolean insert(BoardVO vo);
	public List<BoardVO> selectAll(BoardVO vo);
	public BoardVO selectOne(BoardVO vo);
}
