package com.hy.springherb.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.person.model.PersonService;

@Controller
public class PDeleteController {
	@Autowired
	private PersonService personService;
	
	public PDeleteController() {
		System.out.println("생성자 호출: PDeleteController");
	}	

	@RequestMapping("/person/pDelete.do")
	public ModelAndView pdelete(
		@RequestParam(value="no", defaultValue="0") int no) {
		//1.
		System.out.println("pdelete()-삭제 처리, 파라미터 no="+no);
		
		//2.		
		int cnt=personService.deletePerson(no);	
		System.out.println("삭제 처리 결과, cnt="+cnt);
		
		//3.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/person/pList.do");
		
		return mav;
	}
	
	
	
}








