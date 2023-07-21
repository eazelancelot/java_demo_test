package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@RestController
public class PersonInfoController {
	
	@Autowired
	private PersonInfoService personInfoService;
	
	@PostMapping(value = "add_person_info")
	public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		return personInfoService.addPersonInfo(request.getPersonInfoList());
	}
	
	@PostMapping(value = "get_person_info_by_id")
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) throws Exception {
		return personInfoService.getPersonInfoById(request.getId());
	}
	
	@PostMapping(value = "get_person_info_by_age")
	public GetPersonInfoResponse getPersonInfoByAge(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoByAge(request.getAge());
	}
	
	@PostMapping(value = "get_all_person_info")
	public GetPersonInfoResponse getAllPersonInfo() {
		return personInfoService.getAllPersonInfo();
	}

}
