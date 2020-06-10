package com.lzheng.familyfinance.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.dao.QuotaDao;
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
import java.math.BigDecimal;
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

    @Autowired
    private QuotaDao quotaDao;



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



    /**
     * @author 6yi
     * @date 2020/6/2
     * @return
     * @Description       这里写崩了,早期没想好要统一处理,只传了OID过了,自己给自己挖坑了.......有空再改把...
     **/
    public void updateOrderStatus(Integer[] oIds,Integer mId) {
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            for (Integer oid : oIds
            ) {
                orderDao.updateOrderStatus(oid, mId);
                Order order = orderDao.selectByPrimaryKey(oid);

                if (order.getIType().equals("支出")) {
                    Date oDate = order.getODate();
                    DateTime date = DateUtil.date(System.currentTimeMillis());
                    String monthStart = date.toString("YYYY-MM") + "-01";
                    DateTime dateTime = DateUtil.parse(monthStart);
                    if (oDate.getTime() >= dateTime.getTime()) {
                        quotaDao.updateEexpenses(order.getOMoney().negate(), order.getMId());
                    }

                }
            }
                dataSourceTransactionManager.commit(transactionStatus);
            } catch(Exception e){
                if (transactionStatus != null) {
                    dataSourceTransactionManager.rollback(transactionStatus);
                }
                throw new GlobalException(505, "事务失败" + e.getMessage());
            }
        }


    public void updateByPrimaryKey(Order order){
        TransactionStatus transactionStatus=null;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

            Date oDate = order.getODate();
            DateTime date = DateUtil.date(System.currentTimeMillis());
            String monthStart = date.toString("YYYY-MM")+"-01";
            DateTime dateTime = DateUtil.parse(monthStart);
            if(oDate.getTime()>=dateTime.getTime()) {
                if(order.getIType().equals("1")){
                    order.setIType("支出");
                }else{
                    order.setIType("收入");
                }
                Order order1 = orderDao.selectByPrimaryKey(order.getOId());
                if (order1.getIType().equals(order.getIType()) && order1.getIType().equals("支出")) {
                    //支出改变金额,加上修改前后的差值
                    BigDecimal bigDecimal = order.getOMoney().subtract(order1.getOMoney());
                    quotaDao.updateEexpenses(bigDecimal, order.getMId());
                } else if (!order1.getIType().equals(order.getIType()) && order1.getIType().equals("支出")) {
                    //支出改为收入,只需要减掉之前的
                    quotaDao.updateEexpenses(order1.getOMoney().negate(), order.getMId());
                } else if (order1.getIType().equals("收入") && order.getIType().equals("支出")) {
                    //收入改为支出,直接加上新修改后的值
                    quotaDao.updateEexpenses(order.getOMoney().negate(), order.getMId());
                }
            }
            orderDao.updateByPrimaryKey(order);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            if(transactionStatus!=null){
                dataSourceTransactionManager.rollback(transactionStatus);
            }
           throw  new GlobalException(505,"事务失败");
        }
    }


    public void  addOrder(Order order){
        TransactionStatus transactionStatus=null;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

           if (order.getIType().equals("支出")){
               Date oDate = order.getODate();
               DateTime date = DateUtil.date(System.currentTimeMillis());
               String monthStart = date.toString("YYYY-MM")+"-01";
               DateTime dateTime = DateUtil.parse(monthStart);
               if(oDate.getTime()>=dateTime.getTime()){
                   quotaDao.updateEexpenses(order.getOMoney(),order.getMId());
               }

           }

            orderDao.insert(order);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            if(transactionStatus!=null){
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            throw  new GlobalException(505,"事务失败");
        }


    }


}
