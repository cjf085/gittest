package com.hy.springherb.board.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hy.springherb.common.SearchVO;
import com.hy.springherb.reboard.model.ReBoardVO;

@Repository
public class BoardDAOMybatis extends SqlSessionDaoSupport 
	implements BoardDAO {
	
	private String namespace="config.mybatis.mapper.oracle.board";
	
	public int insertBoard(BoardVO vo) {
		return getSqlSession().insert(namespace+".insertBoard", vo);
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo) {
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
	
	public BoardVO selectByNo(int no) {
		return getSqlSession().selectOne(namespace+".selectByNo", no);
	}
	
	public String selectPwd(int no) {
		return getSqlSession().selectOne(namespace+".selectPwd", no);
	}
	
	public int updateBoard(BoardVO vo){
		return getSqlSession().update(namespace+".updateBoard", vo);				
	}
	
	
	public int deleteBoard(int no){
		return getSqlSession().delete(namespace+".deleteBoard", no);
	}

	
	/*	
	public List<BoardVO> mainNotice() throws SQLException {
		//공지사항에서 최근글 6개만 조회
		select *
		from ( select * from board order by no desc )
		where rownum<=6
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<BoardVO> list=new ArrayList<>();
		try {
			conn=pool.getConnection();
			String sql="select * " 
				+ " from ( select * from board order by no desc)" 
				+ " where rownum<=6";
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				String title =rs.getString("title");
				int no=rs.getInt("no");
				
				BoardVO vo = new BoardVO();
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


















