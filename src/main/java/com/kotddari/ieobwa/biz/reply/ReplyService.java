package com.kotddari.ieobwa.biz.reply;

import java.util.List;

public interface ReplyService {
	public boolean insert(ReplyVO vo);
	public boolean update(ReplyVO vo);
	public boolean delete(ReplyVO vo);
	public List<ReplyVO> selectAll(ReplyVO vo);
	public ReplyVO selectOne(ReplyVO vo);
}
