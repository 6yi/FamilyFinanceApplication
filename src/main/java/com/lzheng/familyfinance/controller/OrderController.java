package com.lzheng.familyfinance.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.dto.DeleteDTO;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.service.OrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;
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




    @PostMapping("/get")
    public Result getOrder(){
        List<Order> allOrder = orderService.getAllOrder();
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(allOrder);
        return result;
    }

    @DeleteMapping("/delete")
    public Result deleteOrder(@RequestBody JSONObject jsonParam, HttpServletRequest request){
        try{
            DeleteDTO deleteDTO = jsonParam.toJavaObject(DeleteDTO.class);
            Integer mid=deleteDTO.getMid();
            Integer tmid=(Integer) request.getAttribute("mid");
            if(!tmid.equals(mid)){
                throw new GlobalException(302,"你别瞎删别人的,兄弟");
            }

            orderService.updateOrderStatus(deleteDTO.getOids(),tmid);

            Result result = new Result();
            result.setCode(0);
            result.setMsg("success");
            return result;
        }catch (Exception e){
            throw new GlobalException(302,e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result updateOrder(@RequestBody JSONObject jsonParam){
        try{
            Order order = jsonParam.toJavaObject(Order.class);
            orderService.updateByPrimaryKey(order);
            Result result = new Result();
            result.setCode(0);
            result.setMsg("success");
            return result;
        }catch (Exception e){
            throw new GlobalException(302,e.getMessage());
        }

    }

    @PostMapping("/getbytime")
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

    @PostMapping("/getbytimeandmid")
    public Result getOrderByTime(@RequestParam(value = "start", required = true) String start
            ,@RequestParam(value = "end", required = true) String end
            ,@RequestParam(value = "mid", required = true) Integer mid
            ,HttpServletRequest request){
        try{
            Date startDate = DateUtil.parse(start);
            Date endDate = DateUtil.parse(end);
            Integer tmid=(Integer)request.getAttribute("mid");
            if(!mid.equals(tmid)){

                throw new GlobalException(302,"禁止查看他人账单");
            }
            List<Order> orderByTime = orderService.getOrderByTimeAndMid(startDate, endDate,mid);
            Result result = new Result();
            result.setCode(0);
            result.setMsg("success");
            result.setData(orderByTime);
            return result;
        }catch (Exception e){
            throw new GlobalException(302,e.getMessage());
        }
    }





    @PostMapping("/addorder")
    public Result addOrder(@RequestParam(value = "type", required = true) Integer type
            ,@RequestParam(value = "item", required = true) Integer item
            ,@RequestParam(value = "money", required = true) String money
            ,@RequestParam(value = "tips", required = true) String tips
            ,@RequestParam(value = "time", required = true) String time
            ,@RequestParam(value = "mid", required = true) Integer mid
            ,HttpServletRequest request){
        Result result = new Result();
        try{
            DateTime dateTime = DateUtil.parse(time);
            Order order = new Order();
            order.setIId(item);
            order.setMId(mid);
            order.setOTips(tips);
            order.setODate(dateTime);
            if(type.equals(1)){
                order.setIType("支出");
            }else{
                order.setIType("收入");
            }
            order.setOMoney(new BigDecimal(money));
            order.setStatus(1);
            orderService.addOrder(order);
            result.setCode(0);
            result.setMsg("success");
            return result;
        }catch (Exception e){
            throw new GlobalException(500,e.getMessage());
        }

    }


}
