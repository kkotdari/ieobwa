package com.kim.board.biz.related;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.board.biz.board.BoardVO;

@Repository("relatedDAO")
public class RelatedDAO {
	@Autowired
	private SqlSessionTemplate myBatis;

	public boolean insert(RelatedVO vo) {
		int res = myBatis.insert("RelatedDAO.insertRelated", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}

	public List<BoardVO> selectAll(RelatedVO vo) {
		return myBatis.selectList("RelatedDAO.selectAllRelated", vo);
	}
}
