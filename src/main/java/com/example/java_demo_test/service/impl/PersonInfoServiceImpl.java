package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.constants.RtnCode1;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.PersonInfo2Dao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

	@Autowired
	private PersonInfo2Dao personInfoDao;

	@Transactional
	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		logger.info("addPersonInfo service");
		// ���b: �ˬd�Ѽ�
		// 1. �ˬd List �O�_�� null �� ��
		if (CollectionUtils.isEmpty(personInfoList)) {
			return new PersonInfoResponse(RtnCode1.DATA_ERROR.getMessage());
		}
		// 2. �ˬd List �����C����T(PersonInfo)
		List<String> ids = new ArrayList<>();
		for (PersonInfo item : personInfoList) {
//			// �ˬd id
//			if(!StringUtils.hasText(item.getId())) {
//				return new PersonInfoResponse("�s�W��T���~!!");
//			}
//			// �ˬd name
//			if(!StringUtils.hasText(item.getName())) {
//				return new PersonInfoResponse("�s�W��T���~!!");
//			}
//			// �ˬd city
//			if(!StringUtils.hasText(item.getCity())) {
//				return new PersonInfoResponse("�s�W��T���~!!");
//			}
//			// �ˬd age
//			if (item.getAge() < 0) {
//				return new PersonInfoResponse("�s�W��T���~!!");
//			}
			// �ˬd id�Bname�Bcity�Bage
			if (!StringUtils.hasText(item.getId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCity()) || item.getAge() < 0) {
				return new PersonInfoResponse("�s�W��T���~!!");
			}
			// �`�� id
			ids.add(item.getId());
		}
		// �ˬd�n�s�W�� ids �O�_�w�s�b
		List<PersonInfo> res = personInfoDao.findAllById(ids);
		if (res.size() == personInfoList.size()) {
			return new PersonInfoResponse("�s�W��T�����w�s�b!!");
		}
		if (res.size() > 0) {
//			return new PersonInfoResponse("�s�W��T�w�s�b!!");
			List<String> resIds = new ArrayList<>();
			for (PersonInfo item : res) {
				resIds.add(item.getId());
			}
			List<PersonInfo> saveList = new ArrayList<>();
			for (PersonInfo item : personInfoList) {// A,B,C,D,E
				if (!resIds.contains(item.getId())) { // resIds=C,D
					saveList.add(item);
				}
			}
//			if(saveList.isEmpty()) {
//				return new PersonInfoResponse("�s�W��T�����w�s�b!!");
//			}
//			List<PersonInfo> saveList2 = personInfoList.stream()
//					.filter(item -> !resIds.contains(item.getId()))
//					.collect(Collectors.toList());
			// saveList=A,B,E
			personInfoDao.saveAll(saveList);
			return new PersonInfoResponse(saveList, RtnCode1.SUCCESSFUL.getMessage());
		}
		// saveAll
		personInfoDao.saveAll(personInfoList);
		return new PersonInfoResponse(personInfoList, RtnCode1.SUCCESSFUL.getMessage());
	}

	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		// �ˬd
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		Optional<PersonInfo> op = personInfoDao.findById(id);
		if (!op.isPresent()) {
			return new GetPersonInfoResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		return new GetPersonInfoResponse(op.get(), RtnCode1.SUCCESSFUL.getMessage());
	}

	@Override
	public GetPersonInfoResponse getAllPersonInfo() {
		return new GetPersonInfoResponse(personInfoDao.findAll(), RtnCode1.SUCCESSFUL.getMessage());
	}

	@Override
	public GetPersonInfoResponse getPersonInfoByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonInfo> getAll(String name, String city) {
		boolean hasText = !StringUtils.hasText(name) && !StringUtils.hasText(city);
		String inputName = hasText ? "." : StringUtils.hasText(name) ? name : null;
		String inputCity = hasText ? "." : StringUtils.hasText(city) ? city : null;
		List<PersonInfo> res = personInfoDao.findByNameOrCityContainingOneOf(inputName, inputCity);
		return res;
	}

	@Override
	public List<PersonInfo> getNothing(String name, String city) {
		String inputName = StringUtils.hasText(name) ? name : null;
		String inputCity = StringUtils.hasText(city) ? city : null;
		List<PersonInfo> res = personInfoDao.findByNameOrCityContainingOneOf(inputName, inputCity);
		return res;
	}

}
