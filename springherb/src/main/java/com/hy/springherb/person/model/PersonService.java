package com.hy.springherb.person.model;

import java.util.List;

public interface PersonService {
	public int insertPerson(PersonDTO dto);
	public PersonDTO selectByNo(int no);
	public List<PersonDTO>  selectAll(PersonDTO dto);	
	public int updatePerson(PersonDTO dto);
	public int deletePerson(int no);
	
}
