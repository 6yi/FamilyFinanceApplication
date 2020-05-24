package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Item;

import java.util.List;

public interface ItemDao {
    int deleteByPrimaryKey(Integer iId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer iId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectByType(String type);
}