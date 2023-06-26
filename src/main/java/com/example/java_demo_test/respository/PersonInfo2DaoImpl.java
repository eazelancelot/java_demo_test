package com.example.java_demo_test.respository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfo2DaoImpl extends BaseDao {
	
	public List<PersonInfo> doQueryByAge(int age){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class);
	}
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitSize);
	}
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize, int startPosition){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitSize, startPosition);
	}
	
	public int updateAgeByName(int age, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("update PersonInfo set age = :age where name = :name");
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		params.put("name", name);
		return doUpdate(sb.toString(), params);
	}

	
//	public PersonInfo findByIdAndName(String id, String name) {
//		StringBuffer sb = new StringBuffer();
//        sb.append(" select new PersonInfo(p.id, p.name) from PersonInfo p")
//          .append(" where p.id = :inputId and p.name = :inputName");
//        Map<String, Object> params = new HashMap<>();
//        params.put("inputId", id);
//        params.put("inputName", name);
//        return doQuery(sb.toString(), params, PersonInfo.class).get(0);
//		
//	}
	
//	public PersonInfo joinTwoTables(String id) {
//		StringBuffer sb = new StringBuffer();
//        sb.append(" select p.id, p.name, b.amount from person_info p "
//        		+ "join bank b on p.id = b.account "
//        		+ "where p.id = :inputId");
//        Map<String, Object> params = new HashMap<>();
//        params.put("inputId", id);
//        return doNativeQuery(sb.toString(), params, PersonInfo.class).get(0);		
//	}
}
