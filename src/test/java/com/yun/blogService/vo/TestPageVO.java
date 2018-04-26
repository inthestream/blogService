package com.yun.blogService.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPageVO {

	@Test
	public void testPageSizeBetweenDefaultSizeAndDefaultMaxSize() {
		final int DEFAULT_SIZE = 10;
		final int DEFAULT_MAX_SIZE = 50;
		
		PageVO pageVO = new PageVO();
		assertEquals(pageVO.getSize(), DEFAULT_SIZE);
		
		pageVO.setSize(DEFAULT_MAX_SIZE);
		assertEquals(pageVO.getSize(), DEFAULT_MAX_SIZE);
		
		//if page size is out of range, set default size
		pageVO.setSize(DEFAULT_MAX_SIZE + 10);
		assertEquals(pageVO.getSize(), DEFAULT_SIZE);
	}
}
