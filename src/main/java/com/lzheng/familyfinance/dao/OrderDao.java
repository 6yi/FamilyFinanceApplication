package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    int deleteByPrimaryKey(Integer oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Order record);


    int updateByPrimaryKey(Order record);

    int updateOrderStatus(Integer oId,Integer mId);


    List<Order> selectAll();

    List<Order> selectByDate(@Param(value = "startDate") Date startDate
            ,@Param(value = "endDate") Date endDate);

    List<Order> selectByDateAndMid(@Param(value = "startDate") Date startDate
            ,@Param(value = "endDate") Date endDate,@Param(value = "mid") Integer mid);


    void updateOrderByIid(Integer id);
}