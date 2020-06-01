package com.lzheng.familyfinance.controller;

import com.lzheng.familyfinance.domain.Tips;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.TipsAndQuotaService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
