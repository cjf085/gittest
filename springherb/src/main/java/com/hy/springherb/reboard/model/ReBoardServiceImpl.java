package com.hy.springherb.reboard.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.springherb.board.model.BoardServiceImpl;
import com.hy.springherb.common.SearchVO;

@Service
public class ReBoardServiceImpl implements ReBoardService{
	private static final Logger Logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private ReBoardDAO reBoardDao;	
		
	public int insertReBoard(ReBoardVO vo) {		
		return reBoardDao.insertReBoard(vo);
	}

	@Override
	public List<ReBoardVO> selectAll(SearchVO searchVo) {
		return reBoardDao.selectAll(searchVo);
	}

	@Override
	public int selectTotalRecordCount(SearchVO searchVo){
		return reBoardDao.selectTotalRecordCount(searchVo);
	}
	
	public int updateReadCount(int no) {
		return reBoardDao.updateReadCount(no);
	}
	
	public ReBoardVO selectByNo(int no) {
		return reBoardDao.selectByNo(no);
	}
	
	public boolean checkPwd(int no, String pwd){
		String dbPwd = reBoardDao.selectPwd(no);
		if(dbPwd.equals(pwd)) {
			return true;
		}else {
			return false;
		}
	}
	
	public int updateReBoard(ReBoardVO vo) {
		return reBoardDao.updateReBoard(vo);	
	}
	
	public void deleteReBoard(Map<String, String> map){
		reBoardDao.deleteReBoard(map);
	}

	public int updateDowncount(int no) {
		return reBoardDao.updateDowncount(no);
	}

	@Transactional //처리 실패시 자동 롤백
	public int reply(ReBoardVO vo) {
		int cnt = reBoardDao.updateSortNo(vo);
		Logger.info("답변처리 - sortno 수정 결과 cnt={}", cnt);
		
		/*vo.setSortNo(vo.getSortNo()+1);
		vo.setStep(vo.getStep()+1);*/
		
		cnt = reBoardDao.replyReboard(vo);
		Logger.info("답변처리 결과, cnt={}", cnt);
		
		return cnt;
	}
	
	
	
	/*
	public List<ReBoardVO> mainNotice() throws SQLException {
		return reReBoardDao.mainNotice();
	}*/
	
	
}







