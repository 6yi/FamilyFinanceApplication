package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * quota
 * @author 
 */
@Data
public class Quota implements Serializable {
    private Integer qId;

    private Integer mId;

    private BigDecimal qExpenses;

    private BigDecimal qLimit;

    private static final long serialVersionUID = 1L;
}