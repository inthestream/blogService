package com.yun.blogService.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.yun.blogService.domain.WebBoard;
import com.yun.blogService.persistence.WebBoardRepository;
import com.yun.blogService.service.WebBoardService;
import com.yun.blogService.vo.PageVO;

@Service
public class WebBoardServiceImpl implements WebBoardService {

	WebBoardRepository repo;
	
	@Autowired
	public void setWebBoardRepository(WebBoardRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Optional<WebBoard> findById(Long bno) {
		return repo.findById(bno);
		
	}

	@Override
	public Page<WebBoard> findAll(PageVO vo) {
		return repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), vo.makePageable(vo.getPrimarykeyColumn()));
	}
	
	public void save(WebBoard boardVO) {
		repo.save(boardVO);
	}

}
