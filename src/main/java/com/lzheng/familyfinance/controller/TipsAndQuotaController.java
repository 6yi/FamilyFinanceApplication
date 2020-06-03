package com.lzheng.familyfinance.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.domain.Quota;
import com.lzheng.familyfinance.domain.Tips;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.service.TipsAndQuotaService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName TipsAndQuotaController
 * @Author 6yi
 * @Date 2020/5/31 21:27
 * @Version 1.0
 * @Description:
 */

@RestController
public class TipsAndQuotaController {

    @Autowired
    TipsAndQuotaService tipsAndQuotaService;

    @GetMapping("/tips")
    public Result getTips(){
        List<Tips> tips = tipsAndQuotaService.getTips();
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(tips);
        return  result;
    }

    @GetMapping("/tipsok/{id}")
    public Result TipsOk(@PathVariable("id")Integer id){
        tipsAndQuotaService.tipsOk(id);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return  result;
    }

    @PostMapping("/addtips")
    public Result addTips(@RequestParam(value = "title",required = true) String title,@RequestParam(value = "body",required = true) String body){
        Tips tips = new Tips();
        tips.setStatus(1);
        tips.setTBody(body);
        tips.setTTitle(title);
        tipsAndQuotaService.addTips(tips);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return  result;
    }

    @GetMapping("/quota/{id}")
    public Result getQuota(@PathVariable("id")Integer id){
        Quota quota = tipsAndQuotaService.getQuota(id);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(quota);
        return  result;
    }

    @PutMapping("/updatequota")
    public Result update(@RequestBody JSONObject jsonParam, HttpServletRequest request){
        Quota quota = jsonParam.toJavaObject(Quota.class);
        if(!request.getAttribute("mid").equals(quota.getMId())){
            throw  new GlobalException(302,"你没有修改的权限");
        }
        tipsAndQuotaService.updateQuota(quota);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }



}
