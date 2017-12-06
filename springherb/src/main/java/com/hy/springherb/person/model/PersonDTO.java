package com.hy.springherb.person.model;

import java.sql.Timestamp;

/*
 DTO(Data Transfer Object)
 VO(Value Object)
 Bean
 => 데이터 전달 단위, 데이터를 표현하는 객체
    table의 컬럼들을 멤버변수로 갖는다
    DAO 클래스의 메서드에서 매개변수나 반환타입으로 사용됨
 => Beans 규약에 따라 private멤버변수, public getter/setter는 필수    
 */
public class PersonDTO {
	//private 멤버변수-필수
	private int no;
	private String name;
	private String tel;
	private Timestamp regdate;
	
	//생성자-선택
	public PersonDTO() {
		super();
	}

	public PersonDTO(int no, String name, String tel, Timestamp regdate) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.regdate = regdate;
	}

	//getter/setter - 필수	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	//toString() - 선택
	@Override
	public String toString() {
		return "PersonDTO [no=" + no + ", name=" + name + ", tel=" + tel + ", regdate=" + regdate + "]";
	}
		
}













