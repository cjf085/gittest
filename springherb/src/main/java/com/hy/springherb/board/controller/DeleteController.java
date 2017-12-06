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
public class DeleteController {
	private static final Logger logger
	=LoggerFactory.getLogger(DeleteController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/delete.do", method=RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no,
			ModelMap model) {
		logger.info("삭제 화면 파라미터, no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			return "common/message";
		}
		
		return "board/delete";
	}
	
	@RequestMapping(value="/board/delete.do", method=RequestMethod.POST)
	public String delete_post(@ModelAttribute BoardVO vo, Model model) {
		logger.info("삭제처리 파라미터 vo={}", vo);
		
		String msg="";
		String url="/board/delete.do?no="+vo.getNo();
		if(boardService.checkPwd(vo.getNo(), vo.getPwd())) {
			int cnt=boardService.deleteBoard(vo.getNo());
			if(cnt>0) {
				msg="삭제되었습니다.";
				url="/board/list.do";
			}else {
				msg="삭제 실패";
			}			
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";		
	}
	
	
}










