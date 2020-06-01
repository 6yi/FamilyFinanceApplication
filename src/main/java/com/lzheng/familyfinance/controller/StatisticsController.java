package com.lzheng.familyfinance.controller;

import cn.hutool.core.date.DateUtil;
import com.lzheng.familyfinance.domain.StatisticsResult;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.OrderService;
import com.lzheng.familyfinance.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName StatisticsController
 * @Author 6yi
 * @Date 2020/5/31 14:48
 * @Version 1.0
 * @Description:
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {


    @Autowired
    private StatisticsService statisticsService;


    @PostMapping("/get")
    public Result getResult(@RequestParam(value = "start", required = true) String start
            , @RequestParam(value = "end", required = true) String end
            , HttpServletRequest request){
        Date startDate = DateUtil.parse(start);
        Date endDate = DateUtil.parse(end);
        StatisticsResult statisticsResult = statisticsService.getResult(startDate, endDate, (Integer)request.getAttribute("mid"));
        Result result = new Result();
        result.setData(statisticsResult);
        result.setMsg("success");
        result.setCode(0);
        return result;
    }



}
