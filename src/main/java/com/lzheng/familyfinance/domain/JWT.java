package com.lzheng.familyfinance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @ClassName JWT
 * @Author 6yi
 * @Date 2020/5/23 14:50
 * @Version 1.0
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWT {
    private String head;
    private String playLoad;
    private String signal;

}



