package com.lzheng.familyfinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName IndexController
 * @Author 6yi
 * @Date 2020/6/9 20:57
 * @Version 1.0
 * @Description:
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
