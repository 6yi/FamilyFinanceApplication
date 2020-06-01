package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Tips;

import java.util.List;

public interface TipsDao {
    int deleteByPrimaryKey(Integer tId);

    int insert(Tips record);

    int insertSelective(Tips record);

    Tips selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Tips record);

    int updateByPrimaryKey(Tips record);
    List<Tips> selectAll();

    void updateTips(Integer tid);
}