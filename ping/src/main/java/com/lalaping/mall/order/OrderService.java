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
	public List<OrderDto> selectXdmListOrder(OrderVo vo){
		return orderDao.selectXdmListOrder(vo);
	}
	public int ueleteOrder(OrderDto orderDto) {
		return orderDao.ueleteOrder(orderDto);
	}
	public int delete(OrderDto orderDto) {
		return orderDao.delete(orderDto);
	}
	public List<OrderDto> selectListReturn(OrderDto orderDto){
		 return orderDao.selectListReturn(orderDto);
	}
	public List<OrderDto> orderList(OrderDto orderDto){
		 return orderDao.orderList(orderDto);
	}
	public int listCount(OrderVo vo) {
		return orderDao.listCount(vo);
	}
	
	public List<OrderDto> allListOrder(){
		return orderDao.allListOrder();
	}
	
	public int allListOrderCount() {
		return orderDao.allListOrderCount();
	}
	public int orderListCount(OrderDto orderDto) {
		return orderDao.orderListCount(orderDto);
	}
	public OrderDto selectOne(OrderDto orderDto) {
		return orderDao.selectOne(orderDto);

	}
}
