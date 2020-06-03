package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Quota;

import java.math.BigDecimal;

public interface QuotaDao {
    int deleteByPrimaryKey(Integer qId);

    int insert(Quota record);

    int insertSelective(Quota record);

    Quota selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Quota record);

    int updateByPrimaryKey(Quota record);

    int updateEexpenses(BigDecimal money,Integer mid);

    int resetQuota();
}