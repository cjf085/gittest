package com.hy.springherb.reboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.hy.springherb.common.FileuploadUtil;
import com.hy.springherb.common.PaginationInfo;
import com.hy.springherb.common.SearchVO;
import com.hy.springherb.common.Utility;
import com.hy.springherb.reboard.model.ReBoardService;
import com.hy.springherb.reboard.model.ReBoardVO;

@Controller
public class ReBoardController {
	private static final Logger logger
	= LoggerFactory.getLogger(ReBoardController.class);

	@Autowired
	private ReBoardService reBoardService;
	
	@Autowired
	private FileuploadUtil fileUtil;
	
	@RequestMapping(value="/reBoard/write.do", method=RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면 보여주기");
		
		return "reBoard/write";
	}
	
	@RequestMapping(value="/reBoard/write.do", method=RequestMethod.POST)
	public String write_post(HttpServletRequest request, 
			@ModelAttribute ReBoardVO reBoardVo, 
			Model model) {
		logger.info("글쓰기 처리-파라미터, vo={}", reBoardVo);
		
		//파일 업로드 처리
		List<Map<String, Object>> list = null;
		String fileName="", originaFileName="";
		long fileSize=0;
		try {
			list = fileUtil.fileupload(request);
			
			//업로드 한 경우에만 파일정보 --- 여러개 처리할 경우 여기서 수정 배열 사용
			if(list!=null && !list.isEmpty()) {
				for(Map<String, Object> map : list) {
					fileName = (String) map.get("filename");
					originaFileName = (String) map.get("originalFilename");
					fileSize = (Long) map.get("filesize");							
				} //for
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//db작업
		reBoardVo.setFileName(fileName);
		reBoardVo.setOriginalFileName(originaFileName);
		reBoardVo.setFileSize(fileSize);
		
		int cnt =reBoardService.insertReBoard(reBoardVo);
		logger.info("글쓰기 결과, cnt={}", cnt);
		
		String msg="", url="";
		if(cnt>0) {
			msg="글쓰기 처리되었습니다.";
			url="/reBoard/list.do";
		}else {
			msg="글쓰기 실패";
			url="/reBoard/write.do";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";		
	}
	
	@RequestMapping("/reBoard/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록, 파라미터 searchVo={}", searchVo);
		
		//Paging 처리에 필요한 변수를 계산해주는 PaginationInfo 생성
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT_PER_PAGE);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
				
		//SearchVo에 값 셋팅
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT_PER_PAGE);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("searchVo 최종값 : {}", searchVo);
		
		List<ReBoardVO> list =reBoardService.selectAll(searchVo);
		logger.info("글목록 결과, list.size()={}", list.size());
		
		int totalRecord = reBoardService.selectTotalRecordCount(searchVo);
		logger.info("글 전체 개수 조회 결과, totalRecord={}", totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "reBoard/list";		
	}

	
	@RequestMapping("/reBoard/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue="0") int no,
			Model model) {
		logger.info("조회수 증가, 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			return "common/message";
		}
		
		int cnt = reBoardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		
		return "redirect:/reBoard/detail.do?no="+no;
	}
	
	@RequestMapping("/reBoard/detail.do")
	public String detail(HttpServletRequest request, @RequestParam(defaultValue="0") int no, 
			ModelMap model) {
		logger.info("상세보기 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			return "common/message";
		}
		
		ReBoardVO vo =reBoardService.selectByNo(no);
		logger.info("상세보기 결과, vo={}", vo);
		
		String content=vo.getContent();
		if(content!=null && !content.isEmpty()) {
			content=content.replace("\r\n", "<br>");
			vo.setContent(content);
		}

		String fileInfo="", downInfo="";
		String fileName=vo.getFileName();
		double fileSize=0;
		if(fileName!=null && !fileName.isEmpty()) {
			fileInfo = Utility.gerFileInfo(vo.getOriginalFileName(), fileSize, request);	
			
			downInfo="다운 : "+vo.getDownCount();
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("fileInfo", fileInfo);
		model.addAttribute("downInfo", downInfo);
		
		return "reBoard/detail";
	}
	
	@RequestMapping(value="/reBoard/edit.do", method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue="0") int no, 
			ModelMap model, HttpServletRequest request) {
		logger.info("수정화면 조회 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			return "common/message";
		}
		
		ReBoardVO vo =reBoardService.selectByNo(no);
		logger.info("수정화면 조회 결과, vo={}", vo);
		
		String fileInfo = "";
		if(vo.getFileName()!=null && !vo.getFileName().isEmpty()) {
			fileInfo = Utility.gerFileInfo(vo.getOriginalFileName(), vo.getFileSize(), request);
		}
		
		model.addAttribute("fileInfo",fileInfo);
		model.addAttribute("vo", vo);
		
		return "reBoard/edit";
	}
	
	@RequestMapping(value="/reBoard/edit.do", method=RequestMethod.POST)
	public String edit_post(@ModelAttribute ReBoardVO reBoardVo, @RequestParam String oldFileName, 
			HttpServletRequest request, Model model) {
		logger.info("글수정 처리-파라미터, vo={}, oldFileName={}", reBoardVo, oldFileName);
		
		//파일 업로드 처리
		List<Map<String, Object>> fileList = null;
		String fileName="", originaFileName="";
		long fileSize=0;
		
		//db작업
		String msg="";
		String url="/reBoard/edit.do?no="+reBoardVo.getNo();	
		if(reBoardService.checkPwd(reBoardVo.getNo(), reBoardVo.getPwd())) {
			try {
				fileList = fileUtil.fileupload(request);
				
				for(Map<String, Object> map : fileList) {
					fileName = (String) map.get("filename");
					originaFileName = (String) map.get("originalFilename");
					fileSize = (Long) map.get("filesize");	
					
					reBoardVo.setOriginalFileName(originaFileName);
					reBoardVo.setFileName(fileName);
					reBoardVo.setFileSize(fileSize);
				} //for
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			int cnt =reBoardService.updateReBoard(reBoardVo);
			logger.info("글수정 결과, cnt={}", cnt);
			
			if(cnt>0) {
				msg="글수정되었습니다.";
				url="/reBoard/detail.do?no="+reBoardVo.getNo();
				
				//파일을 새로 첨부한 경우, 기존파일이 존재한다면 삭제
				if(fileName!=null && !fileName.isEmpty()) {
					if(oldFileName!=null && !oldFileName.isEmpty()) {
						File delFile = new File(fileUtil.getUploadPath(request),oldFileName);
						if(delFile.exists()) {
							boolean bool = delFile.delete();
							logger.info("기존 파일 삭제여부 bool={}",bool);								
						}
					}
				}
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
	
	@RequestMapping(value="/reBoard/delete.do", method=RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no,
			@RequestParam(defaultValue="0") int groupNo,
			@RequestParam(defaultValue="0") int step,
			@RequestParam String fileName,			
			ModelMap model) {
		logger.info("삭제 화면 파라미터, no={}, groupNo={}",	no, groupNo);
		logger.info("step={}, fileName={}",step, fileName);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			return "common/message";
		}
		
		return "reBoard/delete";
	}
	
	@RequestMapping(value="/reBoard/delete.do", method=RequestMethod.POST)
	public String delete_post(@ModelAttribute ReBoardVO vo, HttpServletRequest request, Model model) {
		logger.info("삭제처리 파라미터 vo={}", vo);
		
		String msg="";
		String url="/reBoard/delete.do?no="+vo.getNo()+"&groupNo="+vo.getGroupNo()+"&step="+vo.getStep()+"&fileName="+vo.getFileName();
		if(reBoardService.checkPwd(vo.getNo(), vo.getPwd())) {
			//프로시저에서 사용할 파라미터를 map에 저장
			Map<String, String> map = new HashMap<String, String>();
			map.put("no", vo.getNo()+"");
			map.put("groupNo", vo.getGroupNo()+"");
			map.put("step", vo.getStep()+"");
			
			reBoardService.deleteReBoard(map);
			
			//기존 파일이 첨부된 경우 파일 삭제
			if(vo.getFileName()!=null && !vo.getFileName().isEmpty()) {
				String path = fileUtil.getUploadPath(request);
				File file = new File(path, vo.getFileName());
				if(file.exists()) {
					boolean bool = file.delete();
					logger.info("기존파일 삭제여부:{}", bool);
				}
			}
				
			msg="삭제되었습니다.";
			url="/reBoard/list.do";
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";		
	}
	
	//파일다운로드
	@RequestMapping("/reBoard/download.do")
	public ModelAndView download(@RequestParam(defaultValue="0") int no, @RequestParam String fileName, HttpServletRequest request) {
		logger.info("다운로드수 증가, 파라미터 no={}, fileName={}",no,fileName);
		
		//다운로드수 증가시키기
		int cnt = reBoardService.updateDowncount(no);
		logger.info("다운로드수 증가, 결과 cnt={}", cnt);
		
		//map에 다운로드할 파일 객체를 저장해서 ModelAndView에 저장한 후 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		
		String path = fileUtil.getUploadPath(request);
		File file = new File(path,fileName);
		
		map.put("myfile", file);
		//ModelAndView(String viewName, Map<String, ?> model)
		ModelAndView mav = new ModelAndView("downloadView", map);
		
		return mav;
	}
	
	@RequestMapping(value="/reBoard/reply.do",method=RequestMethod.GET)
	public String reply_get(@RequestParam(defaultValue="0") int no, ModelMap model) {
		logger.info("답변하기 화면 보여주기, 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg","잘못된 url입니다.");
			model.addAttribute("url","reBoard/list.do");
			return "common/message";
		}
		
		ReBoardVO vo = reBoardService.selectByNo(no);
		logger.info("답변하기 조회 결과, vo={}",vo);
		
		model.addAttribute("vo",vo);
		
		return "reBoard/reply";
	}

	@RequestMapping(value="/reBoard/reply.do",method=RequestMethod.POST)
	public String reply_post(@ModelAttribute ReBoardVO vo, Model model) {
		logger.info("답변하기 처리, 파라미터 vo={}", vo);
		
		int cnt = reBoardService.reply(vo);
		logger.info("답변하기 처리, 결과 cnt={}", cnt);
		
		String msg="", url="";
		if(cnt>0) {
			msg = "답변하기 성공";
			url = "/reBoard/list.do";
		} else {
			msg = "답변하기 실패";
			url = "/reBoard/reply.do?no="+vo.getNo();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}
