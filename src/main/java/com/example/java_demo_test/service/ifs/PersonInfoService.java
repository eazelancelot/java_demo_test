package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {
	
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList);
	
	public GetPersonInfoResponse getPersonInfoById(String id);
	
	public GetPersonInfoResponse getAllPersonInfo();
	
	public GetPersonInfoResponse getPersonInfoByAge(int age);
	
	List<PersonInfo> getAll(String name, String city);
	
	List<PersonInfo> getNothing(String name, String city);

}
