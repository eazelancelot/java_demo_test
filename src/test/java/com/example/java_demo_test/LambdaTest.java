package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.example.java_demo_test.entity.Register;

public class LambdaTest {

	@Test
	public void listForEachTest() {
		List<String> strList = new ArrayList<>(Arrays.asList("A", "B", "C"));
		for (String item : strList) {
			System.out.println(item);
		}
		// Lambda
		strList.forEach((item) -> {
			System.out.println(item);
		});
		// 參數列表只有一個參數的時候，小括號可以省略
		strList.forEach(item -> {
			System.out.println(item);
		});
		// 表達式主體只有一行的時候，大括號和結尾的分號可以省略
		strList.forEach(item -> System.out.println(item));
	}

	@Test
	public void mapForEachTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		for (Entry<String, Integer> item : map.entrySet()) {
			System.out.println(item.getKey());
			System.out.println(item.getValue());
		}
		// Lambda
		map.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		});
	}

	@Test
	public void mapListForEachTest() {
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("A", 1);
		map1.put("B", 2);
		map1.put("C", 3);
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("AA", 11);
		map2.put("BB", 22);
		map2.put("CC", 33);
		List<Map<String, Integer>> mapList = new ArrayList<>(Arrays.asList(map1, map2));
		mapList.forEach(map -> {
			map.forEach((k, v) -> {
				System.out.println(k);
				System.out.println(v);
			});
		});
		mapList.forEach(map -> map.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		}));
	}

	// map 沒有重複的 key
	@Test
	public void listToMap() {
		List<Register> list = new ArrayList<>(
				Arrays.asList(new Register("A01", "AA123"), new Register("A02", "BB123")));
		Map<String, String> map = new HashMap<>();
		for (Register item : list) {
			map.put(item.getAccount(), item.getPwd());
		}
		// Lambda forEach
		list.forEach(item -> {
			map.put(item.getAccount(), item.getPwd());
		});
		// Lambda stream
		Map<String, String> mapp = list.stream().collect(Collectors.toMap(Register::getAccount, Register::getPwd));
		// ========================
		map.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		mapp.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
	}

	// map 有重複的 key 
	@Test
	public void listToMap2() {
		List<Register> list = new ArrayList<>(Arrays.asList(//
				new Register("A01", "AA123"), //
				new Register("A02", "BB123"), //
				new Register("A02", "CC123")));
		Map<String, String> map = new HashMap<>();
		// Lambda forEach
		list.forEach(item -> {
			map.put(item.getAccount(), item.getPwd());
		});
		map.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		// Lambda stream
		Map<String, String> mapp = list.stream().collect(Collectors.toMap(
				Register::getAccount, Register::getPwd, (k1, k2) -> k2));
		mapp.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		System.out.println("=================");
		list.forEach(item -> {
			System.out.println(item.getAccount() + ": " + item.getPwd());
		});
	}
	
	@Test
	public void compareTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(7, 1, 10, 6, 3));
		list.sort((o1, o2) -> o1.compareTo(o2));
		list.forEach(item -> {
			System.out.println(item);
		});
	}
	
	// 使用 stream()
	@Test
	public void compareTest2() {
		List<Integer> list = new ArrayList<>(Arrays.asList(7, 1, 10, 6, 3));
		//預設 asc
		List<Integer> newList = list.stream().sorted().collect(Collectors.toList());
		System.out.println("================");
		list.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("================");
		newList.forEach(item -> {
			System.out.println(item);
		});
		// desc
		List<Integer> newList2 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("================");
		newList2.forEach(item -> {
			System.out.println(item);
		});
	}
	
	@Test
	public void filterTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(7, 1, 10, 6, 3));
		List<Integer> newList = new ArrayList<>();
		for(Integer item : list) {
			if (item % 2 == 1) {
				newList.add(item);
			}
		}
		list.forEach(item -> {
			if (item % 2 == 1) {
				newList.add(item);
			}
		});
		
		List<Integer> newList2 = list.stream().filter(item -> item % 2 == 1).collect(Collectors.toList());
		newList2.forEach(item -> {
			System.out.println(item);
		});
	}
	
	// map 指的是將原本資料轉換成另一種 資料格式/值 輸出
	@Test
	public void mapTest() {
		List<String> list = new ArrayList<>(Arrays.asList("7", "1", "10", "6", "3"));
		//將 list 中的所有字串轉成數值，再都 +2
		List<Integer> newList = list.stream().map(item -> {
			Integer i = Integer.parseInt(item);
			i += 2;
			return i;
		}).collect(Collectors.toList());
		
		list.forEach(item -> {
			System.out.println(item);
		});
		System.out.println("===============");
		newList.forEach(item -> {
			System.out.println(item);
		});
	}
	
	@Test
	public void reduceTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(7, 1, 10, 6, 3));
		Optional<Integer> res = list.stream().reduce((i, j) -> i*j);
		System.out.println(res.get());
		Integer res1 = list.stream().reduce(100, (i, j) -> i*j);
		System.out.println(res1);
	}
	
	@Test
	public void consumerTest() {
		Consumer<String> c = x -> {
			System.out.println(x.toLowerCase());
		};
		System.out.println("==============");
		c.accept("ABC");
	}
	
	@Test
	public void biConsumerTest() {
		BiConsumer<String, String> c = (x, y) -> {
			System.out.println(x + " " + y);
		};
		c.accept("hello", "java");
	}
	
	@Test
	public void functionTest() {
		Function<Integer, String> f = x -> {
			int y = x*10; 
			return String.valueOf(y);
		};
		System.out.println(f.apply(5));
	}
	
	@Test
	public void functionTest2() {
		Function<String, String> f = str -> {
			return "Hi~" + str;
		};
		System.out.println(f.apply("Java"));
	}
	
	@Test
	public void biFunctionTest() {
		BiFunction<Integer, Integer, String> f = (x, y) -> {
			int z = x + y;
			return String.valueOf(z);
		};
		System.out.println(f.apply(2, 3));
	}
	
	@Test
	public void predicateTest() {
		Predicate<String> p = x -> {
			return x.length() > 3;
		};
		System.out.println(p.test("Java"));
	}
	
	@Test
	public void predicateTest2() {
		Predicate<String> p = x -> {
			return x.length() > 4;
		};
		List<String> list = new ArrayList<>(
				Arrays.asList("C++", "Java", "Python", "Kotlin"));
		list.forEach(item -> {
			System.out.println(p.test(item));
		});
	}
	
	@Test
	public void andPredicateTest() {
		Predicate<String> p1 = x -> {
			return x.length() > 4;
		};
		Predicate<String> p2 = x -> {
			return x.startsWith("P");
		};
		List<String> list = new ArrayList<>(Arrays.asList("C++", "Java", "Python", "Kotlin"));
		list.forEach(item -> {
			boolean res = p1.and(p2).test(item);
			System.out.println(res);
		});
	}
	
	@Test
	public void orPredicateTest() {
		Predicate<String> p1 = x -> {
			return x.length() > 3;
		};
		Predicate<String> p2 = x -> {
			return x.startsWith("C");
		};
		List<String> list = new ArrayList<>(Arrays.asList("C++", "Java", "Python", "Kotlin"));
		list.forEach(item -> {
			boolean res = p1.or(p2).test(item);
			System.out.println(res);
		});
	}
	
	@Test
	public void supplierTest() {
		Supplier<String> s = () -> {
			return "Hello Java!!";
		};
		System.out.println(s.get());
	}
	
	@Test
	public void intSupllierTest() {
		IntSupplier s = () -> {
			return 123;
		};
		System.out.println(s.getAsInt());
	}
	
	@Test
	public void doubleSupllierTest() {
		DoubleSupplier s = () -> {
			return 123;
		};
		System.out.println(s.getAsDouble());
	}

}
