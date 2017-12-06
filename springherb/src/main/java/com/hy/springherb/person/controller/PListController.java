package com.hy.springherb.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.person.model.PersonDTO;
import com.hy.springherb.person.model.PersonService;

@Controller
public class PListController {
	@Autowired
	private PersonService personService;
	
	public PListController() {
		System.out.println("생성자 호출:PListController");
	}	

	@RequestMapping("/person/pList.do")
	public ModelAndView plist(@ModelAttribute PersonDTO dto) {
		//1.
		System.out.println("plist()-전화번호 목록, 파라미터 dto="+dto);
		
		//2.
		List<PersonDTO> list=null;
		
		list=personService.selectAll(dto);
		System.out.println("목록 조회 결과, list.size()="+list.size());
			
		//3.
		ModelAndView mav = new ModelAndView();
		mav.addObject("alist", list);
		mav.setViewName("person/pList");
		
		return mav;
	}
	
}












