package com.hy.springherb.board.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.springherb.common.SearchVO;
import com.hy.springherb.reboard.model.ReBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDao;	
		
	public int insertBoard(BoardVO vo) {		
		return boardDao.insertBoard(vo);
	}

	@Override
	public List<BoardVO> selectAll(SearchVO searchVo) {
		return boardDao.selectAll(searchVo);
	}

	@Override
	public int selectTotalRecordCount(SearchVO searchVo){
		return boardDao.selectTotalRecordCount(searchVo);
	}
	
	public int updateReadCount(int no) {
		return boardDao.updateReadCount(no);
	}
	
	public BoardVO selectByNo(int no) {
		return boardDao.selectByNo(no);
	}
	
	public boolean checkPwd(int no, String pwd){
		String dbPwd = boardDao.selectPwd(no);
		if(dbPwd.equals(pwd)) {
			return true;
		}else {
			return false;
		}
	}
	
	public int updateBoard(BoardVO vo) {
		return boardDao.updateBoard(vo);	
	}
	
	public int deleteBoard(int no){
		return boardDao.deleteBoard(no);
	}

	
	
	/*
	
	
	
	
	public List<BoardVO> mainNotice() throws SQLException {
		return boardDao.mainNotice();
	}*/
	
	
}







