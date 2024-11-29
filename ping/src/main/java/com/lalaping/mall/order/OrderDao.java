package com.lalaping.mall.order;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	public OrderDto CheckSelectOne(OrderDto orderDto);
	public OrderDto memberSelectOne(OrderDto orderDto);
	public int insertOrder(OrderDto orderDto);
	public List<OrderDto> selectListOrder(OrderDto orderDto);
	public int ueleteOrder(OrderDto orderDto);
	public List<OrderDto> selectListReturn(OrderDto orderDto);

}
