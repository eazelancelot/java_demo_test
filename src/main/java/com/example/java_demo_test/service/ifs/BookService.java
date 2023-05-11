package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.BookSearchRequest;
import com.example.java_demo_test.vo.BookSearchResponse;

public interface BookService {
	
	public BookSearchResponse search(String name, String isbn, String author, boolean isConsumer);
	
	public BookSearchResponse search(BookSearchRequest request);
	
//	public List<? extends BookSearchBaseResponse> searchA(BookSearchRequest request);
//	
//	public <T extends BookSearchBaseResponse> T searchB(BookSearchRequest request);

}
