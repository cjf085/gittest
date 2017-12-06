package com.hy.springherb.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.person.model.PersonDTO;
import com.hy.springherb.person.model.PersonService;

@Controller
@RequestMapping("/person/pEdit.do")
public class PEditController {
	@Autowired
	private PersonService personService;
	
	public PEditController() {
		System.out.println("생성자 호출 : PEditController");
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pedit_get(
		@RequestParam(value="no", defaultValue="0") int no) {
		//1.
		System.out.println("pedit_get():수정화면, 파라미터 no="+no);
		
		//2.
		PersonDTO dto=null;
		dto=personService.selectByNo(no);
		System.out.println("수정화면-조회 결과, dto="+dto);
				
		//3.
		ModelAndView mav = new ModelAndView("person/pEdit");
		mav.addObject("dto", dto);
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView pedit_post(@ModelAttribute PersonDTO dto) {
		System.out.println("pedit_post()-수정처리, 파라미터 dto="+dto);
		
		String msg="수정실패", url="/person/pEdit.do?no="+dto.getNo();
		int cnt=personService.updatePerson(dto);
		System.out.println("수정처리 결과, cnt="+cnt);
		
		if(cnt>0) {
			msg="수정성공";
			url="/person/pDetail.do?no="+dto.getNo();
		}		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		
		return mav;
	}
	
	
}








