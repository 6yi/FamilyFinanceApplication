package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderService
 * @Author 6yi
 * @Date 2020/5/24 12:44
 * @Version 1.0
 * @Description:
 */

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;


    // TODO
    public List<Order> getAllOrder(){
        return orderDao.selectAll();
    }

    public List<Order> getOrderByTime(Date start, Date end){

        return orderDao.selectByDate(start,end);

    }

    public List<Order> getOrderByTimeAndMid(Date start, Date end,Integer mid){

        return orderDao.selectByDateAndMid(start,end,mid);

    }

    public void updateOrderStatus(Integer oId,Integer mId){
        orderDao.updateOrderStatus(oId,mId);
    }
    public void  addOrder(Order order){
        orderDao.insert(order);
    }


}
