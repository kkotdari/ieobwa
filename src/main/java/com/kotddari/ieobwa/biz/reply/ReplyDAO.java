package com.kotddari.ieobwa.biz.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository("replyDAO")
public class ReplyDAO {
	@Autowired
	private SqlSessionTemplate myBatis;

	public boolean insert(ReplyVO vo) {
		int res = myBatis.insert("ReplyDAO.insertReply", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}
	
	public boolean update(ReplyVO vo) {
		int res = myBatis.update("ReplyDAO.updateReply", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}
	
	public boolean delete(ReplyVO vo) {
		int res = myBatis.delete("ReplyDAO.deleteReply", vo);
		if (res < 1) {
			return false;
		}
		return true;
	}

	public List<ReplyVO> selectAll(ReplyVO vo) {
		return myBatis.selectList("ReplyDAO.selectAllReply", vo);
	}
	
	public ReplyVO selectOne(ReplyVO vo) {
		return myBatis.selectOne("ReplyDAO.selectOneReply", vo);
	}
}

