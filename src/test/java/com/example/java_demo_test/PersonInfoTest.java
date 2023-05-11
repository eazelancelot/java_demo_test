package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class PersonInfoTest {

	@Autowired
	private PersonInfoService personInfoService;
	
	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
	public void addPersonInfoTest() {
		List<PersonInfo> list = new ArrayList<>(Arrays.asList(new PersonInfo("A04", "A04_name", 20, "新北市"),
				new PersonInfo("A05", "A05_name", 20, "新北市")));
		PersonInfo a = new PersonInfo("A01", "A01_name", 36, "台南");
		PersonInfo b = new PersonInfo("A04", "A03_name", 20, "新北市");
		List<PersonInfo> list2 = new ArrayList<>();
		list2.add(a);
		list2.add(b);
		System.out.println(list2.contains(a));
		PersonInfoResponse response = personInfoService.addPersonInfo(list);
		System.out.println(response.getMessage());

	}

	@Test
	public void addPersonInfoListNullTest() {
		List<PersonInfo> list = null;
		PersonInfoResponse response = personInfoService.addPersonInfo(list);
		Assert.isTrue(response.getMessage().equalsIgnoreCase(RtnCode.DATA_ERROR.getMessage()), "Test error!!");
	}

	@Test
	public void addPersonInfoDataCheckTest() {
		List<PersonInfo> list = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", 20, "新北市")));
		List<PersonInfo> list1 = new ArrayList<>(Arrays.asList(new PersonInfo("", "A01_name", 20, "新北市")));
		PersonInfoResponse response = personInfoService.addPersonInfo(list1);
		Assert.isTrue(response.getMessage().equalsIgnoreCase(RtnCode.DATA_ERROR.getMessage()), "Test error!!");
		List<PersonInfo> list2 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "", 20, "新北市")));
		response = personInfoService.addPersonInfo(list2);
		List<PersonInfo> list3 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", -20, "新北市")));
		List<PersonInfo> list4 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", 20, "  ")));
	}
	
	@Test
	public void updateNameByIdTest() {
		int result = personInfoDao.updateNameById("A02", "A02_name");
		System.out.println(result);
	}
	
	@Test
	public void addInfoTest() {
		int result = personInfoDao.insertInfo("A08", "A08_name", 16, "NY");
		System.out.println(result);
	}
	
	@Test
	public void selectInfoTest() {
		PersonInfo result = personInfoDao.findByIdAndName("A08", "A08_name");
		System.out.println(result);
	}
	
	@Test
	public void selectInfo2Test() {
		PersonInfo result = personInfoDao.findByIdAndName2("A08", "A08_name");
		System.out.println(result);
	}
	
	@Test
	public void joinTwoTablesTest() {
		List<Map<String, Object>> result = personInfoDao.joinTwoTables("A01");
		System.out.println(result);
	}
	
	@Test
	public void limitTest() {
		List<PersonInfo> res = personInfoDao.findAllByLimit(5);
		System.out.println(res.size());
	}
	
	@Test
	public void doQueryByAgeTest() {
		List<PersonInfo> res = personInfoDao.doQueryByAge(30);
		System.out.println(res.size());
	}
	
	@Test
	public void updateAgeByNameTest() {
		int res = personInfoDao.updateAgeByName(38, "A01_name");
		System.out.println(res);
	}
	
	@Test
	public void findByNameContainingOneOfTest() {
		List<PersonInfo> res = personInfoDao.findByNameContainingOneOf("北|中|南");
		System.out.println(res.size());
	}
	
	@Test
	public void findByNameOrCityTest() {
		List<PersonInfo> res = personInfoDao.findByNameContainingOrCityContaining("", "");
		List<PersonInfo> res1 = personInfoDao.findByNameContainingOrCityContaining(null, null);
		List<PersonInfo> res2 = personInfoDao.findByNameContainingOrCityContaining("A0", null);
		System.out.println(res.size());
		System.out.println(res1.size());
		System.out.println(res2.size());
	}
	
	@Test
	public void queryNothingTest() {
		String inputName = "";
		String inputCity = "";
		List<PersonInfo> res = personInfoService.getNothing(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "A0";
		inputCity = "";
		res = personInfoService.getNothing(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "";
		inputCity = "高";
		res = personInfoService.getNothing(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "A0";
		inputCity = "高";
		res = personInfoService.getNothing(inputName, inputCity);
		System.out.println(res.size());
	}
	
	@Test
	public void queryAllTest() {
		String inputName = "";
		String inputCity = "";
		List<PersonInfo> res = personInfoService.getAll(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "A0";
		inputCity = "";
		res = personInfoService.getAll(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "";
		inputCity = "高";
		res = personInfoService.getAll(inputName, inputCity);
		System.out.println(res.size());
		System.out.println("=========================");
		inputName = "A0";
		inputCity = "高";
		res = personInfoService.getAll(inputName, inputCity);
		System.out.println(res.size());
	}
	
	@Test
	public void test() {
		String name = null;
		String name1 = "null";
		System.out.println(name1.length());
		System.out.println(name.length());
	}
}
