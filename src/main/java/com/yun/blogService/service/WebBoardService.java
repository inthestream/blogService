package com.yun.blogService.service;

import org.springframework.data.domain.Page;

import com.yun.blogService.domain.WebBoard;
import com.yun.blogService.vo.PageVO;

public interface WebBoardService {

	public void findOne();
	
	public Page<WebBoard> findAll(PageVO vo);
	
	public void save(WebBoard boardVO);
}
