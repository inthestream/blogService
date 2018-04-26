package com.yun.blogService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yun.blogService.domain.WebBoard;
import com.yun.blogService.service.WebBoardService;
import com.yun.blogService.vo.PageMaker;
import com.yun.blogService.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

	public WebBoardService webBoardService;
	
	@Autowired
	private void setWebBoardService(WebBoardService webBoardService) {
		this.webBoardService = webBoardService;
	}
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model){
		vo.setDir(Direction.DESC);
		vo.setPrimarykeyColumn("bno");
		
		Page<WebBoard> result = webBoardService.findAll(vo);
		
		model.addAttribute("result", new PageMaker<WebBoard>(result));
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") WebBoard vo) {
		
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard boardVO, RedirectAttributes rttr) throws Exception {
		webBoardService.save(boardVO);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/boards/list";
	}
}
