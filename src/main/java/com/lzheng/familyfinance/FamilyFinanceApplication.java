package com.lzheng.familyfinance;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * @ClassName FamilyFinanceApplication
 * @Author 6yi
 * @Date 2020/5/22 12:12
 * @Version 1.0
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.lzheng.familyfinance.dao")
public class FamilyFinanceApplication {
    public static void main(String[] args) {

        SpringApplication.run(FamilyFinanceApplication.class, args);
    }
}