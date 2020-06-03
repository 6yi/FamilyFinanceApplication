package com.lzheng.familyfinance.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RegisterController
 * @Author 6yi
 * @Date 2020/6/2 23:44
 * @Version 1.0
 * @Description:
 */

@RestController
public class RegisterController {

    @Autowired
    MemberService memberService;

    @PostMapping("/register")
    public Result register(@RequestBody JSONObject jsonParam){
        Member member = jsonParam.toJavaObject(Member.class);
        Result register = memberService.register(member);
        return register;
    }

}
