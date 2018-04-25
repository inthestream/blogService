package com.yun.blogService.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.yun.blogService.domain.WebBoard;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class TestWebBoardRepository {

	@Autowired
	WebBoardRepository repo;
	
	Long FIRST_INDEX = 1L;
	Long NEXT_INDEX = 21L;
	int FIRST_PAGE = 0;
	int SECOND_PAGE = 1;
	int PAGE_SIZE = 20;
	
	/*@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i -> {
			WebBoard board = new WebBoard();
			
			board.setTitle("Sample Board Title " + i);
			board.setContent("Content Sample ..." + i + " of Board");
			board.setWriter("user0" + (i % 10));
			
			repo.save(board);
		});
	}*/
	
	@Test
	public void testFindAllMethod() {
		Page<WebBoard> result = setPageableAndFindAllMethod(FIRST_PAGE, PAGE_SIZE, Direction.ASC, "bno");
		assertEquals(result.getContent().get(0).getBno(), FIRST_INDEX);
		
		result = setPageableAndFindAllMethod(SECOND_PAGE, PAGE_SIZE, Direction.ASC, "bno");
		assertEquals(result.getContent().get(0).getBno(), NEXT_INDEX);
	}
	
	@Test
	public void testFindAllWithPredicate() {
		PAGE_SIZE = 5;
		
		Page<WebBoard> result = setPageableWithPredicateAndFindAllMethod(FIRST_PAGE, PAGE_SIZE, Direction.ASC, "bno"
																		,"writer", "user10");
		
		result.getContent().forEach(o -> {
			assertEquals(o.getWriter(), "user10");
		});
		
	}
	
	private Page<WebBoard> setPageableAndFindAllMethod(int pageIndex
						, int pageSize, Direction direction, String columnName) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, direction, columnName);
		return repo.findAll(repo.makePredicate(null, null), pageable);
	}
	
	private Page<WebBoard> setPageableWithPredicateAndFindAllMethod(int pageIndex
				, int pageSize, Direction direction, String columnName, String type, String keyword) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, direction, columnName);
		return repo.findAll(repo.makePredicate(type, keyword), pageable);
	}
	
	
}
