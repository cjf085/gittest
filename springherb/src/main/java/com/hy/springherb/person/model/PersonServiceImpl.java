package com.hy.springherb.person.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDAO personDao;
	
	public PersonServiceImpl() {
		System.out.println("생성자 호출:PersonService");
	}	

	@Transactional
	public int insertPerson(PersonDTO dto){		
		return personDao.insertPerson(dto);		
	}
	
	public PersonDTO selectByNo(int no){
		return personDao.selectByNo(no);		
	}
	
	
	public List<PersonDTO>  selectAll(PersonDTO dto){
		return personDao.selectAll(dto);
	}
	
	
	public int updatePerson(PersonDTO dto){		
		return personDao.updatePerson(dto);
	}
	
	
	public int deletePerson(int no){
		return personDao.deletePerson(no);
	}
	
}
