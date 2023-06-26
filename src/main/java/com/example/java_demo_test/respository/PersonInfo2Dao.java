package com.example.java_demo_test.respository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;

@Repository
public interface PersonInfo2Dao extends JpaRepository<PersonInfo, String>{
	
	public List<PersonInfo> findByAgeGreaterThan(int age);
	
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int fromAge, int toAge);
	
	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age1, int age2);
	
	public List<PersonInfo> findByCityContaining(String str);
	
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age, String str);
	
	public List<PersonInfo> doQueryByAge(int age);
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize);
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize, int startPosition);
	
	@Transactional
	public int updateAgeByName(int age, String name);

	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.name = :newName where p.id = :newId")
	public int updateNameById(
			@Param("newId") String inputId,
			@Param("newName") String inputName);
		
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city) values (:inputId, :inputName, "
			+ ":inputAge, :inputCity)", nativeQuery = true)
	public int insertInfo(
			@Param("inputId") String inputId,
			@Param("inputName") String inputname,
			@Param("inputAge") int inputage,
			@Param("inputCity") String inputCity);
	
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city) values (?1, ?2, ?3, ?4)", nativeQuery = true)
	public int insertInfo2(String inputId, String inputname, int inputage, String inputCity);
	
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city) "
			+ "select :inputId, :inputName, :inputAge, :inputCity "
			+ "where not exists (select 1 from person_info where id = :inputId)", nativeQuery = true)
	public int insertInfo3(
			@Param("inputId") String inputId,
			@Param("inputName") String inputname,
			@Param("inputAge") int inputage,
			@Param("inputCity") String inputCity);
	
	//只顯示特定欄位
	public PersonInfo findByIdAndName(String id, String name);
	
	@Query(value = "select new PersonInfo(p.id, p.name) from PersonInfo p "
			+ "where p.id = :inputId and p.name = :inputName")
	public PersonInfo findByIdAndName2(
			@Param("inputId")String id, 
			@Param("inputName") String name);
	
	@Query(value = "select p.id, p.name, b.amount from person_info p join bank b on p.id = b.account"
			+ " where p.id = :inputId", nativeQuery = true)
	public List<Map<String, Object>> joinTwoTables(@Param("inputId")String id);
	
	@Query(value = "select p.id, p.name, p.age, p.city from person_info p limit :limitNum", 
			nativeQuery = true)
	public List<PersonInfo> findAllByLimit(
			@Param("limitNum")int num);
	
	@Query(value = "select p.id, p.name, p.age, p.city from person_info p limit :startIndex, :limitNum", 
			nativeQuery = true)
	public List<PersonInfo> findAllByLimitAndStartIndex(
			@Param("startIndex")int startIndex,
			@Param("limitNum")int num);
	
	/*
	 * 1. 根據學生的ID --> 撈出該學生所選的課程代碼: Student --> findById --> 得到課程代碼
	 * 2. 根據 1 得到的課程代碼撈出課程所有相關的訊息: Course --> findByIds(List<String> ids)
	 */
//	@Query("select C from Course C where id like %(select S.courseCode from Student S where id = :inputId)%")
//	public List<Course> findCourseByCode(@Param("inputId") String inputId){
//		
//	}
	
	@Query(value = "select * from person_info p where p.city regexp :inputRegexp", nativeQuery = true)
	public List<PersonInfo> findByNameContainingOneOf(@Param("inputRegexp")String str);
	
	@Query(value = "select * from person_info p where p.name regexp :regexpName or p.city regexp :regexpCity", 
			nativeQuery = true)
	public List<PersonInfo> findByNameOrCityContainingOneOf(
			@Param("regexpName")String inputName,
			@Param("regexpCity")String inputCity);
	
	//JPA
	public List<PersonInfo> findByNameContainingOrCityContaining(String name, String city);
	
}
 