package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode1;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.PersonInfo2Dao;
import com.example.java_demo_test.respository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoBankVo;
import com.example.java_demo_test.vo.PersonInfoResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class PersonInfoTest {

	@Autowired
	private PersonInfoService personInfoService;
	
	@Autowired
	private PersonInfo2Dao personInfo2Dao;
	
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
		Assert.isTrue(response.getMessage().equalsIgnoreCase(RtnCode1.DATA_ERROR.getMessage()), "Test error!!");
	}

	@Test
	public void addPersonInfoDataCheckTest() {
		List<PersonInfo> list = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", 20, "新北市")));
		List<PersonInfo> list1 = new ArrayList<>(Arrays.asList(new PersonInfo("", "A01_name", 20, "新北市")));
		PersonInfoResponse response = personInfoService.addPersonInfo(list1);
		Assert.isTrue(response.getMessage().equalsIgnoreCase(RtnCode1.DATA_ERROR.getMessage()), "Test error!!");
		List<PersonInfo> list2 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "", 20, "新北市")));
		response = personInfoService.addPersonInfo(list2);
		List<PersonInfo> list3 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", -20, "新北市")));
		List<PersonInfo> list4 = new ArrayList<>(Arrays.asList(new PersonInfo("A01", "A01_name", 20, "  ")));
	}
	
	@Test
	public void updateNameByIdTest() {
		int result = personInfo2Dao.updateNameById("A02", "A02_name");
		System.out.println(result);
	}
	
	@Test
	public void addInfoTest() {
		int result = personInfo2Dao.insertInfo("A08", "A08_name", 16, "NY");
		System.out.println(result);
	}
	
	@Test
	public void addInfoTest3() {
		int result = personInfo2Dao.insertInfo3("A08", "A08_name", 16, "NY");
		System.out.println(result);
	}
	
	@Test
	public void selectInfoTest() {
		PersonInfo result = personInfo2Dao.findByIdAndName("A08", "A08_name");
		System.out.println(result);
	}
	
	@Test
	public void selectInfo2Test() {
		PersonInfo result = personInfo2Dao.findByIdAndName2("A08", "A08_name");
		System.out.println(result);
	}
	
//	@Test
//	public void joinTwoTablesTest() {
//		List<Map<String, Object>> result = personInfo2Dao.joinTwoTables("A01");
//		System.out.println(result);
//	}
	
	@Test
	public void limitTest() {
		List<PersonInfo> res = personInfo2Dao.findAllByLimit(5);
		System.out.println(res.size());
	}
	
	@Test
	public void doQueryByAgeTest() {
		List<PersonInfo> res = personInfo2Dao.doQueryByAge(30);
		System.out.println(res.size());
	}
	
	@Test
	public void updateAgeByNameTest() {
		int res = personInfo2Dao.updateAgeByName(38, "A01_name");
		System.out.println(res);
	}
	
	@Test
	public void findByNameContainingOneOfTest() {
		List<PersonInfo> res = personInfo2Dao.findByNameContainingOneOf("北|中|南");
		System.out.println(res.size());
	}
	
	@Test
	public void findByNameOrCityTest() {
		List<PersonInfo> res = personInfo2Dao.findByNameContainingOrCityContaining("", "");
		List<PersonInfo> res1 = personInfo2Dao.findByNameContainingOrCityContaining(null, null);
		List<PersonInfo> res2 = personInfo2Dao.findByNameContainingOrCityContaining("A0", null);
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
	
	@Test
	public void insertInfoTest() {
		int res = personInfoDao.insertInfo("A99", "A99_name", 25, "台南");
		System.out.println(res);
	}
	
	@Test
	public void insertInfo2Test() {
		int res = personInfoDao.insertInfo2("A98", "A98_name", 25, "台南");
		System.out.println(res);
	}
	
	@Test
	public void insertInfo3Test() {
		int res = personInfoDao.insertInfo3("A97", "A97_name", 25, "台南");
		System.out.println(res);
	}
	
	@Test
	public void insertInfo4Test() {
		int res = personInfoDao.insertInfo4(1003);
		System.out.println(res);
	}
	
	@Test
	public void insertInfo7Test() {
		int id = 1004;
		int res = personInfoDao.insertInfo7(id, String.valueOf(id));
		System.out.println(res);
	}
	//===============================
	@Test
	public void updateTest() {
		int res = personInfoDao.updateNameById("王小二", "1003");
		System.out.println(res);
	}
	
	@Test
	public void nativeUpdateTest() {
		int res = personInfoDao.nativeUpdateNameById("王小三", "1003");
		System.out.println(res);
	}
	
	@Test
	public void selectTest() {
		PersonInfo res = personInfoDao.findByIdAndName("1003", "王小三");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAge());
		System.out.println(res.getCity());
	}
	
	@Test
	public void select0Test() {
		PersonInfo res = personInfoDao.findByIdAndName0("1003", "王小三");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAge());
		System.out.println(res.getCity());
	}
	
	@Test
	public void select1Test() {
		PersonInfo res = personInfoDao.findByIdAndName1("1003", "王小三");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAge());
		System.out.println(res.getCity());
	}
	
	@Test
	public void select2Test() {
		PersonInfo res = personInfoDao.findByIdAndName2("1003", "王小三");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAge());
		System.out.println(res.getCity());
	}
	
	@Test
	public void select3Test() {
		PersonInfo res = personInfoDao.findByIdAndName3("1003", "王小三");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAge());
		System.out.println(res.getCity());
	}
	
	@Test
	public void select4Test() {
		List<Map<String, Object>> res = personInfoDao.findByIdAndName4("1003", "王小三");
		for(Map<String, Object> item: res) {
			for(Entry<String, Object> map: item.entrySet()) {
				System.out.println("key: " + map.getKey());
				System.out.println("value: " + map.getValue());
			}
		}
	}
	
	@Test
	public void selectLimitTest() {
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThanLimit(18, 5);
		System.out.println(res.size());
		for(PersonInfo item : res) {
			System.out.println(item.getAge());
		}
	}
	
	@Test
	public void selectLimit2Test() {
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThanLimit2(18, 1, 5);
		System.out.println(res.size());
		for(PersonInfo item : res) {
			System.out.println(item.getAge());
		}
	}
	
	@Test
	public void selectLimit3Test() {
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThanLimit3(18, 1, 5);
		System.out.println(res.size());
		for(PersonInfo item : res) {
			System.out.println(item.getAge());
		}
	}
	
	@Test
	public void selectRegexpTest() {
		List<String> list = new ArrayList<>(Arrays.asList("北", "中", "南"));
		String regexp = String.join("|", list); // "北|中|南"
		List<PersonInfo> res = personInfoDao.findByCityRegexp(regexp);
		for(PersonInfo item : res) {
			System.out.println(item.getId());
			System.out.println(item.getCity());
		}
	}
	
	@Test
	public void selectRegexp1Test() {
		List<String> cityList = new ArrayList<>(Arrays.asList("北", "中", "南"));
		List<String> nameList = new ArrayList<>(Arrays.asList("A0"));
		String regexpCity = String.join("|", cityList);
		String regexpName = String.join("|", nameList);
		List<PersonInfo> res = personInfoDao.findByCityOrNameRegexp(regexpCity, regexpName);
		for(PersonInfo item : res) {
			System.out.println(item.getId());
			System.out.println(item.getCity());
		}
	}
	
	@Test
	public void joinTwoTablesTest() {
		List<Map<String, Object>> res = personInfoDao.joinTwoTables();
		for(Map<String, Object> list : res) {
			for(Entry<String, Object> map : list.entrySet()) {
				System.out.println("column: " + map.getKey());
				System.out.println("value: " + map.getValue());
			}
		}
	}
	
	@Test
	public void joinTwoTables3Test() {
		PersonInfoBankVo res = personInfoDao.joinTwoTables3("A01");
		System.out.println(res.getId());
		System.out.println(res.getName());
		System.out.println(res.getAmount());
	}
	
	@Test
	public void containingTest() {
		String inputId = "A0";
		String inputName = "A0";
		String inputCity = "台";
		int age = 20;
		
		String id = StringUtils.hasText(inputId) ? inputId : "";
		String name = StringUtils.hasText(inputName) ? inputName : "";
		String city = StringUtils.hasText(inputCity) ? inputCity : "";
		List<PersonInfo> res = personInfoDao.findByNameContainingAndCityContainingAndAgeGreaterThanEqual(name, city, age);
		System.out.println(res.size());
		for(PersonInfo item : res) {
			System.out.println(item.getId());
		}
	}
	
	@Test
	public void findAllTest() {
		Page<PersonInfo> res = personInfoDao.findAll(PageRequest.of(0, 4));
		for(PersonInfo item : res) {
			System.out.println(item.getId());
		}
	}
	
	@Test
	public void findByCityRegexpPagingTest() {
		Page<PersonInfo> res = personInfoDao.findByCityRegexpPaging("北|中|南", PageRequest.of(0, 4));
		for(PersonInfo item : res) {
			System.out.println(item.getId());
		}
	}
	
	@Test
	public void batchUpdateTest() {
//		int res = personInfoDao.updateCity("台南", "台南市");
		int res = personInfoDao.updateCity("台南市", "台南");
//		int res = personInfoDao.batchUpdateName("台南", "台南市");
		System.out.println(res);
	}
}
