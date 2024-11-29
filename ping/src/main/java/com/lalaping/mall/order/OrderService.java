package com.lalaping.mall.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	public OrderDao orderDao;
	
	public OrderDto CheckSelectOne(OrderDto orderDto) {
		return orderDao.CheckSelectOne(orderDto);
	}
	public OrderDto memberSelectOne(OrderDto orderDto) {
		return orderDao.memberSelectOne(orderDto);
	}
	public int insertOrder(OrderDto orderDto) {
		return orderDao.insertOrder(orderDto);
	}
	public List<OrderDto> selectListOrder(OrderDto orderDto){
		return orderDao.selectListOrder(orderDto);
	}

}
