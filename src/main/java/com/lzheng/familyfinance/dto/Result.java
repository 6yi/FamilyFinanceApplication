package com.lzheng.familyfinance.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ResultDTO
 * @Author 6yi
 * @Date 2020/5/23 13:07
 * @Version 1.0
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回结果")
public class Result<T> {

    @ApiModelProperty("结果状态码")
    private Integer code;
    @ApiModelProperty("结果信息,如果是授权则是JWT")
    private String msg;
    @ApiModelProperty("结果数据")
    private T data;

}
