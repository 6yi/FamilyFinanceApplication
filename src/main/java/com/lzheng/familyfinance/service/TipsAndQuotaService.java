package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.QuotaDao;
import com.lzheng.familyfinance.dao.TipsDao;
import com.lzheng.familyfinance.domain.Quota;
import com.lzheng.familyfinance.domain.Tips;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TipsAndQuotaService
 * @Author 6yi
 * @Date 2020/5/31 21:28
 * @Version 1.0
 * @Description:
 */

@Service
public class TipsAndQuotaService {

    @Autowired
    private TipsDao tipsDao;

    @Autowired
    private QuotaDao quotaDao;
    public List<Tips> getTips(){
        return tipsDao.selectAll();
    }

    public void tipsOk(Integer tid){
        tipsDao.updateTips(tid);
    }

    public void addTips(Tips tips){
        tipsDao.insert(tips);
    }

    public Quota getQuota(Integer id){
        return quotaDao.selectByPrimaryKey(id);
    }

    public void updateQuota(Quota quota){
        quotaDao.updateByPrimaryKey(quota);
    }
}
