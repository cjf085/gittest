package com.hy.springherb.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.person.model.PersonDTO;
import com.hy.springherb.person.model.PersonService;

@Controller
public class PWriteController {
	@Autowired
	private PersonService personService;
	
	public PWriteController() {
		System.out.println("생성자 호출: PWriteController");
	}
	
	@RequestMapping(value="/person/pWrite.do", method=RequestMethod.GET)
	public ModelAndView pwrite_get() {
		//전화번호 등록 화면 보여주기
		
		//1.
		System.out.println("pwrite_get()-등록화면 보여주는 메서드 호출");
		
		//2.		
		//3.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/pWrite");
		
		return mav;
	}
	
	@RequestMapping(value="/person/pWrite.do", 
			method=RequestMethod.POST)	
	public ModelAndView pwrite_post(@ModelAttribute PersonDTO personDto) {
		//1. 요청 파라미터 출력
		System.out.println("pwrite_post()-등록처리: 파라미터 dto="+personDto);
		
		//2. db 작업
		String msg="등록 실패", url="/person/pWrite.do";
		int cnt=personService.insertPerson(personDto);
		System.out.println("등록 처리 결과, cnt="+cnt);
		
		if(cnt>0) {
			msg="등록 성공";
			url="/person/pList.do";
		}		
		
		//3. 결과, 뷰페이지 저장/리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		
		return mav;
	}
	
}












