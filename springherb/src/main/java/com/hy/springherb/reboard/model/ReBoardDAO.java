package com.hy.springherb.reboard.model;

import java.util.List;
import java.util.Map;

import com.hy.springherb.common.SearchVO;

public interface ReBoardDAO {
	public int insertReBoard(ReBoardVO vo);
	public List<ReBoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecordCount(SearchVO searchVo);
	public int updateReadCount(int no);
	public ReBoardVO selectByNo(int no);
	public String selectPwd(int no); 
	public int updateReBoard(ReBoardVO vo);
	public void deleteReBoard(Map<String, String> map);
	/*
	public List<ReBoardVO> mainNotice();*/
	public int updateDowncount(int no);

	public int updateSortNo(ReBoardVO vo);
	public int replyReboard(ReBoardVO vo);
}

