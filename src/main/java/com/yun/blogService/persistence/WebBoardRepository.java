package com.yun.blogService.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.yun.blogService.domain.QWebBoard;
import com.yun.blogService.domain.WebBoard;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {
	
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QWebBoard board = QWebBoard.webBoard;
		
		builder.and(board.bno.gt(0));
		
		if(type == null) {
			return builder;
		}
		
		switch(type) {
		
		case "title":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		
		case "content":
			builder.and(board.content.like("%" + keyword + "%"));
			break;
			
		case "writer":
			builder.and(board.writer.like("%" + keyword + "%"));
			break;
		}
		
		return builder;
		
	}

}
