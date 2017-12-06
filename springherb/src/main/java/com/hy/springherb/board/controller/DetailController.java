package com.hy.springherb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hy.springherb.board.model.BoardService;
import com.hy.springherb.board.model.BoardVO;

@Controller
public class DetailController {
	private static final Logger logger
		=LoggerFactory.getLogger(DetailController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue="0") int no,
			Model model) {
		logger.info("조회수 증가, 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			return "common/message";
		}
		
		int cnt = boardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		
		return "redirect:/board/detail.do?no="+no;
	}
	
	@RequestMapping("/board/detail.do")
	public String detail(@RequestParam(defaultValue="0") int no, 
			ModelMap model) {
		logger.info("상세보기 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			return "common/message";
		}
		
		BoardVO vo =boardService.selectByNo(no);
		logger.info("상세보기 결과, vo={}", vo);
		
		String content=vo.getContent();
		if(content!=null && !content.isEmpty()) {
			content=content.replace("\r\n", "<br>");
			vo.setContent(content);
		}
		
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	
}











