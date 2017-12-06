package com.hy.springherb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hy.springherb.board.model.BoardService;
import com.hy.springherb.board.model.BoardVO;

@Controller
public class WriteController {
	private static final Logger logger
		= LoggerFactory.getLogger(WriteController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/write.do", method=RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면 보여주기");
		
		return "board/write";
	}
	
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
	public String write_post(@ModelAttribute BoardVO boardVo, 
			Model model) {
		logger.info("글쓰기 처리-파라미터, vo={}", boardVo);
		
		int cnt =boardService.insertBoard(boardVo);
		logger.info("글쓰기 결과, cnt={}", cnt);
		
		String msg="", url="";
		if(cnt>0) {
			msg="글쓰기 처리되었습니다.";
			url="/board/list.do";
		}else {
			msg="글쓰기 실패";
			url="/board/write.do";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";		
	}
	
	
	
	
}






















