package com.hy.springherb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hy.springherb.board.model.BoardService;
import com.hy.springherb.board.model.BoardVO;

@Controller
@RequestMapping("/board/edit.do")
public class EditController {
	private static final Logger logger
		=LoggerFactory.getLogger(EditController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue="0") int no, 
			ModelMap model) {
		logger.info("수정화면 조회 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			return "common/message";
		}
		
		BoardVO vo =boardService.selectByNo(no);
		logger.info("수정화면 조회 결과, vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "board/edit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String edit_post(@ModelAttribute BoardVO boardVo, 
			Model model) {
		logger.info("글수정 처리-파라미터, vo={}", boardVo);
		
		String msg="";
		String url="/board/edit.do?no="+boardVo.getNo();	
		if(boardService.checkPwd(boardVo.getNo(), boardVo.getPwd())) {
			int cnt =boardService.updateBoard(boardVo);
			logger.info("글수정 결과, cnt={}", cnt);
			
			if(cnt>0) {
				msg="글수정되었습니다.";
				url="/board/detail.do?no="+boardVo.getNo();
			}else {
				msg="글수정 실패";							
			}
		}else {
			msg="비밀번호가 일치하지 않습니다.";							
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";		
	}
	
	
}

