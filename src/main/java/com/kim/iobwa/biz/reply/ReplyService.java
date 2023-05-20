package com.kim.iobwa.biz.reply;

import java.util.List;

public interface ReplyService {
	public boolean insert(ReplyVO vo);
	public boolean delete(ReplyVO vo);
	public List<ReplyVO> selectAll(ReplyVO vo);
}
