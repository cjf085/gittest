package com.hy.springherb.reboard.model;

import java.util.List;
import java.util.Map;

import com.hy.springherb.common.SearchVO;

public interface ReBoardService {
	public int insertReBoard(ReBoardVO vo);
	public List<ReBoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecordCount(SearchVO searchVo);
	public int updateReadCount(int no);
	public ReBoardVO selectByNo(int no);
	public boolean checkPwd(int no, String pwd);	
	public int updateReBoard(ReBoardVO vo);	
	public void deleteReBoard(Map<String, String> map);	
	public int updateDowncount(int no);
	public int reply(ReBoardVO vo);
	
/*	
	public List<ReBoardVO> mainNotice();
*/
	
}
