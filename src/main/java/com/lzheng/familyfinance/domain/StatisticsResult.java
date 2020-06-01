package com.lzheng.familyfinance.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @ClassName StatisticsResult
 * @Author 6yi
 * @Date 2020/5/31 14:51
 * @Version 1.0
 * @Description:
 */

@Data
public class StatisticsResult {

    private BigDecimal familyOutCome;
    private BigDecimal familyInCome;

    private BigDecimal personOutCome;
    private BigDecimal personInCome;


    private HashMap<String,BigDecimal> familyOutComeSum;
    private HashMap<String,BigDecimal> familyInComeSum;

    private HashMap<String,BigDecimal> personOutComeSum;
    private HashMap<String,BigDecimal> personInComeSum;

}
