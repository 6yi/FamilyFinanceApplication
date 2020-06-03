package com.lzheng.familyfinance.config;

import com.lzheng.familyfinance.dao.QuotaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ClassName TimeTasker
 * @Author 6yi
 * @Date 2020/6/3 11:09
 * @Version 1.0
 * @Description:
 */


@EnableScheduling
@Configuration
public class TimeTasker {
    @Autowired
    QuotaDao quotaDao;

    @Scheduled(cron = "0 0 1 1 * ?")
    public void resetQuota(){
        quotaDao.resetQuota();
    }

}
