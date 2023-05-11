package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.respository.OrderDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderResponse getAllMenus() {		
		return new OrderResponse(orderDao.findAll(), "取得餐點成功!!");
	}

	@Override
	public OrderResponse addMenus(List<Menu> menuList) {
		if(CollectionUtils.isEmpty(menuList)) { //判斷 menuList == null || menuList.isEmpty()
			return new OrderResponse("新增餐點錯誤!!");
		}
		for(Menu menuItem : menuList) {
			if(!StringUtils.hasText(menuItem.getItem())) {//檢查餐點名稱不得為全空白或空字串
				return new OrderResponse("餐點名稱不能為空!!");
			}
			if(menuItem.getPrice() <= 0) {
				return new OrderResponse("餐點價格錯誤!!");
			}
		}
		List<Menu> response = orderDao.saveAll(menuList);
		return new OrderResponse(response, "新增餐點成功!!");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {//beef:10; AAA:5; tea:6
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;
		for(Entry<String, Integer> map : orderMap.entrySet()) {
			if(map.getValue() < 0) {
				return new OrderResponse("餐點數量錯誤!!");
			}
			Optional<Menu> op = orderDao.findById(map.getKey());
			if (!op.isPresent()) {
				continue;
			}
			finalOrderMap.put(map.getKey(), map.getValue());
			totalPrice += op.get().getPrice()*map.getValue();
		}
		totalPrice = totalPrice > 500 ? (int)(totalPrice*0.9) : totalPrice;
		return new OrderResponse(finalOrderMap, totalPrice, "點餐成功!!");
		
		//=========================
//		List<String> itemList = new ArrayList<>();
//		for(Entry<String, Integer> item : orderMap.entrySet()) {
//			if(item.getValue() < 0) {
//				return new OrderResponse("餐點數量錯誤!!");
//			}
//			itemList.add(item.getKey());
//		}
//		//itemList:["beef", "AAA", "tea"]；AAA 此品項實際上不存在
//		List<Menu> result = orderDao.findAllById(itemList);//第1筆,"beef": 120; 第2筆, "tea": 30
//		int totalPrice = 0;
//		Map<String, Integer> finalOrderMap = new HashMap<>();
//		for(Menu menu : result) {
//			String item = menu.getItem();//餐點名稱(有2筆); 第1筆: beef; 第2筆: tea 
//			int price = menu.getPrice();
//			for(Entry<String, Integer> map : orderMap.entrySet()) {
//				String key = map.getKey();//orderMap 中的餐點名稱
//				int value = map.getValue();//orderMap 中的餐點數量
//				if(item.equals(key)) {
//					int singleTotalPrice = price * value; //價格*數量
//					//totalPrice = totalPrice + singleTotalPrice;
//					totalPrice += singleTotalPrice;
//					finalOrderMap.put(key, value);
//				}
//			}
//		}
//		totalPrice = totalPrice > 500 ? (int)(totalPrice*0.9) : totalPrice;
//		return new OrderResponse(finalOrderMap, totalPrice, "點餐成功!!");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if(!StringUtils.hasText(name)) {
			return new GetMenuResponse("餐點名稱錯誤!!");
		}
		Menu menu = orderDao.findByItem(name);
		if(menu == null) {
			return new GetMenuResponse("餐點不存在!!");
		}
		return new GetMenuResponse(menu, "successful!!");
//		Optional<Menu> op = orderDao.findById(name);
//		if(!op.isPresent()) {
//			return new GetMenuResponse("餐點不存在!!");
//		}
//		return new GetMenuResponse(op.get(), "successful!!");
	}

	@Override
	public OrderResponse updateMenuPrice(List<Menu> menuList) {//beef:150; AAA:50; tea:60()
//		List<Menu> updateMenus = new ArrayList<>();
//		for(Menu menu : menuList) {
//			boolean boo = orderDao.existsById(menu.getItem());
//			if (boo) {
//				updateMenus.add(menu);
//			}
//		}
//		if (updateMenus.size() == 0) {
//			return new OrderResponse("查無菜單!!");
//		}
//		return new OrderResponse(orderDao.saveAll(updateMenus), "successful");
		
		//=============================
		if(CollectionUtils.isEmpty(menuList)) {
			return new OrderResponse("菜單價格錯誤!!");
		}
		List<String> ids = new ArrayList<>();
		for(Menu menu : menuList) {
			//不需要判斷空字串或是空白字串，是因為即使帶入了空字串或空白字串，DB中也查無資料
			if(menu.getPrice() < 0) {
				return new OrderResponse("菜單價格錯誤!!");
			}
			ids.add(menu.getItem());//ids=["beef", "AAA", "tea"]
		}
		List<Menu> res = orderDao.findAllById(ids); //res(In DB)= beef:120; tea:30 
		if(res.size() == 0) {
			return new OrderResponse("查無菜單!!");
		}
		List<Menu> newMenuList = new ArrayList<>();
		for(Menu resMenu : res) {
			String itemInDB = resMenu.getItem();
			for(Menu menu : menuList) {
				String updateItem = menu.getItem();
				if(itemInDB.equals(updateItem)) {
					newMenuList.add(menu);
				}
			}
		}		
		return new OrderResponse(orderDao.saveAll(newMenuList), "successful");
	}

}
