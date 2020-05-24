package com.lzheng.familyfinance.service;

import cn.hutool.crypto.digest.Digester;
import com.lzheng.familyfinance.dao.MemberDao;
import com.lzheng.familyfinance.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberService
 * @Author 6yi
 * @Date 2020/5/23 16:40
 * @Version 1.0
 * @Description:
 */

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    Digester digester;

    public Member login(String username, String password){
        return memberDao.selectByUserNameAndPassword(username,digester.digestHex(password));
    }


}
