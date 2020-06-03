package com.lzheng.familyfinance.controller;

import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.MemberService;
import com.lzheng.familyfinance.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName LoginController
 * @Author 6yi
 * @Date 2020/5/23 14:39
 * @Version 1.0
 * @Description:
 */

@RestController
public class LoginController {

    @Autowired
    MemberService memberService;

    @PostMapping("/login")
    public Result login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password){
        Member member = memberService.login(username, password);
        Result result = new Result();
        if(member==null){
            result.setCode(500);
            result.setMsg("用户名或密码错误");
        }else{
            result.setCode(0);
            JWT newJwt = JWTUtils.getNewJwt(member.getMType(),member.getMId());
            result.setMsg(newJwt.getHead()+"."+newJwt.getPlayLoad()+"."+newJwt.getSignal());
            ArrayList<Object> members = new ArrayList<>();
            members.add(member);
            result.setData(members);
        }
        return  result;
    }




}
