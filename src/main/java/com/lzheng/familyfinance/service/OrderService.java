package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

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
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

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


    public void updateOrderStatus(Integer[] oIds,Integer mId){
        TransactionStatus transactionStatus=null;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            for (Integer oid:oIds
                 ) {
                orderDao.updateOrderStatus(oid,mId);
            }
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            if(transactionStatus!=null){
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            new GlobalException(505,"事务失败");
        }
    }


    public void updateByPrimaryKey(Order order){
        orderDao.updateByPrimaryKey(order);
    }


    public void  addOrder(Order order){
        orderDao.insert(order);
    }


}
