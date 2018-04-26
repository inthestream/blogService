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
public class TestWebBoardRepository {

	@Autowired
	WebBoardRepository repo;
	
	Long FIRST_INDEX = 1L;
	Long NEXT_INDEX = 21L;
	int FIRST_PAGE = 0;
	int SECOND_PAGE = 1;
	int PAGE_SIZE = 20;
	String primaryColumn = "bno";
	
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
	public void testFindAll() {
		
		Page<WebBoard> result = setPageableAndFindAll(FIRST_PAGE, PAGE_SIZE, Direction.ASC, primaryColumn);
		assertEquals(result.getContent().get(0).getBno(), FIRST_INDEX);
		
		result = setPageableAndFindAll(SECOND_PAGE, PAGE_SIZE, Direction.ASC, primaryColumn);
		assertEquals(result.getContent().get(0).getBno(), NEXT_INDEX);
	}
	
	@Test
	public void testFindAllWithPredicate() {
		PAGE_SIZE = 5;
		String searchWord = "user10";
		Page<WebBoard> result = setPageableWithPredicateAndFindAll(FIRST_PAGE, PAGE_SIZE, Direction.ASC, primaryColumn
																		,"writer", searchWord);
		result.getContent().forEach(o -> {
			assertEquals(o.getWriter(), searchWord);
		});
	}
	
	@Test
	public void testSave() {
		WebBoard board = new WebBoard();
		board.setTitle("testTitle");
		board.setContent("testContent");
		board.setWriter("testWriter");
		
		Page<WebBoard> lastRow = setPageableAndFindAll(1, 1, Direction.DESC, primaryColumn);
		Long lastId = lastRow.getContent().get(0).getBno();
		
		repo.save(board);		
		
		Page<WebBoard> lastRowAfterSave = setPageableAndFindAll(1, 1, Direction.DESC, primaryColumn);
		Long lastIdAfterSave = lastRowAfterSave.getContent().get(0).getBno();
		
		lastId = lastId + 1L;
		
		assertEquals(lastId, lastIdAfterSave);
		
	}
	
	private Page<WebBoard> setPageableAndFindAll(int pageIndex
						, int pageSize, Direction direction, String columnName) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, direction, columnName);
		return repo.findAll(repo.makePredicate(null, null), pageable);
	}
	
	private Page<WebBoard> setPageableWithPredicateAndFindAll(int pageIndex
				, int pageSize, Direction direction, String columnName, String type, String keyword) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, direction, columnName);
		return repo.findAll(repo.makePredicate(type, keyword), pageable);
	}
	
	
}
