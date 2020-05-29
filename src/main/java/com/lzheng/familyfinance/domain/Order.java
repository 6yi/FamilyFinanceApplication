package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * order
 * @author 
 */
@Data
@ApiModel("支出收入账单实体")
public class Order implements Serializable {
    @ApiModelProperty("账单ID")
    private Integer oId;

    @ApiModelProperty("项目ID")
    private Integer iId;

    @ApiModelProperty("成员ID")
    private Integer mId;

    @ApiModelProperty("账单金额")
    private BigDecimal oMoney;

    @ApiModelProperty("账单日期")
    private Date oDate;

    @ApiModelProperty("账单备注")
    private String oTips;

    @ApiModelProperty("账单状态")
    private Integer status;

    @ApiModelProperty("账单类型")
    private String iType;


    @ApiModelProperty("账单项目名")
    private String iName;

    @ApiModelProperty("创建账单的成员名")
    private String mName;

    private static final long serialVersionUID = 1L;
}