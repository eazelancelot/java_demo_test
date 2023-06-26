package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest(classes = JavaDemoTestApplication.class)
public class ApiTest {
	
	@Value("${heartbeat.ms}")
	private int heartbeat;
	
	@Test
	public void getPropertiesValue() {
		System.out.println(heartbeat);
		String str = String.valueOf('e');
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getApiTest0() throws JsonMappingException, JsonProcessingException {
		String targetUrl = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=10&skip=0";
		RestTemplate restTemplate = new RestTemplate();
//		String resStr = restTemplate.getForObject(targetUrl, String.class);
		ResponseEntity<String> resString = restTemplate.getForEntity(targetUrl, String.class);

		System.out.println(resString.getStatusCode());
		System.out.println(resString.getStatusCodeValue());
		String responseString = resString.getBody();
		System.out.println(responseString);
		// ========================================
		System.out.println("========================================");
//		System.out.println(resStr);
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> resList = mapper.readValue(responseString, List.class);
		for (Map<String, Object> item : resList) {
			for (Entry<String, Object> map : item.entrySet()) {
				System.out.println("key: " + map.getKey());
				System.out.println("value: " + (String)map.getValue());
			}
		}
	}
	
	@Test
	public void postApiTest0() {
		String targetUrl = "http://172.20.10.8:8080/api/register";
		Map<String, String> bodyMap = new HashMap<>();
		bodyMap.put("account", "A99");
		bodyMap.put("pwd", "AA123");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String reqBodyStr = mapper.writeValueAsString(bodyMap);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestBody = new HttpEntity<String>(reqBodyStr, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(targetUrl, requestBody, String.class);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getApiTest() {
		RestTemplate restTemplate = new RestTemplate();
		String urlStr = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=10&skip=0";
		ResponseEntity<String> res = restTemplate.getForEntity(urlStr, String.class);
		//===================
		System.out.println(res.getStatusCodeValue());
		System.out.println(res.getStatusCode());
		String resStr = res.getBody();
		System.out.println(resStr);
		List<Map<String, String>> mapList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mapList = objectMapper.readValue(resStr, List.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Map<String, String> item : mapList) {
			for(Entry<String, String> map : item.entrySet()) {
				System.out.println("==================");
				System.out.println("key: " + map.getKey());
				System.out.println("value: " + map.getValue());
				System.out.println("==================");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getApiTest1() {
		RestTemplate restTemplate = new RestTemplate();
		String urlStr = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=10&skip=0";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> request = new HttpEntity<String>(headers);
//		ResponseEntity<String> res = restTemplate.getForEntity(urlStr, String.class);
		ResponseEntity<String> res = restTemplate.exchange(urlStr, HttpMethod.GET, request, String.class);
		//===================
		System.out.println(res.getStatusCodeValue());
		System.out.println(res.getStatusCode());
		String resStr = res.getBody();
		System.out.println(resStr);
		List<Map<String, String>> mapList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mapList = objectMapper.readValue(resStr, List.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Map<String, String> item : mapList) {
			for(Entry<String, String> map : item.entrySet()) {
				System.out.println("==================");
				System.out.println("key: " + map.getKey());
				System.out.println("value: " + map.getValue());
				System.out.println("==================");
			}
		}
	}
	
	@Test
	public void postApiTest() {
		RestTemplate restTemplate = new RestTemplate();
		String urlStr = "http://192.168.8.163:8080/api/register";
		//set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set request_body
		Map<String, String> map = new HashMap<>();
		map.put("account", "A999");
		map.put("pwd", "ASD999");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String reqStr = objectMapper.writeValueAsString(map);
			HttpEntity<String> requestBody = new HttpEntity<String>(reqStr, headers);
			ResponseEntity<String> res = restTemplate.postForEntity(urlStr, requestBody, String.class);
			System.out.println(res.getStatusCodeValue());
			System.out.println(res.getStatusCode());
			String resStr = res.getBody();
			System.out.println(resStr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void mapToStringTest() {
		Map<String, String> map = new HashMap<>();
		map.put("account", "A999");
		map.put("pwd", "ASD999");
		System.out.println(map.toString());
		System.out.println("===================");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String reqStr = objectMapper.writeValueAsString(map);
			System.out.println(reqStr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void reverseStringTest() {
		String str = "abcde";
		StringBuffer sb = new StringBuffer(str);
		System.out.println("str: " + str);
		System.out.println("reverse str: " + sb.reverse().toString());
	}

}
