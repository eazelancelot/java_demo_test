package com.example.java_demo_test.respository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA 專有的注釋
	private EntityManager entityManager;

//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		Query query = entityManager.createQuery(sql, clazz);
//		if (!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//
////			for (Parameter p : query.getParameters()) {
////				query.setParameter(p, params.get(p.getName()));
////			}
//		}
//
//		return query.getResultList();
		return doQuery(sql, params, clazz, -1);
	}

	/*
	 * 限制回傳筆數
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize) {
//		Query query = entityManager.createQuery(sql, clazz);
//		if (!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//		}
//		if (limitSize > 0) {
//			query.setMaxResults(limitSize);
//		}
//		return query.getResultList();
		return doQuery(sql, params, clazz, limitSize, -1);
	}

	/*
	 * limitSize: 限制回傳筆數 
	 * startPosition: 每頁的起始位置
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize, int startPosition) {
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if (limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if(startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}
	
	protected int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManager.createQuery(sql);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doNativeQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize, int startPosition) {
		Query query = entityManager.createNativeQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if (limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if(startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}

}
