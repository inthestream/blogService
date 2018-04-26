package com.yun.blogService.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.yun.blogService.domain.WebBoard;
import com.yun.blogService.vo.PageVO;

public interface WebBoardService {

	public Optional<WebBoard> findById(Long bno);
	
	public Page<WebBoard> findAll(PageVO vo);
	
	public void save(WebBoard boardVO);
}
