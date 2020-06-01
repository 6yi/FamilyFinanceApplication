package com.lzheng.familyfinance.dto;

import lombok.Data;

/**
 * @ClassName DeleteDTO
 * @Author 6yi
 * @Date 2020/5/30 9:54
 * @Version 1.0
 * @Description:
 */

@Data
public class DeleteDTO {
    private Integer mid;
    private Integer[] oids;
}
