package com.lzheng.familyfinance.dao;

import com.lzheng.familyfinance.domain.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    int deleteByPrimaryKey(Integer mId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member selectByUserName(String username);

    Member selectByUserNameAndPassword(String username, String password);
}