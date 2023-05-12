package com.kim.board.biz.related;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.board.biz.board.BoardVO;

@Service("relatedService")
public class RelatedServiceImpl implements RelatedService {
	@Autowired
	private RelatedDAO relatedDAO;

	@Override
	public boolean insert(RelatedVO vo) {
		return relatedDAO.insert(vo);
	}
	
	@Override
	public List<BoardVO> selectAll(RelatedVO vo) {
		return relatedDAO.selectAll(vo);
	}
}
