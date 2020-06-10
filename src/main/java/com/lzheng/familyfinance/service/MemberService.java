package com.lzheng.familyfinance.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.Digester;
import com.lzheng.familyfinance.dao.MemberDao;
import com.lzheng.familyfinance.dao.QuotaDao;
import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.domain.Quota;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    private QuotaDao quotaDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    Digester digester;

    public Result login(String username, String password){
        Result result = new Result();
        if (StrUtil.isBlank(username)||username.length()<6||username.length()>18) {
            result.setCode(500);
            result.setMsg("用户名不能为空并且长度在6-18");
        }else if (StrUtil.hasBlank(password)||password.length()<6||password.length()>18) {
            result.setCode(500);
            result.setMsg("密码不能为空并且长度在6-18");
        }else {
            Member member = memberDao.selectByUserNameAndPassword(username, digester.digestHex(password));
            if (member == null) {
                result.setCode(500);
                result.setMsg("用户名或密码错误");
            } else {
                result.setCode(0);
                JWT newJwt = JWTUtils.getNewJwt(member.getMType(), member.getMId());
                result.setMsg(newJwt.getHead() + "." + newJwt.getPlayLoad() + "." + newJwt.getSignal());
                ArrayList<Object> members = new ArrayList<>();
                members.add(member);
                result.setData(members);
            }
        }
        return result;
    }

    public Result register(Member member){
        TransactionStatus transactionStatus = null;
        Result result = new Result();
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            if (StrUtil.isBlank(member.getMUsername())||member.getMUsername().length()<6||member.getMUsername().length()>18) {
                result.setCode(500);
                result.setMsg("用户名不能为空并且长度在6-18");
            }else if (StrUtil.hasBlank(member.getMPassword())||member.getMPassword().length()<6||member.getMPassword().length()>18) {
                result.setCode(500);
                result.setMsg("密码不能为空并且长度在6-18");
            }else if(memberDao.selectByUserName(member.getMUsername())!=null){
                result.setCode(500);
                result.setMsg("该用户名已经被注册");
            }else{
                member.setMPassword(digester.digestHex(member.getMPassword()));
                Quota quota = new Quota();
                member.setStatus(1);
                int insert = memberDao.insertSelective(member);

                quota.setMId(member.getMId());
                quota.setQLimit(new BigDecimal(3000));
                quota.setQExpenses(new BigDecimal(0));
                quotaDao.insert(quota);
                result.setCode(0);
                result.setMsg("success");
                member.setMPassword("");
                result.setData(member);
            }
            dataSourceTransactionManager.commit(transactionStatus);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500,"坏事了");
        }
        return result;

    }



    public void updateName(Member member){

        memberDao.updateName(member);

    }
    public void updatePassword(Member member){
        if (StrUtil.hasBlank(member.getMPassword())||member.getMPassword().length()<6||member.getMPassword().length()>18) {
            throw new GlobalException(500,"密码长度必须在6-18");
        }
        member.setMPassword(digester.digestHex(member.getMPassword()));
        memberDao.updatePassword(member);

    }



}
