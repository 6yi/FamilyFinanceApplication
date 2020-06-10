package com.lzheng.familyfinance.dto;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName UpdateDTO
 * @Author 6yi
 * @Date 2020/6/7 19:07
 * @Version 1.0
 * @Description:
 */

@Data
public class UpdateDTO {
    private String name;
    private Integer mid;
    private String password;
}
