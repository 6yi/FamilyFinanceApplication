package com.lzheng.familyfinance.controller;

import cn.hutool.core.date.DateUtil;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderController
 * @Author 6yi
 * @Date 2020/5/23 18:53
 * @Version 1.0
 * @Description:
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/get")
    public Result getOrder(){
        List<Order> allOrder = orderService.getAllOrder();
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(allOrder);
        return result;
    }

    @GetMapping("/getbytime")
    public Result getOrderByTime(@RequestParam(value = "start", required = true) String start
            ,@RequestParam(value = "end", required = true) String end){
        try{
            Date startDate = DateUtil.parse(start);
            Date endDate = DateUtil.parse(end);
            List<Order> orderByTime = orderService.getOrderByTime(startDate, endDate);
            Result result = new Result();
            result.setCode(0);
            result.setMsg("success");
            result.setData(orderByTime);
            return result;
        }catch (Exception e){
            throw new GlobalException(302,e.getMessage());
        }
    }

}
