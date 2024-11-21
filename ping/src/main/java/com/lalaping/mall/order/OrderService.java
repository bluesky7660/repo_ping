package com.lalaping.mall.order;

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

}
