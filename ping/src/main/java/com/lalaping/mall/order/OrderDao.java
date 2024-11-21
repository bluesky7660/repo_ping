package com.lalaping.mall.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	public OrderDto CheckSelectOne(OrderDto orderDto);

}