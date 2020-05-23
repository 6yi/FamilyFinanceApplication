package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    int deleteByPrimaryKey(Integer oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectAll();

    List<Order> selectByDate(@Param(value = "startDate") Date startDate
            ,@Param(value = "endDate") Date endDate);

}