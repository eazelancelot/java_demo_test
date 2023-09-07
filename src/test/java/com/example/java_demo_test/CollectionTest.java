package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class CollectionTest {
	@Test
	public void listTest() {
		//foreach
		List<String> strList = new ArrayList<>(Arrays.asList("A", "B", "C", "A", "C"));
		strList.add(2, "E");
		for(String item : strList) {
			System.out.println(item);
		}
		System.out.println("=======");
		//Lambda
		strList.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("=======");
		//iterator
		Iterator<String> iter = strList.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("=======");
		System.out.println("=======");
		//先將指針指定到 index+1 的位置
		//這樣的寫法，hasNext() 則會回 false
		ListIterator<String> listIter = strList.listIterator(strList.size());
		System.out.println("=======");
		//從後面遍歷到前面
		while(listIter.hasPrevious()) {
			System.out.println(listIter.previous());
		}
		System.out.println("=======");
		System.out.println("=======");
		System.out.println(strList.toString());
		List<String> subList = strList.subList(0, 3);
		System.out.println(strList.toString());
		System.out.println(subList.toString());
	}
	
	/*
	 * set: 不允許元素重複
	 */
	@Test
	public void setTest() {
		// hashSet: 內部順序不一定與插入時的順序一樣
		Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C", "A", "C", "E", "D", "F"));
		set.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("===============");
		// linkedSet: 內部順序與插入時的順序一樣
		Set<String> linkedSet = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "A", "C", "E", "D", "F"));
		linkedSet.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("===============");
		// treeSet: 會由小到大排列順序
		Set<String> treeSet = new TreeSet<>(Arrays.asList("C", "G", "B", "A", "C", "F", "D", "E"));
		treeSet.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("===============");
		List<String> list = new ArrayList<>(treeSet);
		Collections.reverse(list);
		list.forEach(item -> {
			System.out.println(item);
		});
	}
	
	@Test
	public void mapTest() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		System.out.println(map.containsKey(1));
		System.out.println(map.containsKey(4));
		for(Entry<Integer, String> item : map.entrySet()) {
			System.out.println(item.getKey() + ": " + item.getValue());
		}
		System.out.println("===================");
		map.put(1, "F");
		for(Entry<Integer, String> item : map.entrySet()) {
			System.out.println(item.getKey() + ": " + item.getValue());
		}
		System.out.println("===================");
		map.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		
	}
	
	@Test
	public void twoSumTest() {
		int target = 9;
		//nums=[2, 7, 11, 15]
		List<Integer> nums = new ArrayList<>(Arrays.asList(2, 7, 11, 15));
		//將 list 放入 map
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.size(); i++) {
			map.put(nums.get(i), i);
		}
		//計算兩數之和
		for(int i = 0; i < nums.size(); i++) {
			int t = target - nums.get(i);
			if (map.containsKey(t) && map.get(t) != i) {
				int index = map.get(t);
				System.out.println(i);
				System.out.println(index);
				break;
			}
		}
	}

}
