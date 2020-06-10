package com.lzheng.familyfinance.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.dto.UpdateDTO;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.service.MemberService;
import com.lzheng.familyfinance.utils.JWTUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        Result result = memberService.login(username, password);
        return  result;
    }


    @PostMapping("/member/updatename")
    public Result updateName(@RequestBody JSONObject jsonParam,
                         HttpServletRequest request){
        UpdateDTO updateDTO = jsonParam.toJavaObject(UpdateDTO.class);
        Integer tmid=(Integer) request.getAttribute("mid");
        if(!tmid.equals(updateDTO.getMid())){
            throw new GlobalException(302,"你别瞎删别人的,兄弟");
        }
        Member member = new Member();
        member.setMName(updateDTO.getName());
        member.setMId(updateDTO.getMid());
        memberService.updateName(member);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return  result;

    }

    @PostMapping("/member/updatepassword")
    public Result updatePassword(@RequestBody JSONObject jsonParam,
                                 HttpServletRequest request){
        UpdateDTO updateDTO = jsonParam.toJavaObject(UpdateDTO.class);
        Integer tmid=(Integer) request.getAttribute("mid");
        if(!tmid.equals(updateDTO.getMid())){
            throw new GlobalException(302,"你别瞎改别人的,兄弟");
        }
        System.out.println();
        Member member = new Member();
        member.setMPassword(updateDTO.getPassword());
        member.setMId(updateDTO.getMid());

        memberService.updatePassword(member);

        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return  result;
    }



}
