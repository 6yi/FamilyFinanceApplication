package com.lzheng.familyfinance.dto;

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
public class Result {

    private Integer code;
    private String msg;
    private List<Object> data;

}
