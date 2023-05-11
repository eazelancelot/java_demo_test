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
		return new OrderResponse(orderDao.findAll(), "���o�\�I���\!!");
	}

	@Override
	public OrderResponse addMenus(List<Menu> menuList) {
		if(CollectionUtils.isEmpty(menuList)) { //�P�_ menuList == null || menuList.isEmpty()
			return new OrderResponse("�s�W�\�I���~!!");
		}
		for(Menu menuItem : menuList) {
			if(!StringUtils.hasText(menuItem.getItem())) {//�ˬd�\�I�W�٤��o�����ťթΪŦr��
				return new OrderResponse("�\�I�W�٤��ର��!!");
			}
			if(menuItem.getPrice() <= 0) {
				return new OrderResponse("�\�I������~!!");
			}
		}
		List<Menu> response = orderDao.saveAll(menuList);
		return new OrderResponse(response, "�s�W�\�I���\!!");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {//beef:10; AAA:5; tea:6
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;
		for(Entry<String, Integer> map : orderMap.entrySet()) {
			if(map.getValue() < 0) {
				return new OrderResponse("�\�I�ƶq���~!!");
			}
			Optional<Menu> op = orderDao.findById(map.getKey());
			if (!op.isPresent()) {
				continue;
			}
			finalOrderMap.put(map.getKey(), map.getValue());
			totalPrice += op.get().getPrice()*map.getValue();
		}
		totalPrice = totalPrice > 500 ? (int)(totalPrice*0.9) : totalPrice;
		return new OrderResponse(finalOrderMap, totalPrice, "�I�\���\!!");
		
		//=========================
//		List<String> itemList = new ArrayList<>();
//		for(Entry<String, Integer> item : orderMap.entrySet()) {
//			if(item.getValue() < 0) {
//				return new OrderResponse("�\�I�ƶq���~!!");
//			}
//			itemList.add(item.getKey());
//		}
//		//itemList:["beef", "AAA", "tea"]�FAAA ���~����ڤW���s�b
//		List<Menu> result = orderDao.findAllById(itemList);//��1��,"beef": 120; ��2��, "tea": 30
//		int totalPrice = 0;
//		Map<String, Integer> finalOrderMap = new HashMap<>();
//		for(Menu menu : result) {
//			String item = menu.getItem();//�\�I�W��(��2��); ��1��: beef; ��2��: tea 
//			int price = menu.getPrice();
//			for(Entry<String, Integer> map : orderMap.entrySet()) {
//				String key = map.getKey();//orderMap �����\�I�W��
//				int value = map.getValue();//orderMap �����\�I�ƶq
//				if(item.equals(key)) {
//					int singleTotalPrice = price * value; //����*�ƶq
//					//totalPrice = totalPrice + singleTotalPrice;
//					totalPrice += singleTotalPrice;
//					finalOrderMap.put(key, value);
//				}
//			}
//		}
//		totalPrice = totalPrice > 500 ? (int)(totalPrice*0.9) : totalPrice;
//		return new OrderResponse(finalOrderMap, totalPrice, "�I�\���\!!");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if(!StringUtils.hasText(name)) {
			return new GetMenuResponse("�\�I�W�ٿ��~!!");
		}
		Menu menu = orderDao.findByItem(name);
		if(menu == null) {
			return new GetMenuResponse("�\�I���s�b!!");
		}
		return new GetMenuResponse(menu, "successful!!");
//		Optional<Menu> op = orderDao.findById(name);
//		if(!op.isPresent()) {
//			return new GetMenuResponse("�\�I���s�b!!");
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
//			return new OrderResponse("�d�L���!!");
//		}
//		return new OrderResponse(orderDao.saveAll(updateMenus), "successful");
		
		//=============================
		if(CollectionUtils.isEmpty(menuList)) {
			return new OrderResponse("��������~!!");
		}
		List<String> ids = new ArrayList<>();
		for(Menu menu : menuList) {
			//���ݭn�P�_�Ŧr��άO�ťզr��A�O�]���Y�ϱa�J�F�Ŧr��Ϊťզr��ADB���]�d�L���
			if(menu.getPrice() < 0) {
				return new OrderResponse("��������~!!");
			}
			ids.add(menu.getItem());//ids=["beef", "AAA", "tea"]
		}
		List<Menu> res = orderDao.findAllById(ids); //res(In DB)= beef:120; tea:30 
		if(res.size() == 0) {
			return new OrderResponse("�d�L���!!");
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
