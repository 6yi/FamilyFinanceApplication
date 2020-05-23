package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * order
 * @author 
 */
@Data
public class Order implements Serializable {
    private Integer oId;

    private Integer iId;

    private Integer mId;

    private BigDecimal oMoney;

    private Date oDate;

    private String oTips;

    private Integer status;

    private String iName;

    private String mName;

    private static final long serialVersionUID = 1L;
}