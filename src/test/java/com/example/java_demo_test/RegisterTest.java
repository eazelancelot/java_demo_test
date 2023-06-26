package com.example.java_demo_test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.respository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class RegisterTest {
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private RegisterService service;
	
	@Value("${heartbeat.ms: 3000}")
	private int heartbeat;
	
	@Test
	public void getPropertiesValue() {
		System.out.println(heartbeat);
	}
	
	@Test
	public void registerTest() {
//		System.out.println(Base64Util.base64Encode("AA123".getBytes()));
//		System.out.println(Base64Util.base64EncodeWithoutPadding("AA123".getBytes()));
//		System.out.println(Base64Util.base64URLEncode("AA123".getBytes()));
//		System.out.println(new String(Base64Util.base64Decode("QUExMjM="), StandardCharsets.UTF_8));
//		System.out.println(new String(Base64Util.base64Decode("QUExMjM"), StandardCharsets.UTF_8));
//		System.out.println(new String(Base64Util.base64URLDecode("QUExMjM"), StandardCharsets.UTF_8));
//		//=======================================
//		System.out.println("===========================");
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("AA123"));
		try {
			service.register("A99", "A99");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void mapTest() {
		//Map<commodity_id, quantity>
		Map<String, Integer> map = new HashMap<>();
		map.put("C01", 5);
		map.put("C02", 3);
		map.put("C03", 1);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String mapStr = mapper.writeValueAsString(map);
			System.out.println(map.toString());
			System.out.println(mapStr);
			Map<String, Integer> resMap = mapper.readValue(mapStr, Map.class);
			for(Entry<String, Integer> item: resMap.entrySet()) {
				System.out.println("key: " + item.getKey());
				System.out.println("value: " + item.getValue());
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void pageableTest() {
		Page<Register> res = registerDao.findAllByActive(false, PageRequest.of(0, 2));
		for(Register item : res) {
			System.out.println(item.getAccount());
		}
//		Page<Register> res1 = registerDao.findAllByActive(false, PageRequest.of(1, 2));
//		for(Register item : res1) {
//			System.out.println(item.getAccount());
//		}
//		System.out.println(res);
	}
	
	@Test
	public void test9() {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("account", 123);
		map1.put("pwd", "abc");
		Map<String, Object> map2 = new HashMap<>();
		map2.put("reg_time", LocalDateTime.now());
		map2.put("active", true);
		List<Map<String, Object>> list = new ArrayList<>(Arrays.asList(map1, map2));
		
		list.forEach(map -> map.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		}));
	}
	
	@Test
	public void test10() {
		List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
		List<String> newList = list.stream().filter(
				item -> item.equals("A") || item.equals("B")).collect(Collectors.toList());
		
		list.forEach(item -> System.out.println(item));
		System.out.println("======================");
		newList.forEach(item -> System.out.println(item));
	}
	
	@Test
	public void test11() {
		List<String> strList = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
		List<Integer> intList = strList.stream().map(item -> {
			Integer a = Integer.parseInt(item);
			a++;
			return a;
		}).collect(Collectors.toList());
		
		System.out.println(strList);
		System.out.println(intList);
	}
	
	@Test
	public void test51() {
		List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 5, 2));
		
		//default: ASC
		List<Integer> ascList = list.stream().sorted().collect(Collectors.toList());
		//desc
		List<Integer> descList = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		
		System.out.println(list);
		System.out.println(ascList);
		System.out.println(descList);
	}
	
	@Test
	public void test12() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		
		Optional<Integer> result = list.stream().reduce((a, b) -> a + b);
		
		System.out.println(result.get());
		System.out.println("===========================");
		
		Integer result1 = list.stream().reduce(100, (a, b) -> a + b);
		System.out.println(result1);
	}
	
	@Test
	public void listToMaptest() {
		List<Register> list = new ArrayList<>(Arrays.asList(//
				new Register("A01", "AA123"),//
				new Register("A02", "BB123"),//
				new Register("A03", "CC123"),//
				new Register("A01", "DD123")));
		Map<String, String> map = list.stream().collect(
				Collectors.toMap(Register::getAccount, Register::getPwd, //
						(k1, k2) -> k1)); // 表示 k1, k2 重複時，保留 k1(舊的)對應的 value
		map.forEach((k, v) -> {
			System.out.println("account: " + k);
			System.out.println("pwd: " + v);
			System.out.println("================");
		});
	}
	
	@Test
	public void loggerTest() throws Exception {
		service.register(null, null);
	}
	
	@Test
	public void cacheableTest() {
		RegisterResponse res = service.getRegTime("018402", "weeee");
		System.out.println(res.getRegTime());
	}

}
