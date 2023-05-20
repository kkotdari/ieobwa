package com.kim.iobwa.biz.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public boolean insert(ReplyVO vo) {
		return replyDAO.insert(vo);
	}
	
	@Override
	public boolean delete(ReplyVO vo) {
		return replyDAO.delete(vo);
	}
	
	@Override
	public List<ReplyVO> selectAll(ReplyVO vo) {
		return replyDAO.selectAll(vo);
	}
}
