package com.hy.springherb.board.model;

import java.util.List;

import com.hy.springherb.common.SearchVO;
import com.hy.springherb.reboard.model.ReBoardVO;

public interface BoardDAO {
	public int insertBoard(BoardVO vo);
	public List<BoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecordCount(SearchVO searchVo);
	public int updateReadCount(int no);
	public BoardVO selectByNo(int no);
	public String selectPwd(int no); 
	public int updateBoard(BoardVO vo);
	public int deleteBoard(int no);
	
	/*
	public List<BoardVO> mainNotice();*/
	
}

