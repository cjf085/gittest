package com.hy.springherb.person.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOMybatis extends SqlSessionDaoSupport 
	implements PersonDAO{
	/*@Autowired
	private SqlSessionTemplate sqlSession;*/

	String namespace="com.mybatis.mapper.person";

	public PersonDAOMybatis() {
		System.out.println("생성자 호출:PersonDAO!!");
	}

	public int insertPerson(PersonDTO dto) {
		System.out.println("insert전 dto의 no="+dto.getNo());

		int cnt=getSqlSession().insert(namespace+".insertPerson", dto);
		System.out.println("insert후 key값:dto의 no="+dto.getNo());

		return cnt;	
	}

	public PersonDTO selectByNo(int no){
		PersonDTO dto 
		=getSqlSession().selectOne(namespace+".selectByNo", no);

		return dto;		
	}

	public List<PersonDTO>  selectAll(PersonDTO dto) {
		List<PersonDTO> list 
		=getSqlSession().selectList(namespace+".selectAll", dto);
		return list;				
	}

	public int updatePerson(PersonDTO dto){
		int cnt=getSqlSession().update(namespace+".updatePerson", dto);
		return cnt;				
	}

	public int deletePerson(int no){
		int cnt =getSqlSession().delete(namespace+".deletePerson", no);
		return cnt;
	}


}



















