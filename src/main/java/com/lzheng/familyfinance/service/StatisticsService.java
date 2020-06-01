package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.ItemDao;
import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.domain.StatisticsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Statistics
 * @Author 6yi
 * @Date 2020/5/31 14:49
 * @Version 1.0
 * @Description:
 */

@Service
public class StatisticsService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDao orderDao;

    public StatisticsResult getResult(Date start, Date end, Integer mid){
        //获取家庭和个人账单

        List<Order> familyOrders = orderDao.selectByDate(start, end);
        StatisticsResult statisticsResult = new StatisticsResult();
        BigDecimal familyOutCome=new BigDecimal(0);
        BigDecimal familyInCome=new BigDecimal(0);
        BigDecimal personOutCome=new BigDecimal(0);
        BigDecimal personInCome=new BigDecimal(0);
        HashMap<String,BigDecimal> familyOutComeSum=new HashMap<>();
        HashMap<String,BigDecimal> familyInComeSum=new HashMap<>();
        HashMap<String,BigDecimal> personOutComeSum=new HashMap<>();
        HashMap<String,BigDecimal> personInComeSum=new HashMap<>();
        for (Order order:familyOrders) {
            if(order.getMId().equals(mid)){
                if(order.getIType().equals("支出")){
                       personOutCome=personOutCome.add(order.getOMoney());
                        BigDecimal now=personOutComeSum.get(order.getIName());
                        if(now==null){
                            personOutComeSum.put(order.getIName(),order.getOMoney());
                        }else{
                            personOutComeSum.put(order.getIName(),now.add(order.getOMoney()));
                        }
                }else {
                       personInCome= personInCome.add(order.getOMoney());
                        BigDecimal now=personInComeSum.get(order.getIName());
                        if(now==null){
                            personInComeSum.put(order.getIName(),order.getOMoney());
                        }else{
                            personInComeSum.put(order.getIName(),now.add(order.getOMoney()));
                        }

                }
            }
            if(order.getIType().equals("支出")){
                familyOutCome=familyOutCome.add(order.getOMoney());
                BigDecimal now=familyOutComeSum.get(order.getIName());
                if(now==null){
                    familyOutComeSum.put(order.getIName(),order.getOMoney());
                }else{
                    familyOutComeSum.put(order.getIName(),now.add(order.getOMoney()));
                }
            }else {
               familyInCome=familyInCome.add(order.getOMoney());
                BigDecimal now=familyInComeSum.get(order.getIName());
                if(now==null){
                    familyInComeSum.put(order.getIName(),order.getOMoney());
                }else{
                    familyInComeSum.put(order.getIName(),now.add(order.getOMoney()));
                }
            }
        }
        statisticsResult.setFamilyInCome(familyInCome);
        statisticsResult.setFamilyOutCome(familyOutCome);
        statisticsResult.setPersonInCome(personInCome);
        statisticsResult.setPersonOutCome(personOutCome);
        statisticsResult.setFamilyInComeSum(familyInComeSum);
        statisticsResult.setFamilyOutComeSum(familyOutComeSum);
        statisticsResult.setPersonOutComeSum(personOutComeSum);
        statisticsResult.setPersonInComeSum(personInComeSum);
        return statisticsResult;
    }

}
