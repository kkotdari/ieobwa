package com.kim.board.biz.related;

import java.util.List;

import com.kim.board.biz.board.BoardVO;

public interface RelatedService {
	public boolean insert(RelatedVO vo);
	public boolean delete(RelatedVO vo);
	public List<BoardVO> selectAll(RelatedVO vo);
}
