package com.example.java_demo_test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.service.ifs.BookService;
import com.example.java_demo_test.vo.BookSearchRequest;
import com.example.java_demo_test.vo.BookSearchResponse;

public class BookServiceImpl implements BookService {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

	@Override
	public BookSearchResponse search(String name, String isbn, String author, boolean isConsumer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookSearchResponse search(BookSearchRequest request) {
//		String name = request.getName();
//		String isbn = request.getIsbn();
//		String author = request.getAuthor();
//		if(!StringUtils.hasText(name) && !StringUtils.hasText(isbn) && !StringUtils.hasText(author)) {
//			return new BookSearchResponse("parameters cannot empty!!");
//		}
		//            判斷式 ? 判斷式=true 時要給得值 : 判斷式=false 時要給得值
		String name = StringUtils.hasText(request.getName()) ? request.getName() : null;
		String isbn = StringUtils.hasText(request.getIsbn()) ? request.getIsbn() : null;
		String author = StringUtils.hasText(request.getAuthor()) ? request.getAuthor() : null;
		logger.info("search by .....");
		return null;
	}

}
