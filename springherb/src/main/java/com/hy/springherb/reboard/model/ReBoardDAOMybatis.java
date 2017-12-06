package com.hy.springherb.reboard.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hy.springherb.common.SearchVO;

@Repository
public class ReBoardDAOMybatis extends SqlSessionDaoSupport 
	implements ReBoardDAO {
	
	private String namespace="config.mybatis.mapper.oracle.reBoard";
	
	public int insertReBoard(ReBoardVO vo) {
		return getSqlSession().insert(namespace+".insertReBoard", vo);
	}
	
	public List<ReBoardVO> selectAll(SearchVO searchVo) {
		return getSqlSession().selectList(namespace+".selectAll", 
				searchVo);
	}

	@Override
	public int selectTotalRecordCount(SearchVO searchVo) {
		return getSqlSession().selectOne(namespace
				+".selectTotalRecordCount", searchVo);
	}
	
	public int updateReadCount(int no){
		return getSqlSession().update(namespace+".updateReadCount", no);
	}
	
	public ReBoardVO selectByNo(int no) {
		return getSqlSession().selectOne(namespace+".selectByNo", no);
	}
	
	public String selectPwd(int no) {
		return getSqlSession().selectOne(namespace+".selectPwd", no);
	}
	
	public int updateReBoard(ReBoardVO vo){
		return getSqlSession().update(namespace+".updateReBoard", vo);				
	}
	
	
	public void deleteReBoard(Map<String, String> map){
		getSqlSession().delete(namespace+".deleteReBoard", map);
	}

	public int updateDowncount(int no) {
		return getSqlSession().update(namespace+".updateDowncount", no);
	}
	
	@Override
	public int updateSortNo(ReBoardVO vo) {		
		return getSqlSession().update(namespace+".updateSortNo", vo);
	}

	@Override
	public int replyReboard(ReBoardVO vo) {
		return getSqlSession().insert(namespace+".replyReboard", vo);
	}
	
	/*	
	public List<ReBoardVO> mainNotice() throws SQLException {
		//공지사항에서 최근글 6개만 조회
		select *
		from ( select * from reReBoard order by no desc )
		where rownum<=6
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<ReBoardVO> list=new ArrayList<>();
		try {
			conn=pool.getConnection();
			String sql="select * " 
				+ " from ( select * from reReBoard order by no desc)" 
				+ " where rownum<=6";
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				String title =rs.getString("title");
				int no=rs.getInt("no");
				
				ReBoardVO vo = new ReBoardVO();
				vo.setNo(no);
				vo.setTitle(title);
				
				list.add(vo);
			}
			System.out.println("메인 공지사항 조회 결과, list.size()="
					+list.size());
		}finally {
			pool.dbClose(rs, ps, conn);			
		}
		
		return list;
	}
	*/
}


















