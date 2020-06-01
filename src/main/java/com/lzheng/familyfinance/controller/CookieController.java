package com.lzheng.familyfinance.controller;

import com.lzheng.familyfinance.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CookieController
 * @Author 6yi
 * @Date 2020/6/1 13:41
 * @Version 1.0
 * @Description:
 */

@RestController
public class CookieController {


    @PostMapping("/cookie/login")
    public Result cookieLogin(){
        Result result = new Result();
        result.setMsg("success");
        result.setCode(0);
        return result;
    }


}
