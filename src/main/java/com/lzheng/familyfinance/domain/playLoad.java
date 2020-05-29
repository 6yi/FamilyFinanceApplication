package com.lzheng.familyfinance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName playLoad
 * @Author 6yi
 * @Date 2020/5/23 16:03
 * @Version 1.0
 * @Description:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class playLoad{
    private String type;
    private Integer mid;
    private Long Expired;

}