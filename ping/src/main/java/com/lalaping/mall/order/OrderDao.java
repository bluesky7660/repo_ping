package com.lalaping.mall.order;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	public OrderDto CheckSelectOne(OrderDto orderDto);
	public OrderDto memberSelectOne(OrderDto orderDto);
	public int insertOrder(OrderDto orderDto);
	public List<OrderDto> selectListOrder(OrderDto orderDto);
	public List<OrderDto> selectXdmListOrder(OrderVo vo);
	public int ueleteOrder(OrderDto orderDto);
	public int delete(OrderDto orderDto);
	public List<OrderDto> selectListReturn(OrderDto orderDto);
	public List<OrderDto> orderList(OrderDto orderDto);
	
	public List<OrderDto> allListOrder();
	
	public int allListOrderCount();
	public int orderListCount(OrderDto orderDto);
	public int listCount(OrderVo vo);
	public OrderDto selectOne(OrderDto orderDto);

}
