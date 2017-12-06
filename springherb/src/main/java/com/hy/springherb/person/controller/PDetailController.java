package com.hy.springherb.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.person.model.PersonDTO;
import com.hy.springherb.person.model.PersonService;

@Controller
public class PDetailController {
	@Autowired
	private PersonService personService;
	
	public PDetailController() {
		System.out.println("생성자 호출: PDetailController");
	}
	
	@RequestMapping("/person/pDetail.do")
	public ModelAndView pdetail(
		@RequestParam(value="no", defaultValue="0") int no) {
		//1.
		System.out.println("pdetail()-상세보기 파라미터 no="+no);
		
		ModelAndView mav = new ModelAndView();
		
		if(no==0) {
			mav.addObject("msg", "잘못된 url입니다.");
			mav.addObject("url", "/person/pList.do");
			mav.setViewName("common/message");
			
			return mav;
		}
		
		//2.
		PersonDTO dto =null;
		dto =personService.selectByNo(no);
		System.out.println("상세보기 결과, dto="+dto);
		
		//3.
		mav.addObject("dto", dto);
		mav.setViewName("person/pDetail");
		
		return mav;
	}
	
	
}










