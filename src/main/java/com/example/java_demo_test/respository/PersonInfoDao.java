package com.example.java_demo_test.respository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoBankVo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {

	//============================== insert
	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) "
			+ "values (:inputId, :inputName, :intputAge, :inputCity)", nativeQuery = true)
	public int insertInfo(@Param("inputId") String id, @Param("inputName") String name, @Param("intputAge") int age,
			@Param("inputCity") String city);

	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) values (?1, ?2, ?3, ?4)", nativeQuery = true)
	public int insertInfo2(String id, String name, int age, String city);

	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) "
			+ " select :inputId, :inputName, :intputAge, :inputCity "
			+ " where not exists (select 1 from person_info where id = :inputId)", nativeQuery = true)
	public int insertInfo3(@Param("inputId") String id, @Param("inputName") String name, @Param("intputAge") int age,
			@Param("inputCity") String city);

	// if customers.id = 1003 exists, insert customers ""all"" data into person_info
	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) " + " select id, name, age, city from customers "
			+ " where exists (select 1 from customers where id = :inputId)", nativeQuery = true)
	public int insertInfo4(@Param("inputId") int id);

	// if customers.id = 1003 not exists, insert customers id = 1003 data into
	// person_info
	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) "
			+ " select id, name, age, city from customers where id = :inputId"
			+ " and exists (select 1 from customers where id = :inputId)", nativeQuery = true)
	public int insertInfo5(@Param("inputId") int id);

	// if customers.id = 1003 not exists, insert customers id = 1003 data into
	// person_info
	// same as insertInfo5
	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) "
			+ " select id, name, age, city from customers where id = :inputId", nativeQuery = true)
	public int insertInfo6(@Param("inputId") int id);

	@Transactional
	@Modifying
	@Query(value = "insert into person_info(id, name, age, city) "
			+ " select id, name, age, city from customers where id = :cId"
			+ " and not exists (select 1 from person_info where id = :pId)", nativeQuery = true)
	public int insertInfo7(@Param("cId") int cId, @Param("pId") String pId);

	// ================================== update
	@Transactional
	@Modifying
	@Query("update PersonInfo set name = :inputName where id = :inputId")
	public int updateNameById(@Param("inputName") String name, @Param("inputId") String id);

	@Transactional
	@Modifying
	@Query(value = "update person_info set name = :inputName where id = :inputId", nativeQuery = true)
	public int nativeUpdateNameById(@Param("inputName") String name, @Param("inputId") String id);
	
	@Transactional(rollbackOn = Exception.class)
	@Modifying
	@Query("update PersonInfo set city = :newCity where city = :oldCity")
	public int updateCity(//
			@Param("newCity") String newCity, //
			@Param("oldCity") String oldCity);
	
	@Transactional
	@Modifying
	@Query("update PersonInfo set name = concat('new_', :newName) where name like %:oldName%")
	public int batchUpdateName(@Param("newName") String newName, @Param("oldName") String oldName);

	// ================================== select
	// select all columns； 等同於 JPA 的 findByIdAndName
	@Query("select p from PersonInfo p where id = :inputId and name = :inputName")
	public PersonInfo findByIdAndName(//
			@Param("inputId") String id, //
			@Param("inputName") String name);
	
	@Query("select p from PersonInfo p where id = ?1 and name = ?2")
	public PersonInfo findByIdAndName0(String id, String name);

	// select id, name, age
	@Query("select new PersonInfo(p.id, p.name, p.age) from PersonInfo p "
			+ "where id = :inputId and name = :inputName")
	public PersonInfo findByIdAndName1(//
			@Param("inputId") String id, //
			@Param("inputName") String name);

	// nativeQuery: select all columns
	@Query(value = "select id, name, age, city from person_info "
			+ "where id = :inputId and name = :inputName", nativeQuery = true)
	public PersonInfo findByIdAndName2( //
			@Param("inputId") String id, //
			@Param("inputName") String name);

	// nativeQuery: select all columns
	@Query(value = "select * from person_info " //
			+ "where id = :inputId and name = :inputName", nativeQuery = true)
	public PersonInfo findByIdAndName3(//
			@Param("inputId") String id, //
			@Param("inputName") String name);

	// nativeQuery: select id, name, age；不實用
	@Query(value = "select id, name, age from person_info "
			+ "where id = :inputId and name = :inputName", nativeQuery = true)
	public List<Map<String, Object>> findByIdAndName4(//
			@Param("inputId") String id, //
			@Param("inputName") String name);
	
	// select limit
	@Query(value = "select * from person_info where age > :inputAge limit :num", nativeQuery = true)
	public List<PersonInfo> findByAgeGreaterThanLimit(//
			@Param("inputAge") int age, //
			@Param("num") int limitNum);
	
	// select limit with start_position
	@Query(value = "select * from person_info where age > :inputAge limit :startPosition, :num", //
			nativeQuery = true)
	public List<PersonInfo> findByAgeGreaterThanLimit2(//
			@Param("inputAge") int age, //
			@Param("startPosition") int startPosition, //
			@Param("num") int limitNum);
	
	@Query(value = "select * from person_info where age > ?1 limit ?2, ?3", //
			nativeQuery = true)
	public List<PersonInfo> findByAgeGreaterThanLimit3(int age, int startPosition, int limitNum);
	
	@Query(value = "select * from person_info where city regexp :input", //
			nativeQuery = true)
	public List<PersonInfo> findByCityRegexp(//
			@Param("input") String input);
	
	@Query(value = "select * from person_info where city regexp :inputCity or name regexp :inputName", //
			nativeQuery = true)
	public List<PersonInfo> findByCityOrNameRegexp(//
			@Param("inputCity") String inputCity, //
			@Param("inputName") String inputName);
	
	@Query(value = "select * from person_info where city regexp ?1 or name regexp ?2", //
			nativeQuery = true)
	public List<PersonInfo> findByCityOrNameRegexp1(String inputCity, String inputName);
	
	// JPA: city containing 北 or 中 or 南
	public List<PersonInfo> findByCityContainingOrCityContainingOrCityContaining(//
			String city1, String city2, String city3);
	
	//================================ join
	@Query(value = "select p.id, p.name, b.amount from person_info p" //
			+ " join bank b on p.id = b.account where p.id = 'A01'", nativeQuery = true)
	public List<Map<String, Object>> joinTwoTables();
	
	@Query(value = "select p.id, p.name, b.amount from person_info p" //
			+ " join bank b on p.id = b.account where p.id = ?1", nativeQuery = true)
	public List<Map<String, Object>> joinTwoTables2(String id);
	
	@Query("select new com.example.java_demo_test.vo.PersonInfoBankVo(p.id, p.name, b.amount) "//
			+ " from PersonInfo p" //
			+ " join Bank b on p.id = b.account where p.id = ?1")
	public PersonInfoBankVo joinTwoTables3(String id);
	
	public List<PersonInfo> findByIdContainingAndNameContainingAndCityContaining(String id, String name, String city);
	
	public List<PersonInfo> findByNameContainingAndCityContainingAndAgeGreaterThanEqual(String name, String city, int age);
	
	//=========================分頁		
	// JPA 原本提供的方法 List<PersonInfo> findAll();
	public Page<PersonInfo> findAll(Pageable pageable);	
	
	// 自定義方法使用分頁
	@Query(value = "select * from person_info where city regexp :input", //
			nativeQuery = true)
	public Page<PersonInfo> findByCityRegexpPaging(//
			@Param("input") String input, Pageable pageable);
}
